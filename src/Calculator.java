import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class Calculator {

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
                String exponent = "^";
                if (number.equals(add)) {
                    return "+";
                } else if (number.equals(subtract)) {
                    return "-";
                } else if (number.equals(multiply)) {
                    return "*";
                } else if (number.equals(exponent)) {
                    return "^";
                } else
                    System.out.println("Sorry. Try again!");
            } catch (NumberFormatException e) {
                System.out.println("Sorry. Try again!");
            }
        }
        scanner.close();
        return "Error";
    }

    public static boolean comparisonOperation(String firstNumber, String secondNumber) {
        if (firstNumber.compareTo(secondNumber) >= 0) {
            return true;
        } else {
            return false;
        }
    }

    public static Stack createStack(String number) {
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = 0; i < number.length(); i++) {
            stack.push(Character.digit(number.charAt(i), 10));
        }
        return stack;
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

    public Operation getOperation(String operationSymbol) {
        Map<String, Operation> operationMap = new HashMap<>();
        Operation add = new Addition();
        Operation multiply = new Multiplication();
        Operation subtract = new Subtraction();
        Operation exponent = new Exponentiation();
        operationMap.put("+", add);
        operationMap.put("-", subtract);
        operationMap.put("*", multiply);
        operationMap.put("^", exponent);
        return operationMap.get(operationSymbol);
    }

    public String selectOperation() {
        System.out.println("What operation do you want to do? ( '   +   '   -   '   *   '   ^   )");
        return readOperation();
    }

    public String getNumber(String nameNumber, String operationSymbol) {
        if (operationSymbol.equals("^")) {
        return null;
        } else
        System.out.println("Write the " + nameNumber + " number:");
        return readUserInput();
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        String firstNumber = calculator.getNumber("first","");
        String operationSymbol = calculator.selectOperation();
        String secondNumber = calculator.getNumber("second",operationSymbol);
        Operation operation = calculator.getOperation(operationSymbol);
        String result = operation.operate(firstNumber, secondNumber);
        System.out.println(result);

    }
}