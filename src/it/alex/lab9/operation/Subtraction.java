package it.alex.lab9.operation;

import java.util.Stack;

public class Subtraction extends AbstractOperation {

    public final String getOperationSymbol() {
        String symbol = "-";
        return symbol;
    }

    public String getName() {
        return "Subtraction";
    }

    public String operate(String... number) {
        String firstNumber = number[0];
        String secondNumber = number[1];
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
}