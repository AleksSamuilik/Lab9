import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Addition extends MethodsOperation implements Operation {


    public int getNumberOfOperands() {
        final int operandsNumber = 2;
        return operandsNumber;
    }

    public Map getSymbolOperation() {
        Map<String, Operation> operation = new HashMap<>();
        Operation add = new Addition();
        operation.put("+", add);
        return operation;
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
