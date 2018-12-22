/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author QinYing
 */
public class Product implements Comparable<Product> {
    
    private String ptype;
    private String name;
    private String description;
    private double price;
    private int quantity;

    public Product() {
        this.ptype = "";
        this.name = "";
        this.description = "";
        this.price = 0;
        this.quantity = 0;
    }

    public Product(String ptype, String name, String description, double price, int quantity) {
        this.ptype = ptype;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }
    
    public String getPtype() {
        return ptype;
    }
    
    public void setPtype(String ptype) {
        this.ptype = ptype;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    @Override
    public int compareTo(Product o) {
        return this.getPtype().toLowerCase().compareTo(o.getPtype().toLowerCase());
    }

    @Override
    public String toString() {
        return this.ptype + " , " + this.name + " , " + this.description + " , " + this.price + " , " + this.quantity;
    }
  
}
