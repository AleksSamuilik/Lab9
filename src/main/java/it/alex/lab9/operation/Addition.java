package it.alex.lab9.operation;

import java.util.Stack;

public class Addition extends AbstractOperation {


    public final String getOperationSymbol() {
        String symbol = "+";
        return symbol;
    }

    public String getName() {
        return "Addition";
    }

    public String operate(String... number) {
        String firstNumber = number[0];
        String secondNumber = number[1];
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
}
