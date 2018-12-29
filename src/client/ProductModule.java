/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import ADT.SortedListInterface;
import java.util.Iterator;
import model.Product;
import model.ProductType;
import util.ControlScanner;
import util.OrderByClause;

/**
 *
 * @author QinYing
 */
public class ProductModule {
    private SortedListInterface<Product> itemList;
    private SortedListInterface<ProductType> ptypeList;
    
    private final ControlScanner input;
    private static final String INVALID_CHOICE = "      Invalid input.";
    
    public ProductModule(SortedListInterface<Product> itemList, SortedListInterface<ProductType> ptypeList) {
        this.itemList = itemList;
        this.ptypeList = ptypeList;
        input = new ControlScanner();
        itemList.orderBy((o1, o2) -> o1.getPtype().getName().compareToIgnoreCase(o2.getPtype().getName()) < 0 ? OrderByClause.MOVE_TO_FRONT
                : OrderByClause.MOVE_TO_BACK);
    }
    
    public void displayMenu() {
        System.out.println("");
        System.out.println("          +----------------------------------+");
        System.out.println("          |        Product Maintanance       |");
        System.out.println("          |----------------------------------|");
        System.out.println("          |       1. View Product List       |");
        System.out.println("          |       2. Add Product             |");
        System.out.println("          |       3. Update Product          |");
        System.out.println("          |       4. Delete Product          |");
        System.out.println("          |       0. Exit                    |");
        System.out.println("          +----------------------------------+");
        System.out.println("");
    }
    
    public void select() {
        if (ptypeList.isEmpty()) {
            System.out.println("      Please add some product types before you add products");
        } else {
            int choice = -1;
            while (choice != 0) {
                displayMenu();
                choice = input.nextInt("      Selection: ", INVALID_CHOICE, 0, 4);
                switch (choice) {
                    case 1:
                        viewList();
                        break;
                    case 2:
                        addProduct();
                        break;
                    case 3:
                        updateProduct();
                        break;
                    case 4:
                        deleteProduct();
                        break;
                }
            }
        }
    }
    
    public void addProduct() {
        String id = input.nextLine("      Enter Product ID(eg. P0001): ");
        Product p = itemList.firstOrDefault(o -> o.getId().equalsIgnoreCase(id));
        if (p == null) {
            System.out.println("");
            System.out.println("      Product List-->");
            displayProductTypes();
            int index = input.nextInt("      Enter Product Type         : ", "Invalid input. Enter again.", 1, ptypeList.getLength());
            String name = input.nextLine("      Enter Product Name         : ");
            String description = input.nextLine("      Enter Product Description  : ");
            double price = input.nextDouble("      Enter Product Price        : ", "", 0, Double.MAX_VALUE);
            int quantity = input.nextInt("      Enter Product Quantity     : ", "", 0, Integer.MAX_VALUE);
            Product newProduct = new Product(id, ptypeList.get(index - 1), name, description, price, quantity);
            itemList.add(newProduct);
            arrangement(itemList);
            System.out.println("");
            System.out.println("      *--------------------------------------*");
            System.out.println("      |   New Product Successfully Added!!!  |");
            System.out.println("      *--------------------------------------*");
        } else {
            System.out.println("      Duplicted Product ID.");
        }
    }
    public void updateProduct() {
        if (itemList.isEmpty()) {
            System.out.println("      **  Product list is EMPTY");
        } else {
            showList(itemList);
            String id = input.nextLine("      Enter the product id of the product you want to update: ");
            Product toUpdate = itemList.firstOrDefault(o -> o.getId().equalsIgnoreCase(id));
            if (toUpdate == null) {
                System.out.println("      Product not found.");
            } else {
                System.out.println("");
                System.out.println("      Product Details-->");
                System.out.println(String.format("      %3d. %s", 1, toUpdate));
                if (input.getConfirmation("      Are you sure want to update? (Y/N): ")) {
                    System.out.println("      Product Type List-->");
                    displayProductTypes();
                    int ptIndex = input.nextInt("      Enter Product Type         : ", "Invalid input. Enter again.", 1, ptypeList.getLength());
                    toUpdate.setPtype(ptypeList.get(--ptIndex));
                    toUpdate.setName(input.nextLine("      Enter Product Name         : "));
                    toUpdate.setDescription(input.nextLine("      Enter Product Description  : "));
                    toUpdate.setPrice(input.nextDouble("      Enter Product Price        : ", "", 0, Double.MAX_VALUE));
                    toUpdate.setQuantity(input.nextInt("      Enter Product Quantity     : ", "", 0, Integer.MAX_VALUE));
                    arrangement(itemList);
                    System.out.println("      *-------------------------------------*");
                    System.out.println("      |    Product Successfully Updated!!!  |");
                    System.out.println("      *-------------------------------------*");
                } else {
                    System.out.println("      Update has been cancel");
                }
            }
        }
    }
    public void deleteProduct() {
        if (itemList.isEmpty()) {
            System.out.println("      **  Product list is EMPTY");
        } else {
            showList(itemList);
            String id = input.nextLine("      Enter the product id of the product you want to delete: ");
            Product toRemove = itemList.firstOrDefault(o -> o.getId().equalsIgnoreCase(id));
            if (toRemove == null) {
                System.out.println("      Product not found.");
            } else {
                System.out.println("");
                System.out.println("      Product Details-->");
                System.out.println(String.format("      %3d. %s", 1, toRemove));
                if (input.getConfirmation("      Are you sure want to delete? (Y/N): ")) {
                    itemList.remove(toRemove);
                    System.out.println("      *-------------------------------------*");
                    System.out.println("      |    Product Successfully Daleted!!!  |");
                    System.out.println("      *-------------------------------------*");
                    System.out.println("");
                } else {
                    System.out.println("      Delete has been cancel");
                }
            }
        }
    }
    
    private void displayProductTypes() {
        Iterator<ProductType> ptIterator = ptypeList.getIterator();
        for (int i = 0; ptIterator.hasNext(); i++) {
            System.out.println(String.format("      %3d. %s", (i + 1), ptIterator.next()));
        }
    }
    
    private void showList(SortedListInterface<Product> itemList) {
        Iterator<Product> i = itemList.getIterator();
        int index = 0;
        String prevType = "";
        while (i.hasNext()) {
            Product p = i.next();
            if (index == 0 || !prevType.equalsIgnoreCase(p.getPtype().getName())) {
                if (index > 0) {
                    System.out.println("      *------------------------------------------------------------------------------------------------------------------------------------------*");
                    System.out.println("");
                }
                System.out.println(p.getPtype().getName().toUpperCase());
                System.out.println("        No.\tProduct ID\tType ID\t\tType Name\t\t\tName\t\tDescription\t\tPrice(RM)\tQuantity");
                System.out.println("      *------------------------------------------------------------------------------------------------------------------------------------------*");
                prevType = p.getPtype().getName();
            }
            System.out.println(String.format("      %3d. %s", (index + 1), p));
            index++;
        }
    }
    
    private void arrangement(SortedListInterface<Product> itemsList) {
        itemsList.orderBy((o1, o2) -> o1.getPtype().getName().compareToIgnoreCase(o2.getPtype().getName()) < 0 ? OrderByClause.MOVE_TO_FRONT
                : OrderByClause.MOVE_TO_BACK);
    }
    
    private void viewList() {
        if (itemList.isEmpty()) {
            System.out.println("      **  Product list is EMPTY");
        } else {
            showList(itemList);
        }
    }
}
