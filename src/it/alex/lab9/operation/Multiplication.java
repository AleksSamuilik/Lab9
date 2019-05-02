package it.alex.lab9.operation;

import java.util.Stack;


public class Multiplication extends AbstractOperation {

    public final String getOperationSymbol() {
        String symbol = "*";
        return symbol;
    }

    public String getName() {
        return "Multiplication";
    }

    public String operate(String... number) {
        String firstNumber = number[0];
        String secondNumber = number[1];
        String[] array = arrayString(firstNumber, secondNumber);
        String result = "0";
        for (int i = 0; i < array.length; i++) {
            Addition addition = new Addition();
            result = addition.operate(result, array[i]);
        }
        return checkResultStringNumber(true, result);
    }

    private String[] arrayString(String firstNumber, String secondNumber) {
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

    private String multiply(Stack<Integer> stackLargeNumber, Stack<Integer> stackLessNumber, int numberLineArray) {
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
}
