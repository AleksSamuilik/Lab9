import java.util.Stack;

public class Exponentiation implements Operation {

    public String operate(String firstNumber, String secondNumber) {
        Operation operation = new Multiplication();
       return operation.operate(firstNumber,firstNumber);
    }
}
