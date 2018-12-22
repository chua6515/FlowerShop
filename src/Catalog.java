/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author QinYing
 */
public class Catalog implements Comparable<Catalog> {
    
    private int month;
    private String desc;
    private String status;
    private Product productAssign;

    public Catalog() {
        this.month = 0;
        this.desc = "";
        this.status = "";
        this.productAssign = null;
    }
    
    public Catalog(int month, String desc, String status, Product productAssign) {
        this.month = month;
        this.desc = desc;
        this.status = "Pending/Not assigned";
        this.productAssign = productAssign;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Product getProductAssign() {
        return productAssign;
    }

    public void setProductAssign(Product productAssign) {
        this.productAssign = productAssign;
    }
    
    
    @Override
    public int compareTo(Catalog t) {
        int months = t.getMonth();
        return months - this.getMonth();
    }

    @Override
    public String toString() {
        return ("[" + this.getStatus() + "] Month : " + this.getMonth());
    }
    
    public String taskDetails() {
        String msg = this.toString() + "\n"
                + "Month : " + this.getMonth() + "\n"
                + "Desciption : " + this.getDesc() + "\n";
        
        if (this.getProductAssign() != null) {
            msg += "Product Type: " + this.getProductAssign().getPtype() + "\n"
                    + "Product Name : " + this.getProductAssign().getName();
        }
        
        return msg;
    }
    
}
