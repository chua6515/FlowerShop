/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author use
 */
public class Invoice {
        
    private String corpName;
    private String date;
    private String flowerName;
    private int quantity;
    private double price;
    private double amount;

    public Invoice(String corpName, String date, String flowerName, int quantity, double price) {
        this.corpName = corpName;
        this.date = date;
        this.flowerName = flowerName;
        this.quantity = quantity;
        this.price = price;
    }

    public String getCorpName() {
        return corpName;
    }

    public void setCorpName(String corpName) {
        this.corpName = corpName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFlowerName() {
        return flowerName;
    }

    public void setFlowerName(String flowerName) {
        this.flowerName = flowerName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getAmount() {
        amount = quantity * price;
        return amount;
    }

    @Override
    public String toString() {
        return corpName;
    }

}
