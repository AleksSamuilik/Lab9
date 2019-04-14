import java.util.Stack;

public class Addition implements Operation {
    public String operate(String firstNumber, String secondNumber) {
        Calculator addition = new Calculator();
        Stack<Integer> stackFirstNumber = addition.createStack(firstNumber);
        Stack<Integer> stackSecondNumber = addition.createStack(secondNumber);
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
