package it.alex.lab9.operation;

public class SquareRoot extends AbstractOperation {
    @Override
    public String operate(String... inputNumber) {
        double t;
        double number =Double.parseDouble (inputNumber[0]);
        double squareRoot =number/ 2;
        do {
            t = squareRoot;
            squareRoot = (t + (number / t)) / 2;
        } while ((t - squareRoot) != 0);
        squareRoot = Math.round(squareRoot * 1000d) / 1000d;
        return String.valueOf(squareRoot);
    }

    @Override
    public String getOperationSymbol() {
        String symbol = "sqrt";
        return symbol;
    }

    @Override
    public String getName() {
        return "Square root extraction";
    }

    @Override
    public int getNumberOfOperands() {
        final int operandsNumber = 1;
        return operandsNumber;
    }
}
