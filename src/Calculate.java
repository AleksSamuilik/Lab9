import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Scanner;

public class Calculate {

    public static BigDecimal readUserInput() {

        Scanner scanner = new Scanner(System.in);
        BigDecimal base,one;
        one = new BigDecimal("0");
        base = new BigDecimal("-1");
        boolean checkInput = true;
        while (checkInput) {
            String number = scanner.nextLine();
            try {
                BigDecimal xxx = new BigDecimal(number);
                if (xxx.compareTo(one)>=0) {
                    checkInput = false;
                    return xxx;
                } else
                    System.out.println("Sorry. Try again!");
            } catch (NumberFormatException e) {
                System.out.println("Sorry. Try again!");
            }
        }
        scanner.close();
        return base;
    }

    public static int readOperation() {
        Scanner scanner = new Scanner(System.in);
        boolean checkInput = true;
        while (checkInput) {
            String number = scanner.nextLine();
            try {
                String add = "+";
                String subtract = "-";
                String multiply = "*";
                if (number.equals(add)) {
                    return 0;
                } else if (number.equals(subtract)) {
                    return 1;
                } else if (number.equals(multiply)) {
                    return 2;
                } else
                    System.out.println("Sorry. Try again!");
            } catch (NumberFormatException e) {
                System.out.println("Sorry. Try again!");
            }
        }
        scanner.close();
        return -1;
    }


    public static void main(String[] args) {
        BigDecimal firstNumber,secondNumber,result;

        System.out.println("Write the first number:");
        firstNumber  = readUserInput();
        System.out.println("Write the second number:");
        secondNumber  = readUserInput();
        System.out.println("Your the first number: " + firstNumber + "\nYour the second number: " + secondNumber);
        System.out.println("What operation do you want to do? ( '   +   '   -   '   *   ')");
        int check = readOperation();
        switch (check) {
            case 0:
                result = firstNumber.add(secondNumber);
                System.out.println("Result: " + result);
                break;
            case 1:
                result = firstNumber.subtract(secondNumber);
                System.out.println("Result: " + result);
                break;
            case 2:
                result = firstNumber.multiply(secondNumber);
                System.out.println("Result: " + result);
        }
    }
}