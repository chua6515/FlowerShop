/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import ADT.SortedListInterface;
import java.util.Iterator;
import model.Product;
import util.ControlScanner;
import util.OrderByClause;

/**
 *
 * @author QinYing
 */
public class ProductCatalogModule {
    private SortedListInterface<Product> catalogList[];
    private SortedListInterface<Product> itemList;
    private final ControlScanner input;
    private static final String months[] = {"January", "Febuary", "March", "April", "May", "June", "July", "August", "September",
        "October", "November", "December"};
    
    public ProductCatalogModule(SortedListInterface<Product>[] catalogList, SortedListInterface<Product> itemList) {
        this.catalogList = catalogList;
        this.itemList = itemList;
        input = new ControlScanner();
    }
    
    public void select() {
        int choice = -1;
        while (choice != 0) {
            displayMenu();
            choice = input.nextInt("      Selection : ", "Invalid input. Enter again", 0, 12);
            if (choice >= 1) {
                if (catalogList[choice - 1].isEmpty()) {
                    displayMaintenanceMenu(choice - 1);
                } else {
                    displayMaintenanceMenu(choice - 1);
                }
            }
        }
    }
    
    private void displayMaintenanceMenu(int index) {
        System.out.println("");
        System.out.println("      Catalog Month --> (" + months[index] + ")");
        System.out.println("          +----------------------------------+");
        System.out.println("          |    1. View Catalog List          |");
        System.out.println("          |    2. Add Item into Catalog      |");
        //System.out.println("          |    3. Remove Catalog List        |");
        System.out.println("          |    0. Back                       |");
        System.out.println("          +----------------------------------+");
        System.out.println();
        int choice = input.nextInt("      Selection: ", "Invalid input. Enter Again", 0, 3);
        if (choice != 0) {
            switch (choice) {
                case 1:
                    viewList(index);
                    break;
                case 2:
                    addCatalog(index);
                    break;
                /*case 3:
                    if (input.getConfirmation("      Are you sure want to remove this catalog ? (Y/N): ")) {
                        catalogList[index].clear();
                    } else {
                        System.out.println("      Delete has been cancel");
                    }
                    break;*/
            }
        }
    }
    
    public void addCatalog(int index) {
        int total = 0;
        System.out.println("");
        System.out.println("      Catalog List --> (" + months[index] + ")");
        displayList(itemList);
        System.out.println("");
        String id = input.nextLine("      Enter the product id of the product you want to add: ");
        String number[] = id.split(",");
        if (isValid(number)) {
            for (String idToAdd : number) {
                Product p = itemList.firstOrDefault((o) -> o.getId().equalsIgnoreCase(idToAdd));
                if (p == null) {
                    System.out.println("      *----------------------------------------------------*");
                    System.out.println("      |" + "   " + idToAdd + " NOT FOUND. Unsuccessfully Added!!!" + months[index] + "   |");
                    System.out.println("      *----------------------------------------------------*");
                } else {
                    if (catalogList[index].contains(p)) {
                        System.out.println("      *---------------------------------------------------------*");
                        System.out.println("      |" + "   " + idToAdd + " already EXIST. Unsuccessfully Added!!!" + months[index] + "   |");
                        System.out.println("      *---------------------------------------------------------*");
                        
                    } else {
                        total++;
                        catalogList[index].add(p);
                        System.out.println("      *------------------------------------------------------*");
                        System.out.println("      |" + "   " + idToAdd + " Successfully Added into Catalog!!! " + months[index] + "   |");
                        System.out.println("      *------------------------------------------------------*");
                    }
                }
            }
            if (total > 0) {
                categorize(catalogList[index]);
            }
//            /System.out.println("      Total added: " + total);
        } else {
            System.out.println("      ERROR: IDs cannot contain special characters !");
        }
    }
    
    public void viewList(int index) {
        if(catalogList[index].isEmpty()){
            System.out.println("      ** NO product item in this catalog.");
        }else {
            System.out.println("");
            System.out.println("      Catalog (" + months[index] + ")" + " List");
            System.out.println("");
            displayList(catalogList[index]);
        }
    }
    
    private void categorize(SortedListInterface<Product> itemsList) {
        itemsList.orderBy((o1, o2) -> o1.getPtype().getName().compareToIgnoreCase(o2.getPtype().getName()) < 0 ? OrderByClause.MOVE_TO_FRONT
                : OrderByClause.MOVE_TO_BACK);
    }
    
    private void displayList(SortedListInterface<Product> itemList) {
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
                System.out.println("      " + p.getPtype().getName().toUpperCase());
                System.out.println("        No.\tProduct ID\tType ID\t\tType Name\t\t\tName\t\tDescription\t\tPrice(RM)\tQuantity");
                System.out.println("      *------------------------------------------------------------------------------------------------------------------------------------------*");
                prevType = p.getPtype().getName();
            }
            System.out.println(String.format("      %3d. %s", (index + 1), p));
            index++;
        }
    }
    
    private boolean isValid(String[] number) {
        boolean valid = true;
        for (int i = 0; i < number.length && valid; i++) {
            number[i] = number[i].trim();
            for (int j = 0; j < number[i].length(); j++) {
                if (!Character.isLetterOrDigit(number[i].charAt(j))) {
                    valid = false;
                }
            }
        }
        return valid;
    }
    
    
    
    private void displayMenu() {
        System.out.println("");
        System.out.println("          +------------------------------------------------+");
        System.out.println("          |                Catalog Maintenance             |");
        System.out.println("          +------------------------------------------------+");
        for (int i = 0; i < months.length; i++) {
            System.out.println(String.format("          |    %-3d. %-15s %-10s", (i + 1), months[i], !catalogList[i].isEmpty() ? "[Assigned]" : "[Pending/No Assign]    |"));
        }
        System.out.println("          |    0  . Back                                   |");
        System.out.println("          +------------------------------------------------+");
        
    }
}
