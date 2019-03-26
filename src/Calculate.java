import java.util.Scanner;

public class Calculate {

    public static long readUserInput() {

        Scanner scanner = new Scanner(System.in);
        boolean checkInput = true;
        while (checkInput) {
            String number = scanner.nextLine();
            try {
                long xxx = Long.parseLong(number);
                if (xxx >= 0) {
                    checkInput = false;
                    return xxx;
                } else
                    System.out.println("Sorry. Try again!");
            } catch (NumberFormatException e) {
                System.out.println("Sorry. Try again!");
            }
        }
        scanner.close();
        return -1;
    }

    public static int readOperation() {
        Scanner scanner = new Scanner(System.in);
        boolean checkInput = true;
        while (checkInput) {
            String number = scanner.nextLine();
            try {
                String add = "+";
                String substract = "-";
                String multiply = "*";
                if (number.equals(add)) {
                    return 0;
                } else if (number.equals(substract)) {
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
        System.out.println("Write the first number:");
        long firstNumber = readUserInput();
        System.out.println("Write the second number:");
        long secondNumber = readUserInput();
        System.out.println("Your the first number: " + firstNumber + "\nYour the second number: " + secondNumber);
        System.out.println("What operation do you want to do? ( '   +   '   -   '   *   ')");
        int check = readOperation();
        switch (check) {
            case 0:
                long resultAdd = firstNumber + secondNumber;
                System.out.println("Result: " + resultAdd);
                break;
            case 1:
                long resultSubstract = firstNumber - secondNumber;
                System.out.println("Result: " + resultSubstract);
                break;
            case 2:
                long resultMultiply = firstNumber * secondNumber;
                System.out.println("Result: " + resultMultiply);
        }
    }
}