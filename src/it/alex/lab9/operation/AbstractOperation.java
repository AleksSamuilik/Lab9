package it.alex.lab9.operation;

import it.alex.lab9.Operation;

import java.util.Stack;

public abstract class AbstractOperation implements Operation {

    protected boolean comparisonOperation(String firstNumber, String secondNumber) {
        if (firstNumber.compareTo(secondNumber) >= 0) {
            return true;
        } else {
            return false;
        }
    }

    public int getNumberOfOperands() {
        final int operandsNumber = 2;
        return operandsNumber;
    }

    protected Stack createStack(String number) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < number.length(); i++) {
            stack.push(number.charAt(i) - '0');
        }
        return stack;
    }

    protected String checkResultStringNumber(boolean selectsDirection, String resultStringNumber) {
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
}
