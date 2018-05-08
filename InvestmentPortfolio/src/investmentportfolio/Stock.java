/*
 * Program created by Aadarsh Vinayak
 * avinayak@uoguelph.ca
 * 0978669
 */
package investmentportfolio;

/**
 *
 * @author Aadarsh Vinayak
 */
public class Stock extends Investment {

    public static final double COMMISSION = 9.99;
/*    private String symbol;
    private String name;
    private int quantity;
    private double price;
    private double bookValue; */

    public Stock(String type, String symbol, String name, int quantity, double price) {
        this.type = type;
        this.symbol = symbol;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.bookValue = (double) this.quantity * this.price + COMMISSION;
    }


    public void addQuantity(int quantity) {
        bookValue += quantity * price + COMMISSION;
        this.quantity += quantity;
    }

    public void subQuantity(int quantity) {
        this.bookValue *= ((double) quantity / (double) this.quantity);
        this.quantity -= quantity;
    }

    public double calculateGains() {
        return (quantity * price - COMMISSION) - bookValue;
    }

    public void sell(int quantitySold) {
        System.out.println((quantitySold * this.price - COMMISSION));
    }
}
