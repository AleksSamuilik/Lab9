import java.util.Stack;

public class MethodsOperation {

    public boolean comparisonOperation(String firstNumber, String secondNumber) {
        if (firstNumber.compareTo(secondNumber) >= 0) {
            return true;
        } else {
            return false;
        }
    }

    public Stack createStack(String number) {
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = 0; i < number.length(); i++) {
            stack.push(Character.getNumericValue(number.charAt(i)));
        }
        return stack;
    }

    public String checkResultStringNumber(boolean selectsDirection, String resultStringNumber) {
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
