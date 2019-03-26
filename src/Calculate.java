import java.util.Scanner;

public class Calculate {

    public static long readUserInput() {

        Scanner scanner = new Scanner(System.in);
        boolean checkInput = true;
        while (checkInput) {
          String   number = scanner.nextLine();
            try {
                long xxx = Long.parseLong(number);
                if (xxx >= 0) {
                    checkInput=false;
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

    public static void main(String[] args) {
        System.out.println("Write the first number:");
        long firstNumber = readUserInput();
        System.out.println("Write the second number:");
        long secondNumber=readUserInput();
        System.out.println("Your the first number: "+firstNumber + "\nYour the second number: "+secondNumber);
        System.out.println("What operation do you want to do?");



    }
}
