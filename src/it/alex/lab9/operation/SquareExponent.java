package it.alex.lab9.operation;

import it.alex.lab9.Operation;

public class SquareExponent extends AbstractOperation {

    public int getNumberOfOperands() {
        final int operandsNumber = 1;
        return operandsNumber;
    }

    public final String getOperationSymbol() {
        String symbol = "^";
        return symbol;
    }

    public String getName() {
        return "Square exponentiation";
    }

    public String operate(String... number) {
        String firstNumber = number[0];
        Operation operation = new Multiplication();
        return operation.operate(firstNumber, firstNumber);
    }
}
