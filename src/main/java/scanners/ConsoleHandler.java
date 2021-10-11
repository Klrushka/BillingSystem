package scanners;

import java.util.Scanner;

public class ConsoleHandler {

    private static Scanner scanner = new Scanner(System.in);



    public static float readPrice(){

        System.out.println("Please enter price");

        float price = scanner.nextFloat();
        scanner.nextLine();

        return price;
    }

    public static String readName(){

        System.out.println("Please enter name");

        String name = scanner.nextLine();

        return name;
    }
    public static int readAmount(){

        System.out.println("Please enter amount");

        int amount = scanner.nextInt();

        scanner.nextLine();
        return amount;
    }



}
