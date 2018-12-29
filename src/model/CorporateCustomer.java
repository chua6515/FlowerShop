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
public class CorporateCustomer extends Person {
         
    private String corpName;
    private double creditLimit;

    public CorporateCustomer(String corpName, String name, String address, String contactNo, String email, double creditLimit) {
        super(name, address, contactNo, email);
        this.corpName = corpName;
        this.creditLimit = creditLimit;
    }

    public String getCorpName() {
        return corpName;
    }

    public void setCorpName(String corpName) {
        this.corpName = corpName;
    }

    public double getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(double creditLimit) {
        this.creditLimit = creditLimit;
    }

    @Override
    public String toString() {
        return "corpName=" + corpName + super.toString() + ", creditLimit=" + creditLimit;
    }

    
}
