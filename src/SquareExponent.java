import java.util.HashMap;
import java.util.Map;

public class SquareExponent implements Operation {

    public int getNumberOfOperands() {
        final int operandsNumber = 1;
        return operandsNumber;
    }

    public Map getSymbolOperation() {
        Map<String, Operation> operation = new HashMap<>();
        Operation exponent = new SquareExponent();
        operation.put("^", exponent);
        return operation;
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
