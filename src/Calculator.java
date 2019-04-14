
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class Calculator implements Operation {

    private String readUserInput() {
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

    private String readOperation() {
        Scanner scanner = new Scanner(System.in);
        boolean checkInput = true;
        while (checkInput) {
            String number = scanner.nextLine();
            try {
                String add = "+";
                String subtract = "-";
                String multiply = "*";
                if (number.equals(add)) {
                    return "+";
                } else if (number.equals(subtract)) {
                    return "-";
                } else if (number.equals(multiply)) {
                    return "*";
                } else
                    System.out.println("Sorry. Try again!");
            } catch (NumberFormatException e) {
                System.out.println("Sorry. Try again!");
            }
        }
        scanner.close();
        return "Error";
    }

    public String additionOperation(String firstNumber, String secondNumber) {
        Stack<Integer> stackFirstNumber = createStack(firstNumber);
        Stack<Integer> stackSecondNumber = createStack(secondNumber);
        int residue = 0;
        int result;
        int digit;
        StringBuilder builder = new StringBuilder();
        while (true) {
            if (stackFirstNumber.size() != 0 && stackSecondNumber.size() != 0) {
                result = stackFirstNumber.pop() + stackSecondNumber.pop() + residue;
                digit = result % 10;
                residue = (int) (result / Math.pow(10, 1)) % 10;
                builder.insert(0, digit);
            } else if (stackFirstNumber.size() != 0 && stackSecondNumber.size() == 0) {
                result = stackFirstNumber.pop() + residue;
                residue = (int) (result / Math.pow(10, 1)) % 10;
                digit = result % 10;
                builder.insert(0, digit);
            } else if (stackFirstNumber.size() == 0 && stackSecondNumber.size() != 0) {
                result = stackSecondNumber.pop() + residue;
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

    private static boolean comparisonOperation(String firstNumber, String secondNumber) {
        if (firstNumber.compareTo(secondNumber) > 0) {
            return true;
        } else if (firstNumber.compareTo(secondNumber) == 0) {
            return true;
        } else {
            return false;
        }
    }

    private static Stack createStack(String number) {
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = 0; i < number.length(); i++) {
            stack.push(Character.digit(number.charAt(i), 10));
        }
        return stack;
    }

    public  String subtractionOperation(String firstNumber, String secondNumber) {
        boolean selectsDirection = comparisonOperation(firstNumber, secondNumber);
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

    private static String checkResultStringNumber(boolean selectsDirection, String resultStringNumber) {
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

    private static String[] arrayString(String firstNumber, String secondNumber) {
        boolean valid = comparisonOperation(firstNumber, secondNumber);
        int length;
        Stack<Integer> stackLargeNumber;
        Stack<Integer> stackLessNumber;
        if (valid) {
            length = secondNumber.length();
            stackLargeNumber = createStack(firstNumber);
            stackLessNumber = createStack(secondNumber);
        } else {
            length = firstNumber.length();
            stackLargeNumber = createStack(secondNumber);
            stackLessNumber = createStack(firstNumber);
        }
        String[] arrayString = new String[length];
        for (int i = 0; i < arrayString.length; i++) {
            arrayString[i] = multiply(stackLargeNumber, stackLessNumber, i);
            stackLessNumber.pop();
        }
        return arrayString;
    }

    private static String multiply(Stack<Integer> stackLargeNumber, Stack<Integer> stackLessNumber, int numberLineArray) {
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

    public  String multiplyOperation(String firstNumber, String secondNumber) {
        String[] array = arrayString(firstNumber, secondNumber);
        String result = "0";
        for (int i = 0; i < array.length; i++) {
            result = additionOperation(result, array[i]);
        }
        return checkResultStringNumber(true, result);
    }

    public String operate(String firstNumber, String secondNumber) {
        Map<String, Object> operation = new HashMap<>();
        operation.put("+", additionOperation(firstNumber, secondNumber));
        operation.put("-", subtractionOperation(firstNumber, secondNumber));
        operation.put("*", multiplyOperation(firstNumber, secondNumber));
        String selectOperation = selectOperation(firstNumber, secondNumber);
        return (String) operation.get(selectOperation);
    }


    public String selectOperation(String firstNumber, String secondNumber) {
        System.out.println("Your the first number: " + firstNumber + "\nYour the second number: " + secondNumber);
        System.out.println("What operation do you want to do? ( '   +   '   -   '   *   ')");
        return readOperation();
    }

    public String getNumber(String nameNumber) {
        System.out.println("Write the " + nameNumber + " number:");
        return readUserInput();
    }

    public static void main(String[] args) {
        Calculator operate = new Calculator();
        String firstNumber = operate.getNumber("first");
        String secondNumber = operate.getNumber("second");
        String result = operate.operate(firstNumber, secondNumber);
        System.out.println(result);

    }
}