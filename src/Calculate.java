import java.util.Scanner;
import java.util.Stack;

public class Calculate {

    public static String readUserInput() {
        Scanner scanner = new Scanner(System.in);
        String number = "";
        while (scanner.hasNextLine()) {
            boolean valid = true;
            number = scanner.nextLine();
            for (int i = 0; i < number.length(); i++) {
                char digit = number.charAt(i);
                if (Character.isDigit(digit)) {
                    continue;
                } else {
                    System.out.println("Sorry. Try again");
                    valid = false;
                    break;
                }
            }
            if (valid) {
                break;
            }
        }
        return number;
    }

    public static int readOperation() {
        Scanner scanner = new Scanner(System.in);
        boolean checkInput = true;
        while (checkInput) {
            String number = scanner.nextLine();
            try {
                String add = "+";
                String subtract = "-";
                String multiply = "*";
                if (number.equals(add)) {
                    return 0;
                } else if (number.equals(subtract)) {
                    return 1;
                } else if (number.equals(multiply)) {
                    return 2;
                } else
                    System.out.println("Sorry. Try again!");
            } catch (NumberFormatException e) {
                System.out.println("Sorry. Try again!");
            }
        }
        scanner.close();
        return -1;
    }

    public static String additionOperation(String firstNumber, String secondNumber) {
        Stack<Integer> stack1 = createStack(firstNumber);
        Stack<Integer> stack2 = createStack(secondNumber);
        int residue = 0;
        int result;
        int digit;
        StringBuilder builder = new StringBuilder();
        while (true) {
            if (stack1.size() != 0 && stack2.size() != 0) {
                result = stack1.pop() + stack2.pop() + residue;
                digit = result % 10;
                residue = (int) (result / Math.pow(10, 1)) % 10;
                builder.insert(0, digit);
            } else if (stack1.size() != 0 && stack2.size() == 0) {
                result = stack1.pop() + residue;
                residue = (int) (result / Math.pow(10, 1)) % 10;
                digit = result % 10;
                builder.insert(0, digit);
            } else if (stack1.size() == 0 && stack2.size() != 0) {
                result = stack2.pop() + residue;
                residue = (int) (result / Math.pow(10, 1)) % 10;
                digit = result % 10;
                builder.insert(0, digit);
            } else {
                if (residue > 0) {
                    builder.insert(0, residue);
                }
                break;
            }
        }
        return builder.toString();
    }

    public static boolean checkFirstGreaterSecond(String string1, String string2) {
        if (string1.length() > string2.length()) {
            return true;
        } else if (string1.length() == string2.length()) {
            for (int i = 0; i < string1.length(); i++) {
                if (string1.charAt(i) > string2.charAt(i)) {
                    return true;
                } else if (string1.charAt(i) == string2.charAt(i)) {
                    continue;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    public static Stack createStack(String string) {
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = 0; i < string.length(); i++) {
            int digit = Integer.parseInt(String.valueOf(string.charAt(i)));
            stack.push(digit);
        }
        return stack;
    }

    public static String subtractionOperation(String firstNumber, String secondNumber) {
        boolean selectsDirection = checkFirstGreaterSecond(firstNumber, secondNumber);
        Stack<Integer> stack1 = createStack(firstNumber);
        Stack<Integer> stack2 = createStack(secondNumber);
        Stack<Integer> largerNumber;
        Stack<Integer> lessNumber;
        if (selectsDirection) {
            largerNumber = stack1;
            lessNumber = stack2;
        } else {
            largerNumber = stack2;
            lessNumber = stack1;
        }
        int residue = 0;
        int digit = 10;
        int result;
        StringBuilder builder = new StringBuilder();
        while (true) {
            if (lessNumber.size() != 0) {
                result = largerNumber.pop() - lessNumber.pop() - residue;
                if (result >= 0) {
                    builder.insert(0, result);
                    residue = 0;
                } else {
                    result = result + digit;
                    result = (int) (Math.log10((result)));
                    residue = 1;
                    builder.insert(0, result);
                }
            } else if (largerNumber.size() > 0 && lessNumber.size() == 0) {
                builder.insert(0, largerNumber.pop());
            } else if (largerNumber.size() == 0 && lessNumber.size() == 0) {
                break;
            }
        }
        return checkResultStringNumber(selectsDirection, builder.toString());
    }

    public static String checkResultStringNumber(boolean selectsDirection, String resultStringNumber) {
        String result = "";
        char check = '0';
        for (int i = 0; i < resultStringNumber.length(); i++) {
            if (resultStringNumber.charAt(i) == check && resultStringNumber.length() > i + 1) {
                result = resultStringNumber.substring(i + 1);
            } else if (resultStringNumber.charAt(i) != check) {
                result = resultStringNumber.substring(i);
                break;
            } else {
                break;
            }
        }
        if (!selectsDirection && (result.length() != 1 || result.charAt(result.length() - 1) != check)) {
            StringBuilder builder = new StringBuilder();
            builder.insert(0, result);
            builder.insert(0, "-");
            result = builder.toString();
        }
        return result;
    }

    public static String[] arrayString(String string1, String string2) {
        boolean valid = checkFirstGreaterSecond(string1, string2);
        int length;
        Stack<Integer> stackLargeNumber;
        Stack<Integer> stackLessNumber;
        if (valid) {
            length = string2.length();
            stackLargeNumber = createStack(string1);
            stackLessNumber = createStack(string2);
        } else {
            length = string1.length();
            stackLargeNumber = createStack(string2);
            stackLessNumber = createStack(string1);
        }
        String[] arrayString = new String[length];
        for (int i = 0; i < arrayString.length; i++) {
            arrayString[i] = multiply(stackLargeNumber, stackLessNumber, i);
            stackLessNumber.pop();
        }
        return arrayString;
    }

    public static String multiply(Stack<Integer> stackLargeNumber, Stack<Integer> stackLessNumber, int numberLineArray) {
        StringBuilder builder = new StringBuilder();
        Stack<Integer> number = new Stack<>();
        number.addAll(stackLargeNumber);
        int residue = 0;
        int result = 0;
        for (int i = 0; i < stackLargeNumber.size(); i++) {
            result = number.pop() * stackLessNumber.peek() + residue;
            int digit = result % 10;
            residue = (int) (result / Math.pow(10, 1)) % 10;
            builder.insert(0, digit);
        }
        if (residue > 0) {
            builder.insert(0, residue);
        }
        for (int i = 0; i < numberLineArray; i++) {
            builder.insert(builder.length(), "0");
        }
        return builder.toString();
    }

    public static String multiplyOperation(String firstNumber, String secondNumber) {
        String[] array = arrayString(firstNumber, secondNumber);
        String result = "0";
        for (int i = 0; i < array.length; i++) {
            result = additionOperation(result, array[i]);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("Write the first number:");
        String firstNumber = readUserInput();
        System.out.println("Write the second number:");
        String secondNumber = readUserInput();
        System.out.println("Your the first number: " + "\nYour the second number: ");
        System.out.println("What operation do you want to do? ( '   +   '   -   '   *   ')");
        int check = readOperation();
        switch (check) {
            case 0:
                System.out.println(additionOperation(firstNumber, secondNumber));
                break;
            case 1:
                System.out.println(subtractionOperation(firstNumber, secondNumber));
                break;
            case 2:
                System.out.println(multiplyOperation(firstNumber, secondNumber));
        }
    }
}