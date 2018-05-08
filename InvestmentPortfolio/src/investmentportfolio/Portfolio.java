/*
 * Program created by Aadarsh Vinayak
 * avinayak@uoguelph.ca
 * 0978669
 */
package investmentportfolio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Aadarsh Vinayak
 */
public class Portfolio {

    /* ArrayList<Stock> stocks = new ArrayList<Stock>();
    ArrayList<MutualFund> mutualFunds = new ArrayList<MutualFund>();*/
    ArrayList<Investment> investments = new ArrayList();
    HashMap<String, ArrayList<Integer>> hash = new HashMap<String, ArrayList<Integer>>();

    public void getHashValues() {

        String toHash;
        for (int i = 0; i < investments.size(); i++) {
            StringTokenizer st = new StringTokenizer(investments.get(i).getName());
            while (st.hasMoreTokens()) {
                toHash = st.nextToken();
                if (hash.containsKey(toHash)) {
                    hash.get(toHash).add((Integer) i);
                } else {
                    hash.put(toHash, new ArrayList<Integer>());
                    hash.get(toHash).add((Integer) i);
                }
            }
        }
    }

    public void buy() {
        Scanner s = new Scanner(System.in);
        String symbol = "";
        while (symbol.isEmpty()) {
            System.out.print("Please enter the symbol of the company your trying to buy: ");
            symbol = s.nextLine();
        }
        int index = searchSymbol(symbol);
        int quantity = 0;
        double price = 0;
        Boolean done = true;
        if (index >= 0) {
            while (done) {
                try {
                    System.out.print("What is the price of the stock/ mutual fund you just bought? ");
                    price = s.nextDouble();
                    done = false;
                } catch (Exception e) {
                    s.next();
                    System.out.println("Please enter only numbers.");
                }
            }

            done = true;
            while (done) {
                try {
                    System.out.print("What is the quantity of the stock/ mutual fund you just bought? ");
                    quantity = s.nextInt();
                    done = false;
                } catch (Exception e) {
                    s.next();
                    System.out.println("Please enter only numbers.");
                }
            }

            investments.get(index).setPrice(price);
            if (investments.get(index).getType().equalsIgnoreCase("Stock")) {
                ((Stock) investments.get(index)).addQuantity(quantity);
            } else {
                ((MutualFund) investments.get(index)).addQuantity(quantity);
            }
        } else {
            Boolean getInputAgain = true;
            String bought = "";
            while (getInputAgain) {
                System.out.print("Would you like a Stock (\"Stock\")or mutual fund (\"Mutual\"): ");
                bought = s.nextLine();
                if (bought.equalsIgnoreCase("stock") || bought.equalsIgnoreCase("mutual")) {
                    getInputAgain = false;
                }
            }
            String name = "";
            //get name
            while (name.isEmpty()) {
                System.out.print("Enter the name of the stock: ");
                name = s.nextLine();
            }
            //get price
            Boolean cont = true;
            while (cont) {
                try {
                    System.out.print("Enter the price of the stock: ");
                    price = s.nextDouble();
                    cont = false;
                } catch (Exception e) {
                    s.next();
                    System.out.println("Please enter only numbers.");
                }
            }
            //get quantity
            cont = true;
            while (cont) {
                try {
                    System.out.print("Enter the quantity you want to purchase: ");
                    quantity = s.nextInt();
                    cont = false;
                } catch (Exception e) {
                    s.next();
                    System.out.println("Please enter only numbers.");
                }
            }
            String toHash;
            if (bought.equalsIgnoreCase("stock")) {
                Investment i = new Stock(bought, symbol, name, quantity, price);
                investments.add(i);
                StringTokenizer st = new StringTokenizer(i.getName());
                while (st.hasMoreTokens()) {
                    toHash = st.nextToken();
                    if (hash.containsKey(toHash)) {
                        hash.get(toHash).add((Integer) investments.size() - 1);
                    } else {
                        hash.put(toHash, new ArrayList<Integer>());
                        hash.get(toHash).add((Integer) investments.size() - 1);
                    }
                }

            } else {
                Investment i = new MutualFund(bought, symbol, name, quantity, price);
                investments.add(i);
                StringTokenizer st = new StringTokenizer(i.getName());
                while (st.hasMoreTokens()) {
                    toHash = st.nextToken();
                    if (hash.containsKey(toHash)) {
                        hash.get(toHash).add((Integer) investments.size() - 1);
                    } else {
                        hash.put(toHash, new ArrayList<Integer>());
                        hash.get(toHash).add((Integer) investments.size() - 1);
                    }
                }
            }

        }

    }

    public void sell() {
        Scanner s = new Scanner(System.in);
        String symbol = "";
        while (symbol.isEmpty()) {
            System.out.print("Please enter the symbol of the company your trying to sell: ");
            symbol = s.nextLine();
        }
        int index = searchSymbol(symbol);
        int quantity = 0;
        double price = 0;
        if (index >= 0) {
            //get price
            Boolean cont = true;
            while (cont) {
                try {
                    System.out.print("What is the price of the stock/ mutual fund you just sold?  ");
                    price = s.nextDouble();
                    cont = false;
                } catch (Exception e) {
                    s.next();
                    System.out.println("Please enter only numbers.");
                }
            }

            cont = true;
            while (cont) {
                try {
                    System.out.print("What is the quantity of the stock/ mutual fund you just sold? ");
                    quantity = s.nextInt();
                    cont = false;
                } catch (Exception e) {
                    s.next();
                    System.out.println("Please enter only numbers.");
                }
            }

            if (investments.get(index).getType().equalsIgnoreCase("Stock")) {
                investments.get(index).setPrice(price);
                if (investments.get(index).getQuantity() > quantity) {
                    ((Stock) investments.get(index)).subQuantity(quantity);
                } else {
                    System.out.println("You cant sell more then you have!");
                }
                ((Stock) investments.get(index)).sell(quantity);
                if(investments.get(index).getQuantity() == 0)investments.remove(index);
            } else {
                investments.get(index).setPrice(price);
                if (investments.get(index).getQuantity() > quantity) {
                    ((MutualFund) investments.get(index)).subQuantity(quantity);
                } else {
                    System.out.println("You cant sell more then you have!");
                }
                ((MutualFund) investments.get(index)).sell(quantity);
                if(investments.get(index).getQuantity() == 0)investments.remove(index);
            }
        } else {
            System.out.println("You dont have a stock/ mutual fund with that symbol!");
        }
    }

    public void update() {
        Scanner s = new Scanner(System.in);
        String answer = "";
        double price = 0;
        for (int i = 0; i < investments.size(); i++) {
            if (investments.get(i).getType().equalsIgnoreCase("Stock")) {
                investments.get(i).print();
                Boolean input = true;
                while (input) {
                    System.out.println("Would you like to change the price of this stock? ('Y', 'N')");
                    answer = s.nextLine();
                    if (answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("n") || answer.equalsIgnoreCase("yes") || answer.equalsIgnoreCase("no")) {
                        input = false;
                    }
                }
                if (answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("yes")) {
                    Boolean cont = true;
                    while (cont) {
                        try {
                            System.out.print("What is the new price of the stock? ");
                            price = s.nextDouble();
                            cont = false;
                        } catch (Exception e) {
                            s.next();
                            System.out.println("Please enter only numbers.");
                        }
                    }
                    investments.get(i).setPrice(price);
                }
            } else {
                investments.get(i).print();
                Boolean input = true;
                while (input) {
                    System.out.println("Would you like to change the price of this mutual fund? ('Y', 'N')");
                    answer = s.nextLine();
                    if (answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("n") || answer.equalsIgnoreCase("yes") || answer.equalsIgnoreCase("no")) {
                        input = false;
                    }
                }
                if (answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("yes")) {
                    Boolean cont = true;
                    while (cont) {
                        try {
                            System.out.print("What is the new price of the stock? ");
                            price = s.nextDouble();
                            cont = false;
                        } catch (Exception e) {
                            s.next();
                            System.out.println("Please enter only numbers.");
                        }
                    }
                    investments.get(i).setPrice(price);
                }
            }
        }
    }

    public void getGain() {
        double total = 0;
        for (int i = 0; i < investments.size(); i++) {
            if (investments.get(i).getType().equalsIgnoreCase("Stock")) {
                Stock s = (Stock) investments.get(i);
                total += s.calculateGains();
            } else {
                MutualFund m = (MutualFund) investments.get(i);
                total += m.calculateGains();
            }
        }
        System.out.println("Total gains is " + total);
    }

    public void search() {
        Scanner s = new Scanner(System.in);
        int[][] x = new int[3][investments.size()];
        int[] master = new int[3];
        Boolean input = true;
        String answer = "";
        String symbol = "";
        String name = "";

        //getting The symbol from the user          
        while (input) {
            System.out.println("Would you like to search with the companies symbol? ('Y', 'N')");
            answer = s.nextLine();
            if (answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("n") || answer.equalsIgnoreCase("yes") || answer.equalsIgnoreCase("no")) {
                input = false;
            }
        }
        if (answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("yes")) {
            master[0] = 1;
            while (symbol.isEmpty()) {
                System.out.print("Please enter the symbol of the company your trying to find: ");
                symbol = s.nextLine();
            }
            //Search which elements have the symbol
            for (int j = 0; j < investments.size(); j++) {
                if (investments.get(j).getSymbol().equals(symbol)) {
                    x[0][j] = 1;
                }
            }
        }

        //Getting the string from the user
        input = true;
        while (input) {
            System.out.println("Would you like to search with key words? ('Y', 'N')");
            answer = s.nextLine();
            if (answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("n") || answer.equalsIgnoreCase("yes") || answer.equalsIgnoreCase("no")) {
                input = false;
            }
        }
        if (answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("yes")) {
            master[1] = 1;
            while (name.isEmpty()) {
                System.out.print("Enter the keyword of the stock: ");
                name = s.nextLine();
            }

            //Checking if they are key words in a string
            for (int j = 0; j < investments.size(); j++) {
                String[] toCheck = investments.get(j).getName().split(" ");
                List<String> check = Arrays.asList(toCheck);
                String[] keys = name.split(" ");
                int counter = 0;
                for (int k = 0; k < keys.length; k++) {
                    if (check.contains(keys[k])) {
                        counter += 1;
                    }
                }
                if (counter == keys.length) {
                    x[1][j] = 1;
                }
            }
        }

        //Getting the min and max values of the money  
        input = true;
        while (input) {
            System.out.println("Would you like to search with a range of prices? ('Y', 'N')");
            answer = s.nextLine();
            if (answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("n") || answer.equalsIgnoreCase("yes") || answer.equalsIgnoreCase("no")) {
                input = false;
            }
        }
        if (answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("yes")) {
            master[2] = 1;
            double min = 0;
            double max = 0;
            input = true;
            while (input) {
                try {
                    System.out.print("What is the minimum price? ");
                    min = s.nextDouble();
                    input = false;
                } catch (Exception e) {
                    s.next();
                    System.out.println("Please enter only numbers.");
                }
            }
            input = true;
            while (input) {
                try {
                    System.out.print("What is the minimum price? ");
                    max = s.nextDouble();
                    input = false;
                } catch (Exception e) {
                    s.next();
                    System.out.println("Please enter only numbers.");
                }
            }

            //Search if elements have the price range
            for (int j = 0; j < investments.size(); j++) {
                if (investments.get(j).getPrice() > min && investments.get(j).getPrice() < max) {
                    x[2][j] = 1;
                }
            }
        }

        //printing all the elements that match master
        if (!(master[0] == 0 && master[1] == 0 && master[2] == 0)) {
            for (int j = 0; j < investments.size(); j++) {
                if (x[0][j] == master[0] && x[1][j] == master[1] && x[2][j] == master[2]) {
                    investments.get(j).print();
                }
            }
        }
    }

    public void readFile() {
        Scanner s = null;
        PrintWriter writer = null;
        String string;
        String temp = null;
        String type = null;
        String name = null;
        String symbol = null;
        int quantity = 0;
        double price = 0;
        double bookValue = 0;
        int add = 0;
        try {
            s = new Scanner(new FileInputStream(".\\.\\Assets\\investments.txt"));
            while (s.hasNextLine()) {
                string = s.nextLine();
                if (!string.isEmpty()) {
                    boolean toAdd = false;
                    temp = "";
                    for (int i = 0; i < string.length(); i++) {
                        if (string.charAt(i) == '=') {
                            toAdd = true;
                        }

                        if (toAdd == true) {
                            temp += string.charAt(i);
                        }
                    }
                    temp = temp.replaceAll("= ", "");
                    temp = temp.substring(1, temp.length() - 1);
                    if (type == null) {
                        type = temp;
                        temp = "";
                    } else if (symbol == null) {
                        symbol = temp;
                        temp = "";
                    } else if (name == null) {
                        name = temp;
                        temp = "";
                    } else if (quantity == 0) {
                        quantity = Integer.parseInt(temp);
                        temp = "";
                    } else if (price == 0) {
                        price = Double.parseDouble(temp);
                        temp = "";
                    } else if (bookValue == 0) {
                        bookValue = Double.parseDouble(temp);
                        temp = "";
                        add = 1;
                    }
                    if (add == 1) {
                        if (type.equalsIgnoreCase("Stock")) {
                            Investment i = new Stock(type, symbol, name, quantity, price);
                            i.setBookValue(bookValue);
                            investments.add(i);

                        } else {
                            Investment i = new MutualFund(type, symbol, name, quantity, price);
                            i.setBookValue(bookValue);
                            investments.add(i);
                        }
                        type = null;
                        symbol = null;
                        name = null;
                        quantity = 0;
                        price = 0;
                        bookValue = 0;
                        add = 0;
                    }
                }
            }
                    s.close();
        } catch (FileNotFoundException e) {
        }


    }

    public void writeToFile() {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new FileOutputStream(".\\.\\Assets\\investments.txt"));
        } catch (Exception e) {
            System.out.println("File was created");
        }
        for (int i = 0; i < investments.size(); i++) {
            writer.println("type = \"" + investments.get(i).getType() + "\"");
            writer.println("symbol = \"" + investments.get(i).getSymbol() + "\"");
            writer.println("name = \"" + investments.get(i).getName() + "\"");
            writer.println("quantity = \"" + investments.get(i).getQuantity() + "\"");
            writer.println("price = \"" + investments.get(i).getPrice() + "\"");
            writer.println("bookValue = \"" + investments.get(i).getBookValue() + "\"");
            writer.println();
        }
        writer.close();
    }

    private int searchSymbol(String toFind) {
        for (int i = 0; i < investments.size(); i++) {
            if (investments.get(i).getSymbol().equalsIgnoreCase(toFind)) {
                return i;
            }
        }
        return -1;
    }
}
