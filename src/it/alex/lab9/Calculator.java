package it.alex.lab9;

import it.alex.lab9.operation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Calculator {
    private final Map<String, Operation> operationMap;

    public Calculator() {
        Operation add = new Addition();
        Operation subtract = new Subtraction();
        Operation multiply = new Multiplication();
        Operation exponent = new SquareExponent();
        operationMap = new HashMap<>();
        operationMap.put(add.getOperationSymbol(), add);
        operationMap.put(subtract.getOperationSymbol(), subtract);
        operationMap.put(multiply.getOperationSymbol(), multiply);
        operationMap.put(exponent.getOperationSymbol(), exponent);
    }

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
            String symbol = scanner.nextLine();
            if (operationMap.containsKey(symbol)) {
                return symbol;
            } else {
                System.out.println("Sorry. Try again!");
            }
        }
        return "Error";
    }

    private Operation getOperation(String operationSymbol) {
        return operationMap.get(operationSymbol);
    }

    private String selectOperation() {
        System.out.println("What operation do you want to do? ( '   +   '   -   '   *   '   ^   )");
        return readOperation();
    }

    private String[] getOperands(int quantity) {
        String[] arrayNumber = new String[quantity];
        for (int i = 0; i < quantity; i++) {
            System.out.println("Enter the number:");
            arrayNumber[i] = readUserInput();
        }
        return arrayNumber;
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        String operationSymbol = calculator.selectOperation();
        Operation operation = calculator.getOperation(operationSymbol);
        int quantity = operation.getNumberOfOperands();
        String result = operation.operate(calculator.getOperands(quantity));
        calculator.printResult(result, operation);
    }

    private void printResult(String result, Operation operation) {
        System.out.println(operation.getName() + " result:");
        System.out.println(result);
    }
}