package it.alex.lab9;

import java.util.*;

public class Calculator {
    private final Map<String, Operation> operationMap;

    public Calculator(Collection<Operation> collection) {
        Iterator iterator = collection.iterator();
        operationMap = new HashMap<>();
        Operation operation;
        while (iterator.hasNext()) {
            operation = (Operation) iterator.next();
            operationMap.put(operation.getOperationSymbol(), operation);
        }
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
        System.out.print("What operation do you want to do? (  '    ");
        for (String key : operationMap.keySet()) {
            System.out.print(key + "   '  ");
        }
        System.out.print(")");
        System.out.println();
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

    public void calculate() {
        String operationSymbol = selectOperation();
        Operation operation = getOperation(operationSymbol);
        int quantity = operation.getNumberOfOperands();
        String result = operation.operate(getOperands(quantity));
        printResult(result, operation);
    }

    private void printResult(String result, Operation operation) {
        System.out.println(operation.getName() + " result:");
        System.out.println(result);
    }
}