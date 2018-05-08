/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package investmentportfolio;

/**
 *
 * @author Aadarsh Vinayak
 */
public class Investment {
    protected String type;
    protected String symbol;
    protected String name;
    protected int quantity;
    protected double price;
    protected double bookValue;
    
    

    public String getSymbol() {
        return this.symbol;
    }
   
    public String getType() {
        return this.type;
    }

    public String getName() {
        return this.name;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public double getPrice() {
        return this.price;
    }

    public double getBookValue() {
        return this.bookValue;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setBookValue(double bookValue) {
        this.bookValue = bookValue;
    }
    
    public void print() {
        System.out.println("Symbol: " + symbol + "\nName: " + name + "\nPrice: $" + price + "\nQuantity: " + quantity + "\nBook Value: " + bookValue);
    }
    
}
