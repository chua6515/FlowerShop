/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import ADT.*;
import java.util.Scanner;
/**
 *
 * @author QinYing
 */
public class FioreFlowerShop {
    
    public static LinkedListInterface<Product> itemList = new LinkedList<>();
    
    public static PriorityQueueInterface<Catalog> catalogList = new PriorityQueue<>();
    
    static LinkedListInterface<Catalog> inCompleteAssignList = new LinkedList<>();
    static LinkedListInterface<Catalog> completedAssignList = new LinkedList<>();
    
    

    
    
    
    public static boolean validInput;
    
    public static void main(String[] args) {
        //liew meng part----------------------------------------------------
        LinkedInterface<CorporateCustomer> corporateCustomer = new Linked<>();
        
        corporateCustomer.add(new CorporateCustomer("Jy Art.Sdn.Bhd.","Chua Liew Meng", "144, Lorong 12, Taman Sentosa, 34000 Taiping, Perak", "085569987","liewmengchua@gmail.com", 1400.00));
        corporateCustomer.add(new CorporateCustomer("Johnson Florist","Chong Chan Phang", "8, Jalan Pangung Wayang, 34000 Taiping, Perak", "082456693","liewmengchua@gmail.com", 2000.00));
        
        //----------------------------------------------------------------
        
       
        insertProducts();
        insertCatalog();
        
        int selection;
        boolean endProgram = false;

        while (!endProgram) {
            //call main menu
            menu();
            selection = getSelection("      Selection: ", 0, 4);
            // call each module menu
            switch (selection) {
                case 1:
                    MaintenanceMenu();
                    break;
                case 2:
                    break;
                case 3:
                    CustomerAndInvoiceMenu(corporateCustomer);
                    break;
                case 4:
//                    CustomerAndInvoiceMenu();
                    break;
                case 0:
                    endProgram = true;
                    break;
                default:
                    break;
            }
        }
    }
    
    public static void menu() {
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
        
        //need to show the out-of-stock information
        
    }
    
    public static void MaintenanceMenu() {
        int selection;
        
        System.out.println("");
        System.out.println("          +-----------------------------+");
        System.out.println("          |    1. Product Information   |");
        System.out.println("          |    2. Promotion Catalog     |");
        System.out.println("          |    0. Back                  |");
        System.out.println("          +-----------------------------+");
        System.out.println();
        
        selection = getSelection("      Selection: ", 0, 2);

        switch (selection) {
            case 1:
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

                int userSelection = getSelection("      Selection: ", 0, 4);
                productManagement(userSelection);
                break;
                
            case 2:
                System.out.println("");
                System.out.println("          +----------------------------------+");
                System.out.println("          |       Catalogue Maintanance      |");
                System.out.println("          |----------------------------------|");
                System.out.println("          |      1. Add Catalogue            |");
                System.out.println("          |      2. Update Catalogue         |");
                System.out.println("          |      3. Delete Catalogue         |");
                System.out.println("          |      0. Exit                     |");
                System.out.println("          +----------------------------------+");
                System.out.println("");
                
                int userSelection2 = getSelection("      Selection: ", 0, 4);
                catalogManagement(userSelection2);
                break;
                
            case 0:
                break;
        }
    }
    
    public static void productManagement(int userSelection) {
        switch (userSelection) {
            case 0:
                break;
            case 1:
                System.out.println(itemList.toString());
                break;
            case 2:
                addProduct();
                break;
            case 3:
                updateProduct();
                break;
            case 4:
                removeProduct();
                break;
        }
        MaintenanceMenu();
    }
    
    public static void addProduct() {
        Scanner input = new Scanner(System.in);
        
        System.out.println("");
        int ptype = getSelection("      1. Bouquet      2. Fresh Flower\n      Select Product Type       : ", 1, 2);
        String ptypes = (ptype == 1) ? "Bouquet" : "Fresh Flower";
        
        String name;
        do {
            System.out.print("      Enter Product Name        : ");
            name = input.nextLine();
            if (name.isEmpty()) {
                System.out.println("      Product name should not be empty");
            }
        } while (name.isEmpty());
        
        String description;
        do {
            System.out.print("      Enter Product Description : ");
            description = input.nextLine();
            if (name.isEmpty()) {
                System.out.println("      Product description should not be empty");
            }
        } while (name.isEmpty());
        
        double price = getDoub("      Enter Price               : RM ", 1, 999999999);
        int quantity = getSelection("      Enter Quantity            : ", 1, 999999999);
        
        itemList.add(new Product(ptypes, name, description, price, quantity));
        System.out.println("");
        System.out.println("      *--------------------------------------*");
        System.out.println("      |   New Product Successfully Added!!!  |");
        System.out.println("      *--------------------------------------*");
    }
    
    public static void updateProduct() {
        if (!itemList.isEmpty()) {
            System.out.print(itemList.toString());
            System.out.println("      *****************************************************************");
            
            int updateProduct;
            updateProduct = getSelection("      Enter the number of the product you want to update : ", 1, itemList.getLength());
            
            Product editProduct = itemList.getEntry(updateProduct);
            
            int updateSelection;
            System.out.println("          +---------------------------------+");
            System.out.println("          |    Select category to update    |");
            System.out.println("          +---------------------------------+");
            System.out.println("          |   1. Update Product Type        |");
            System.out.println("          |   2. Update Product Name        |");
            System.out.println("          |   3. Update Product Description |");
            System.out.println("          |   4. Update Product Price       |");
            System.out.println("          |   5. Update Product Quantity    |");
            System.out.println("          |   0. Return to Main Menu        |");
            System.out.println("          +---------------------------------+");
            System.out.println("");
            updateSelection = getSelection("      Selection : ", 0, 5);
            
            Scanner input = new Scanner(System.in);
            switch (updateSelection){
                case 0:
                    MaintenanceMenu();
                    break;
                case 1:
                    int editType = getSelection("      1. Bouquet      2. Fresh Flower\n      Select Product Type       : ", 1, 2);
                    String editTypes = (editType == 1) ? "Bouquet" : "Fresh Flower";
                    editProduct.setPtype(editTypes);
                    break;
                    
                case 2:
                    String editName;
                    do {
                        System.out.print("      Enter New Product Name        : ");
                        editName = input.nextLine();
                        if (editName.isEmpty()) {
                            System.out.println("      Product name should not be empty");
                        }
                    } while (editName.isEmpty());
                    
                    editProduct.setName(editName);
                    break;
                    
                case 3:
                    String editDesc;
                    do {
                        System.out.print("      Enter New Product Description : ");
                        editDesc = input.nextLine();
                        if (editDesc.isEmpty()) {
                            System.out.println("      Product description should not be empty");
                        }
                    } while (editDesc.isEmpty());
                    
                    editProduct.setDescription(editDesc);
                    break;
                    
                case 4:
                    double editPrice = getDoub("      Enter New Price               : RM ", 1, 999999999);
                    editProduct.setPrice(editPrice);
                    break;
                    
                case 5:
                    int editQuantity = getSelection("      Enter New Quantity            : ", 1, 999999999);
                    editProduct.setQuantity(editQuantity);
                    break;
            }
            
            System.out.println("");
            System.out.println("      *-------------------------------------*");
            System.out.println("      |    Product Successfully Updated!!!  |");
            System.out.println("      *-------------------------------------*");
            System.out.println("      Product Details-->");
            System.out.println("      " + editProduct.toString());
            System.out.println("      *****************************************************************");
        } else {
            System.out.println("");
            System.out.println("      There is no list for you to update.");
        }
    }
    
    public static void removeProduct() {
        if(!itemList.isEmpty()) {
            System.out.println(itemList.toString());
            System.out.println("      *****************************************************************");

            int removeProduct = getSelection("      Enter the number of the product you want to remove : ", 1, itemList.getLength());
            itemList.remove(removeProduct);
            System.out.println(itemList.toString());
        } else {
            System.out.println("");
            System.out.println("      There is no list for you to delete.");
        }
    }
    
    public static int getSelection(String msg, int low, int high) {
        Scanner input = new Scanner(System.in);

        int selection = -1;
        do {
            try {
                System.out.print(msg);
                selection = input.nextInt();
                validInput = true;
            } catch (Exception e) {
                System.out.println("      Invalid input!\n");
                validInput = false;
                input.next();
            }
        } while (!validInput || selection < low || selection > high);

        return selection;
    }
    
    public static double getDoub(String msg, double low, double high) {
        Scanner input = new Scanner(System.in);

        double selection = -1.00;
        do {
            try {
                System.out.print(msg);
                selection = input.nextDouble();
                validInput = true;
            } catch (Exception e) {
                System.out.println("      Invalid input!\n");
                validInput = false;
                input.next();
            }
        } while (!validInput || selection < low || selection > high);

        return selection;
    }
    
    public static void catalogManagement(int userSelection2) {
        Scanner input = new Scanner(System.in);
        switch(userSelection2) {
            case 0:
            case 1:
                int cMonth = getSelection("      Enter Promotion Month            : ", 1, 12);
                
                String cDesc;
                do {
                    System.out.print("      Enter Promotion Description : ");
                    cDesc = input.nextLine();
                    if (cDesc.isEmpty()) {
                        System.out.println("Promotion description should not be empty");
                    }
                } while (cDesc.isEmpty());
                
                Catalog newCatalog = new Catalog(cMonth, cDesc, "", null);
                catalogList.enqueue(newCatalog);
                System.out.print(catalogList.toString());
                MaintenanceMenu();
                break;
            case 2:
                Catalog newAssign = catalogList.dequeue();
                System.out.println("Task details\n==============");
                System.out.println(newAssign.taskDetails());
                
                System.out.print(itemList.toString());
                int productSelection;
                productSelection = getSelection("Selection : ", 1, itemList.getLength());
                
                newAssign.setProductAssign(itemList.getEntry(productSelection));
                inCompleteAssignList.add(newAssign);
                System.out.println(newAssign.taskDetails());
                System.out.println("=======================================");
        
                break;
            case 3:
            case 4:
        }
    }
    

    
    
    public static void insertProducts() {
        itemList.add(new Product("Fresh Flower", "Roses", "Happy Valentine's Day", 75.50, 8));
        itemList.add(new Product("Bouquet", "Sunflowers", "Happy Graduation", 50.50, 10));
    }
    
    public static void insertCatalog() {
        Catalog catalog1 = new Catalog(01, "Happy Valentine's Day", "", null);
        Catalog catalog2 = new Catalog(05, "Happy Mother's Day", "", null);
        Catalog catalog3 = new Catalog(06, "Happy Father's Day", "", null);
        

        catalogList.enqueue(catalog1);
        catalogList.enqueue(catalog2);
        catalogList.enqueue(catalog3);
    }


    private static void CustomerAndInvoiceMenu(LinkedInterface<CorporateCustomer> corporateCustomer) { 
        //to show the customer maitenance and invoice maintenance menu
        int selection;
        
        System.out.println("");
        System.out.println("          +-----------------------------------------------------+");
        System.out.println("          |Customer Maintenance and Invoice Maintenance Menu    |");
        System.out.println("          |-----------------------------------------------------|");
        System.out.println("          |      1. Customer Maintenance                        |");
        System.out.println("          |      2. Maintenance Maintenance                     |");
        System.out.println("          |      0. Exit                                        |");
        System.out.println("          +-----------------------------------------------------+");
        System.out.println("");
        
        selection = getSelection("      Selection: ", 0, 2);

        switch (selection) {
            case 1:
                System.out.println("");
                System.out.println("          +----------------------------------+");
                System.out.println("          |        Corporate Maintanance     |");
                System.out.println("          |----------------------------------|");
                System.out.println("          |       1. Corporate customer List |");
                System.out.println("          |       2. Add Corporate           |");
                System.out.println("          |       3. Update Corporate        |");
                System.out.println("          |       4. Delete Product          |");
                System.out.println("          |       0. Exit                    |");
                System.out.println("          +----------------------------------+");
                System.out.println("");

                int userSelection = getSelection("      Selection: ", 0, 4);
                CorporateCustomerManagement(corporateCustomer,userSelection);
                break;
                
            case 2:
                System.out.println("");
                System.out.println("          +----------------------------------+");
                System.out.println("          |       Invoice Maintanance        |");
                System.out.println("          |----------------------------------|");
                System.out.println("          |      1. Generate Catalogue       |");
                System.out.println("          |      2. Payment                  |");
                System.out.println("          |      0. Exit                     |");
                System.out.println("          +----------------------------------+");
                System.out.println("");
                
                int userSelection2 = getSelection("      Selection: ", 0, 2);
                catalogManagement(userSelection2);
                break;
                
            case 0:
                break;
        }
    }

    private static void CorporateCustomerManagement(LinkedInterface<CorporateCustomer> corporateCustomer,int userSelection) {
        //all the function of corporate customer customer
        switch (userSelection) {
            case 0:
                break;
            case 1:
                System.out.println(corporateCustomer);
                break;
            case 2:
                addCorporateCustomer(corporateCustomer);
                break;
            case 3:
                updateCorporateCustomer(corporateCustomer);
                break;
            case 4:
                deleteCorporateCustomer(corporateCustomer);
                break;
        }
        CustomerAndInvoiceMenu(corporateCustomer);
    }

    private static void addCorporateCustomer(LinkedInterface<CorporateCustomer> corporateCustomer) {
        
        Scanner sc = new Scanner(System.in);
        System.out.println("\nAdd New Corporate Customer.\n");
        System.out.println("===========================\n");
        System.out.print("Campany Name: ");
        String companyName = sc.nextLine();
        int match;
        do{     
            match = 0; 
            for(int i=1;i<=corporateCustomer.getSize();i++){
                if(corporateCustomer.get(i).getCorpName().compareTo(companyName)==0){
                    match++;
            }
            if(match>0){
                System.out.println("\nInvalid Company Name(Company name Repeated). Please enter again");
                System.out.print("Company name: ");
                companyName = sc.nextLine();
            }
         }
        }while(match > 0);
        System.out.print("\nName: ");
        String name = sc.nextLine();
        System.out.print("\nAddress: ");
        String address = sc.nextLine();
        System.out.print("\nContactNo: ");
        String contactNo = sc.nextLine();
        System.out.print("\nEmail: ");
        String email = sc.nextLine();
        System.out.print("\nCreditLimit: ");
        double creditLimit = sc.nextInt();
        
       corporateCustomer.add(new CorporateCustomer(companyName,name,address,contactNo,email,creditLimit));
       System.out.println("New Corporate Customer Added.");
    }

    private static void updateCorporateCustomer(LinkedInterface<CorporateCustomer> corporateCustomer) {
        
        Scanner sc = new Scanner(System.in);
        System.out.println("\nCompany List\n");
        System.out.println("===========================\n");
        System.out.println(corporateCustomer);
        System.out.println("Please choose the number that you want to update");
        int choice =sc.nextInt();
        
        while(choice<0 || choice >corporateCustomer.getSize()){
            System.out.println("\nInvalid number.Please enter again.");
            System.out.println("Please choose the number that you want to update:");
            
            choice =sc.nextInt();
        }
        System.out.println("Updating Company "+corporateCustomer.get(choice).getCorpName());
        System.out.println("          +---------------------------------+");
        System.out.println("          |    Select category to update    |");
        System.out.println("          +---------------------------------+");
        System.out.println("          |   1. Update Company Name        |");
        System.out.println("          |   2. Update Personal Name       |");
        System.out.println("          |   3. Update Address             |");
        System.out.println("          |   4. Update Contact No          |");
        System.out.println("          |   5. Update Email               |");
        System.out.println("          |   6. Update creditLimit         |");
        System.out.println("          |   0. Back                       |");
        System.out.println("          +---------------------------------+");
        System.out.println("");
        int updateSelection = getSelection("      Selection : ", 0, 6);
        
        switch (updateSelection) {
            case 0:
                CustomerAndInvoiceMenu(corporateCustomer);
                break;
            case 1:
                updateCompanyName(corporateCustomer,choice);
                break;
            case 2:
                updateName(corporateCustomer,choice);
                break;
            case 3:
                updateAddress(corporateCustomer,choice);
                break;
            case 4:
                updateContactNo(corporateCustomer,choice);
                break;
            case 5:
                updateEmail(corporateCustomer,choice);
                break;  
            case 6:
                updateCreditLimit(corporateCustomer,choice);
                break;
        }
    }

    private static void updateCompanyName(LinkedInterface<CorporateCustomer> corporateCustomer,int choice) {
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Current company name: "+corporateCustomer.get(choice).getCorpName());
        System.out.println("Please enter new Company Name:");
        String companyName= sc.nextLine();
                
        if((corporateCustomer.get(choice).getCorpName().compareTo(companyName)!=0) && companyName != "")
        {
                    
            corporateCustomer.get(choice).setCorpName(companyName);
            System.out.println("Successfully updated companyName");
        }
        else
        {
            System.out.println("Invalid input");
            updateCorporateCustomer(corporateCustomer);
        }
                
    }

    private static void updateName(LinkedInterface<CorporateCustomer> corporateCustomer, int choice) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Current name: "+corporateCustomer.get(choice).getName());
        System.out.println("Please enter new Name:");
        String name= sc.nextLine();
                
        if((corporateCustomer.get(choice).getName().compareTo(name)!=0) && name != "")
        {
                    
            corporateCustomer.get(choice).setName(name);
            System.out.println("Successfully updated name");
        }
        else
        {
            System.out.println("Invalid input");
            updateCorporateCustomer(corporateCustomer);
        }
    }

    private static void updateAddress(LinkedInterface<CorporateCustomer> corporateCustomer, int choice) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Current address: "+corporateCustomer.get(choice).getAddress());
        System.out.println("Please enter new address:");
        String address= sc.nextLine();
                
        if((corporateCustomer.get(choice).getAddress().compareTo(address)!=0) && address != "")
        {
                    
            corporateCustomer.get(choice).setAddress(address);
            System.out.println("Successfully updated address");
        }
        else
        {
            System.out.println("Invalid input");
            updateCorporateCustomer(corporateCustomer);
        }
    }

    private static void updateContactNo(LinkedInterface<CorporateCustomer> corporateCustomer, int choice) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Current contact number: "+corporateCustomer.get(choice).getContactNo());
        System.out.println("Please enter new contact No:");
        String contactNo= sc.nextLine();
                
        if((corporateCustomer.get(choice).getContactNo().compareTo(contactNo)!=0) && contactNo != "")
        {
                    
            corporateCustomer.get(choice).setContactNo(contactNo);
            System.out.println("Successfully updated contact number");
        }
        else
        {
            System.out.println("Invalid input");
            updateCorporateCustomer(corporateCustomer);
        }
    }

    private static void updateEmail(LinkedInterface<CorporateCustomer> corporateCustomer, int choice) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Current email: "+corporateCustomer.get(choice).getEmail());
        System.out.println("Please enter new email:");
        String email= sc.nextLine();
                
        if((corporateCustomer.get(choice).getEmail().compareTo(email)!=0) && email != "")
        {
                    
            corporateCustomer.get(choice).setEmail(email);
            System.out.println("Successfully updated email");
        }
        else
        {
            System.out.println("Invalid input");
            updateCorporateCustomer(corporateCustomer);
        }
    }

    private static void updateCreditLimit(LinkedInterface<CorporateCustomer> corporateCustomer, int choice) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Current credit limit: "+corporateCustomer.get(choice).getCreditLimit());
        System.out.println("Please enter new credit limit:");
        Double creditLimit= sc.nextDouble();
                
      
            corporateCustomer.get(choice).setCreditLimit(creditLimit);
            System.out.println("Successfully updated companyName");
       

    }

    private static void deleteCorporateCustomer(LinkedInterface<CorporateCustomer> corporateCustomer) {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nCompany List\n");
        System.out.println("===========================\n");
        System.out.println(corporateCustomer);
        System.out.println("Please choose the number that you want to delete");
        int choice =sc.nextInt();
        
        while(choice<0 || choice >corporateCustomer.getSize()){
            System.out.println("\nInvalid number.Please enter again.");
            System.out.println("Please choose the number that you want to delete:");
            
            choice =sc.nextInt();
        }
        System.out.println("Name: "+corporateCustomer.get(choice).getName());
        System.out.print("\nDo you sure to remove this corporate customer?[Y/N]: ");
        char confirm = sc.next().charAt(0);
        if(confirm=='Y'||confirm =='y'){
            corporateCustomer.remove(choice);
            System.out.println("\nSuccessfully remove the corporate customer.");
        }
        else{
            CustomerAndInvoiceMenu(corporateCustomer);
        }
    }
    
}
