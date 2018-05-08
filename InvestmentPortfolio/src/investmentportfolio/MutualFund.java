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
public class MutualFund extends Investment{

    public static final double FEE = 45.00;
    /*private String symbol;
    private String name;
    private int quantity;
    private double price;
    private double bookValue;*/

    /**
     *
     * @param type
     * @param symbol
     * @param name
     * @param quantity
     * @param price
     */
    public MutualFund(String type, String symbol, String name, int quantity, double price) {
        this.type = type;
        this.symbol = symbol;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.bookValue = (double) this.quantity * this.price;
    }


    public void addQuantity(int quantity) {
        bookValue += quantity * price;
        this.quantity += quantity;
    }

    public void subQuantity(int quantity) {
        this.bookValue *= ((double) quantity / (double) this.quantity);
        this.quantity -= quantity;
    }

    public double calculateGains() {
        return (quantity * price - FEE) - bookValue;
    }

    public void sell(int quantitySold) {
        System.out.println((quantitySold * this.price - FEE));
    }


}
