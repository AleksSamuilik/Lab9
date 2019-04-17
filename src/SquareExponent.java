import java.util.Stack;

public class SquareExponent implements Operation {

    public int counterGetNumber(){
        final int operandsNumber = 1;
        return operandsNumber;
    }

    public String operate(String ... number) {
        String firstNumber = number[0];
        Operation operation = new Multiplication();
       return operation.operate(firstNumber,firstNumber);
    }
}
