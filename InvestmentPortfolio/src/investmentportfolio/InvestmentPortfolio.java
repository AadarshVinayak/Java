/*
 * Program created by Aadarsh Vinayak
 * avinayak@uoguelph.ca
 * 0978669
 */
package investmentportfolio;

import java.util.Scanner;

/**
 *
 * @author Aadarsh Vinayak
 */
public class InvestmentPortfolio {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Boolean stillRunning = true;
        Scanner getTask = new Scanner(System.in);
        String task;

        System.out.println("This is your investement portfolio");

        Portfolio p = new Portfolio();

        p.readFile();
        while (stillRunning) {
            System.out.println("You can either \n\"Buy\": Buy a new stock/ Mutual Fund.\n\"Sell\": Sell a certain quantity of stocks/mutual fund that you have.\n\"Update\": Update the price on a stock/ mutual fund.\n\"Gain\": Get the total worth of your portfolio.\n\"Search\": Search for any specific stock/ mutual fund you have.\n\"Quit\": to exit the program\n");

            task = getTask.nextLine();

            if (task.equalsIgnoreCase("buy")) {
                p.buy();
            } else if (task.equalsIgnoreCase("sell")) {
                p.sell();
            } else if (task.equalsIgnoreCase("update")) {
                p.update();
            } else if (task.equalsIgnoreCase("gain")) {
                p.getGain();
            } else if (task.equalsIgnoreCase("search")) {
                p.search();
            } else if (task.equalsIgnoreCase("quit") || task.equalsIgnoreCase("q")) {
                stillRunning = false;
                System.out.println("Goodbye!");
                p.writeToFile();
            } else {
                System.out.println("Invalid input, please try again!");
            }
        }
    }

}
