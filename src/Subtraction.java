import java.util.Stack;

public class Subtraction implements Operation {

    public int counterGetNumber(){
final int operandsNumber = 2;
        return operandsNumber;
    }

    public  String operate(String ... number) {
        String firstNumber = number[0];
        String secondNumber = number[1];
        Calculator calculator = new Calculator();
        boolean selectsDirection =calculator.comparisonOperation(firstNumber, secondNumber);
        Stack<Integer> stack1 =calculator.createStack(firstNumber);
        Stack<Integer> stack2 =calculator.createStack(secondNumber);
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
        return calculator.checkResultStringNumber(selectsDirection, builder.toString());
    }
}
