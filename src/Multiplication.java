import java.util.Stack;


public class Multiplication implements Operation {

    public int counterGetNumber(){
        final int operandsNumber = 2;
        return operandsNumber;
    }

    public String operate(String ... number) {
        String firstNumber=number[0];
        String secondNumber=number[1];
        Calculator multiplication = new Calculator();
        Operation addition = new Addition();
        String[] array = arrayString(firstNumber, secondNumber);
        String result = "0";
        for (int i = 0; i < array.length; i++) {
            result = addition.operate(result, array[i]);
        }
        return multiplication.checkResultStringNumber(true, result);
    }

    private static String[] arrayString(String firstNumber, String secondNumber) {
        Calculator multiplication = new Calculator();
        boolean valid = multiplication.comparisonOperation(firstNumber, secondNumber);
        int length;
        Stack<Integer> stackLargeNumber;
        Stack<Integer> stackLessNumber;
        if (valid) {
            length = secondNumber.length();
            stackLargeNumber = multiplication.createStack(firstNumber);
            stackLessNumber = multiplication.createStack(secondNumber);
        } else {
            length = firstNumber.length();
            stackLargeNumber = multiplication.createStack(secondNumber);
            stackLessNumber = multiplication.createStack(firstNumber);
        }
        String[] arrayString = new String[length];
        for (int i = 0; i < arrayString.length; i++) {
            arrayString[i] = multiply(stackLargeNumber, stackLessNumber, i);
            stackLessNumber.pop();
        }
        return arrayString;
    }

    private static String multiply(Stack<Integer> stackLargeNumber, Stack<Integer> stackLessNumber, int numberLineArray) {
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
