/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import ADT.SortedListInterface;
import java.util.Iterator;
import model.ProductType;
import util.ControlScanner;

/**
 *
 * @author QinYing
 */
public class ProductTypeModule {
    
    private SortedListInterface<ProductType> ptypeList;
    private final ControlScanner input;
    private static final String INVALID_CHOICE = "      Invalid input.";
    
    public ProductTypeModule(SortedListInterface<ProductType> ptypeList) {
        this.ptypeList = ptypeList;
        input = new ControlScanner();
    }
    
    public void select() {
        int choice = -1;
        while (choice != 0) {
            displayMenu();
            choice = input.nextInt("      Selection: ", INVALID_CHOICE, 0, 4);
            switch (choice) {
                case 1:
                    viewList();
                    break;
                case 2:
                    addProductType();
                    break;
                case 3:
                    updateProductType();
                    break;
                case 4:
                    deleteProductType();
                    break;
                case 0:
                    break;
            }
        }
    }
    
    private void displayMenu() {
        System.out.println("");
        System.out.println("          +-------------------------------------+");
        System.out.println("          |       Product Type Maintanance      |");
        System.out.println("          |-------------------------------------|");
        System.out.println("          |       1. View Product Type          |");
        System.out.println("          |       2. Add Product Type           |");
        System.out.println("          |       3. Update Product Type        |");
        System.out.println("          |       4. Delete Product Type        |");
        System.out.println("          |       0. Exit                       |");
        System.out.println("          +-------------------------------------+");
        System.out.println("");
    }
    
    private void addProductType() {
        System.out.println("");
        String id = input.nextLine("      Enter Product Type ID(eg. T0001) : ");
        if (ptypeList.where(o -> o.getId().equalsIgnoreCase(id)).isEmpty()) {
            String name = input.nextLine("      Enter Product Type Name          : ");
            ProductType newProduct = new ProductType(id, name);
            ptypeList.add(newProduct);
            System.out.println("");
            System.out.println("      *-------------------------------------------*");
            System.out.println("      |   New Product Type Successfully Added!!!  |");
            System.out.println("      *-------------------------------------------*");
        } else {
            System.out.println("      ERROR: Duplicated ID. Enter again.");
        }
    }
    
    private void updateProductType() {
        if (ptypeList.isEmpty()) {
            System.out.println("      ** Product types list is EMPTY");
        } else {
            System.out.println("");
            System.out.println("        No.\t\tType ID\t\tType Name\t\t");
            System.out.println("      *-------------------------------------------------------*");
            displayList(ptypeList);
            System.out.println("");
            String id = input.nextLine("      Enter the product id of the product you want to update: ");
            ProductType newPtype = ptypeList.firstOrDefault(t -> t.getId().equalsIgnoreCase(id));
            if (newPtype != null) {
                System.out.println("");
                System.out.print("      Product Type Details-->");
                System.out.println(newPtype);
                if (input.getConfirmation("      Are you sure want to update ? (Y/N): ")) {
                    String newName = input.nextLine("      Enter Update Product Type Name : ");
                    newPtype.setName(newName);
                    System.out.println("");
                    System.out.println("      *------------------------------------------*");
                    System.out.println("      |    Product Type Successfully Updated!!!  |");
                    System.out.println("      *------------------------------------------*");
                } else {
                    System.out.println("      Update has been cancel.");
                }
            } else {
                System.out.println("      Product type id not found. Enter again");
            }
        }
    }   
    
    private void deleteProductType() {
        if (ptypeList.isEmpty()) {
            System.out.println("      ** Product types list is EMPTY");
        } else {
            System.out.println("");
            System.out.println("        No.\t\tType ID\t\tType Name\t\t");
            System.out.println("      *-------------------------------------------------------*");
            displayList(ptypeList);
            System.out.println("");
            String id = input.nextLine("      Enter the product id of the product you want to delete: ");
            ProductType type = ptypeList.firstOrDefault(t -> t.getId().equalsIgnoreCase(id));
            if (type != null) {
                System.out.print("      Product Type Details-->    ");
                System.out.println(type);
                if (input.getConfirmation("      Are you sure want to delete ? (Y/N): ")) {
                    ptypeList.remove(type);
                    System.out.println("      *------------------------------------------*");
                    System.out.println("      |    Product Type Successfully Daleted!!!  |");
                    System.out.println("      *------------------------------------------*");
                    System.out.println("");
                } else {
                    System.out.println("      Delete has been cancel.");
                }
            } else {
                System.out.println("      Product type not found. Enter again");
            }
        }
    }
    
    private void viewList() {
        if (ptypeList.isEmpty()) {
            System.out.println("      ** Product types list are EMPTY");
        } else {
            System.out.println("");
            System.out.println("        No.\t\tType ID\t\tType Name\t\t");
            System.out.println("      *-------------------------------------------------------*");
            displayList(ptypeList);
        }
    }
    
    private void displayList(SortedListInterface<ProductType> ptypeList) {
        Iterator<ProductType> i = ptypeList.getIterator();
        int index = 0;
        while (i.hasNext()) {
            ProductType pt = i.next();
            System.out.println(String.format("       %3d.\t%s", (index + 1), pt));
            index++;
        }
    }
}
