/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
* https://www.google.com/settings/security/lesssecureapps
 */
package fioreflowershop_;

import ADT.SortedList;
import ADT.SortedListInterface;
import client.CatalogMaintenance;
import java.util.Random;
import java.util.Scanner;
import model.Product;
import model.ProductType;
import util.ControlScanner;

import ADT.Linked;
import ADT.LinkedInterface;
import customerInvoice.CustomerAndInvoiceMaintenance;
import model.Consumer;
import model.CorporateCustomer;
import model.Person;
import model.Invoice;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.internet.MimeMessage;


/**
 *
 * @author QinYing
 */
public class FioreFlowerShop_ {

    private CustomerAndInvoiceMaintenance customerAndInvoice;
    private CatalogMaintenance catalogMaintenance;
    private SortedListInterface<Product> itemList;
    private SortedListInterface<ProductType> ptypeList;
    private SortedListInterface<Product> catalogList[];
    
    private ControlScanner input;
    
    public static void main(String[] args) {
        
        
        new FioreFlowerShop_().select();
    }
    
    private void displayMenu() {
        
        
        System.out.println("");
        System.out.println("          --------------Welcome to Fiore Flower Shop-------------");
        System.out.println("");
        System.out.println("          +-----------------------------------------------------+");
        System.out.println("          |                      Main Menu                      |");
        System.out.println("          |-----------------------------------------------------|");
        System.out.println("          |      1. Catalog Maintenance                         |");
        System.out.println("          |      2. Catalog Orders                              |");
        System.out.println("          |      3. Customer Maintenance and Invoice Payment    |");
        System.out.println("          |      4. Customize Floral Arrangement                |");
        System.out.println("          |      0. Exit                                        |");
        System.out.println("          +-----------------------------------------------------+");
        System.out.println("");
    }
    
    public void select() {
        //liew meng part----------------------------------------------------
        LinkedInterface<CorporateCustomer> corporateCustomer = new Linked<>();
        LinkedInterface<Invoice> invoice = new Linked<>();
        LinkedInterface<Consumer> consumer = new Linked<>();
        
        consumer.add(new Consumer("Ang Wei Kit", "no8,pv15, 34000 Taiping, Perak", "085569987","liewmengchua@gmail.com"));
        
        corporateCustomer.add(new CorporateCustomer("Jy Art.Sdn.Bhd.","Chua Liew Meng", "144, Lorong 12, Taman Sentosa, 34000 Taiping, Perak", "085569987","liewmengchua@gmail.com", 1400.00));
        corporateCustomer.add(new CorporateCustomer("Johnson Florist","Chong Chan Phang", "8, Jalan Pangung Wayang, 34000 Taiping, Perak", "082456693","liewmengchua@gmail.com", 2000.00));
        
        invoice.add(new Invoice("Jy Art.Sdn.Bhd.", "1/12/2018","Rose", 15, 10.00 )); 
        invoice.add(new Invoice("Johnson Florist", "1/12/2018","Allium", 8, 12.00 ));
        int userSelection;
        //----------------------------------------------------------------
        
        int choice = -1;
        while (choice != 0) {
            displayMenu();
            choice = input.nextInt("      Selection: ", "Invalid selection!", 0, 4);
            switch (choice) {
                case 1:
                    catalogMaintenance.select();
                    break;
                case 2:
                    break;
                case 3:
                    customerAndInvoice.CustomerAndInvoiceMenu(corporateCustomer,invoice);
                    break;
                case 4:
                    break;
            }
        }
    }
    
    private void CatalogModule() {
        ptypeList = new SortedList<>();
        insertProductTypes();
        itemList = new SortedList<>();
        insertProduct();
        catalogList = new SortedList[12];
        for (int i = 0; i < catalogList.length; i++) {
            catalogList[i] = new SortedList<>();
        }
        catalogMaintenance = new CatalogMaintenance(itemList, ptypeList, catalogList);
    }
    
    private void insertProductTypes() {
        ptypeList.add(new ProductType("T0001", "Fresh Flower"));
        ptypeList.add(new ProductType("T0002", "Bouquet"));
        ptypeList.add(new ProductType("T0003", "Floral Arrangement"));
    }
    
    private void insertProduct() {
        itemList.add(new Product("P0001", ptypeList.get(rand(ptypeList.getLength())), "Sunflower", "Happy", 11.11, 0));
        itemList.add(new Product("P0002", ptypeList.get(rand(ptypeList.getLength())), "Rose", "Happy", 13, 100));
        itemList.add(new Product("P0003", ptypeList.get(rand(ptypeList.getLength())), "Lavendar", "Happy", 23, 423));
        itemList.add(new Product("P0004", ptypeList.get(rand(ptypeList.getLength())), "Marigold", "Happy", 15, 3));
        itemList.add(new Product("P0005", ptypeList.get(rand(ptypeList.getLength())), "Moonflower", "Happy", 8, 100));
        itemList.add(new Product("P0006", ptypeList.get(rand(ptypeList.getLength())), "Orchid", "Happy", 2.5, 85));
        itemList.add(new Product("P0007", ptypeList.get(rand(ptypeList.getLength())), "Chrysanthemum", "Happy", 1.0, 320));
        itemList.add(new Product("P0008", ptypeList.get(rand(ptypeList.getLength())), "Catmint", "Happy", 10, 30));
        itemList.add(new Product("P0009", ptypeList.get(rand(ptypeList.getLength())), "Bellflower", "Happy", 12.12, 10));
    }
    
    public FioreFlowerShop_() {
        input = new ControlScanner();
        CatalogModule();
    }
    
    private static int rand(int bound) {
        return new Random().nextInt(bound);
    }
}
