/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;


import ADT.SortedListInterface;
import model.Product;
import model.ProductType;
import util.ControlScanner;

/**
 *
 * @author QinYing
 */
public class CatalogMaintenance {
    
    private SortedListInterface<Product> itemList;
    private SortedListInterface<ProductType> ptypeList;
    private SortedListInterface<Product> catalogList[];
    
    private ProductModule productModule;
    private ProductTypeModule productTypeModule;
    private ProductCatalogModule productcatalogModule;
    private final ControlScanner input;

    public CatalogMaintenance(SortedListInterface<Product> itemList, SortedListInterface<ProductType> ptypeList, SortedListInterface<Product> catalogList[]) {
        this.itemList = itemList;
        this.ptypeList = ptypeList;
        this.catalogList = catalogList;
        input = new ControlScanner();
                
        productTypeModule = new ProductTypeModule(ptypeList);
        productModule = new ProductModule(itemList, ptypeList);
        productcatalogModule = new ProductCatalogModule(catalogList, itemList);
    }
    
    public void select() {
        int choice = -1;
        while (choice != 0) {
            displayMenu();
            choice = input.nextInt("      Selection: ", "Invalid input. Selection again.", 0, 3);
            switch (choice) {
                case 1:
                    productTypeModule.select();
                    break;
                case 2:
                    productModule.select();
                    break;
                case 3:
                    productcatalogModule.select();
                    break;
            }
        }
    }

    private void displayMenu() {
        System.out.println("");
        for(int i=1; i<=itemList.getLength(); i++) {
            Product quantity = itemList.getEntry(i);
            if(quantity.getQuantity() < 5 && quantity.getQuantity() >= 1) {
                System.out.println("      " + "**" + " " + quantity.getName() + " is less in stock! Which is " + quantity.getQuantity() + " " + "items left.");
            } else if(quantity.getQuantity() == 0) {
                System.out.println("      " + "**" + " " + quantity.getName() + " is out of stock! Which is " + quantity.getQuantity() + " " + "item left.");
            } 
        }
        System.out.println("");
        System.out.println("          +----------------------------------+");
        System.out.println("          |    1. Product Type Information   |");
        System.out.println("          |    2. Product Information        |");
        System.out.println("          |    3. Promotion Catalog          |");
        System.out.println("          |    0. Back                       |");
        System.out.println("          +----------------------------------+");
        System.out.println();
        
    }
    
    
}
