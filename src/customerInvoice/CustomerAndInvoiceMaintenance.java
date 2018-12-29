/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customerInvoice;

import ADT.LinkedInterface;
import model.Consumer;
import model.CorporateCustomer;
import model.Person;
import model.Invoice;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Scanner;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author use
 */
public class CustomerAndInvoiceMaintenance {
    
    public static boolean validInput;
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
    
    public static void CustomerAndInvoiceMenu(LinkedInterface<CorporateCustomer> corporateCustomer,LinkedInterface<Invoice> invoice ) { 
        //to show the customer maitenance and invoice maintenance menu
        int selection;
        
        System.out.println("");
        System.out.println("          +-----------------------------------------------------+");
        System.out.println("          |Customer Maintenance and Invoice Maintenance Menu    |");
        System.out.println("          |-----------------------------------------------------|");
        System.out.println("          |      1. Customer Maintenance                        |");
        System.out.println("          |      2. Invoice Maintenance                     |");
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
                CorporateCustomerManagement(corporateCustomer,invoice,userSelection);
                break;
                
            case 2:
                System.out.println("");
                System.out.println("          +----------------------------------+");
                System.out.println("          |       Invoice Maintanance        |");
                System.out.println("          |----------------------------------|");
                System.out.println("          |      1. Generate Invoice         |");
                System.out.println("          |      2. Payment                  |");
                System.out.println("          |      0. Exit                     |");
                System.out.println("          +----------------------------------+");
                System.out.println("");
                
                int userSelection2 = getSelection("      Selection: ", 0, 2);
                invoiceManagement(corporateCustomer,invoice,userSelection2);
                break;
                
            case 0:
                break;
        }
    }

    private static void CorporateCustomerManagement(LinkedInterface<CorporateCustomer> corporateCustomer,LinkedInterface<Invoice> invoice,int userSelection) {
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
                updateCorporateCustomer(corporateCustomer,invoice);
                break;
            case 4:
                deleteCorporateCustomer(corporateCustomer,invoice);
                break;
        }
        CustomerAndInvoiceMenu(corporateCustomer,invoice);
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

    private static void updateCorporateCustomer(LinkedInterface<CorporateCustomer> corporateCustomer,LinkedInterface<Invoice> invoice) {
        
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
                CustomerAndInvoiceMenu(corporateCustomer,invoice);
                break;
            case 1:
                updateCompanyName(corporateCustomer,invoice,choice);
                break;
            case 2:
                updateName(corporateCustomer,invoice,choice);
                break;
            case 3:
                updateAddress(corporateCustomer,invoice,choice);
                break;
            case 4:
                updateContactNo(corporateCustomer,invoice,choice);
                break;
            case 5:
                updateEmail(corporateCustomer,invoice,choice);
                break;  
            case 6:
                updateCreditLimit(corporateCustomer,choice);
                break;
        }
    }

    private static void updateCompanyName(LinkedInterface<CorporateCustomer> corporateCustomer,LinkedInterface<Invoice> invoice,int choice) {
        
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
            updateCorporateCustomer(corporateCustomer,invoice);
        }
                
    }

    private static void updateName(LinkedInterface<CorporateCustomer> corporateCustomer,LinkedInterface<Invoice> invoice, int choice) {
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
            updateCorporateCustomer(corporateCustomer,invoice);
        }
    }

    private static void updateAddress(LinkedInterface<CorporateCustomer> corporateCustomer,LinkedInterface<Invoice> invoice, int choice) {
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
            updateCorporateCustomer(corporateCustomer,invoice);
        }
    }

    private static void updateContactNo(LinkedInterface<CorporateCustomer> corporateCustomer,LinkedInterface<Invoice> invoice, int choice) {
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
            updateCorporateCustomer(corporateCustomer,invoice);
        }
    }

    private static void updateEmail(LinkedInterface<CorporateCustomer> corporateCustomer,LinkedInterface<Invoice> invoice, int choice) {
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
            updateCorporateCustomer(corporateCustomer,invoice);
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

    private static void deleteCorporateCustomer(LinkedInterface<CorporateCustomer> corporateCustomer,LinkedInterface<Invoice> invoice) {
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
            CustomerAndInvoiceMenu(corporateCustomer,invoice);
        }
    }

    private static void invoiceManagement(LinkedInterface<CorporateCustomer> corporateCustomer,LinkedInterface<Invoice> invoice ,int userSelection2) {
        //all the invoice function
        switch (userSelection2) {
            case 0:
                break;
            case 1:
                generateInvoice(corporateCustomer,invoice);
                break;
            case 2:
                payment(corporateCustomer,invoice);
                break;
//            case 3:
//                updateCorporateCustomer(corporateCustomer,invoice);
//                break;
//            case 4:
//                deleteCorporateCustomer(corporateCustomer,invoice);
//                break;
        }
        CustomerAndInvoiceMenu(corporateCustomer,invoice);
    }

    private static void generateInvoice(LinkedInterface<CorporateCustomer> corporateCustomer,LinkedInterface<Invoice> invoice) {
        Scanner sc = new Scanner(System.in);
        
        
        //get last day
        Date today = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);

        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.DATE, -1);

        Date firstDayOfMonth = calendar.getTime();

        DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        System.out.println("\n\nToday            : " + sdf.format(today));
        System.out.println("Last Day of Month: " + sdf.format(firstDayOfMonth));
        if(sdf.format(today).equals(sdf.format(firstDayOfMonth))){
            
                        //start from here to copy
            System.out.println("\nInvoice List");
            System.out.println("\n===========================");
            System.out.println(invoice.toString());
            System.out.println("Please choose the number that you want to generate invoice:");
            
            int choice =sc.nextInt();
        
            while(choice<0 || choice >corporateCustomer.getSize()){
                System.out.println("\nInvalid number.Please enter again.");
                System.out.println("Please choose the number that you want to generate invoice:");
            
                choice =sc.nextInt();
            }
            System.out.println("====================================");
            System.out.println("Date:"+invoice.get(choice).getDate());
            System.out.println("FlowerName:"+invoice.get(choice).getFlowerName());
            System.out.println("Quantity:"+invoice.get(choice).getQuantity());
            System.out.println("Price:"+invoice.get(choice).getPrice());
            System.out.println("Amount:"+invoice.get(choice).getAmount());
            System.out.println("====================================\n");
            
            //asking whether want to send email or not
            System.out.println("\nDo you want to send the invoice to the corporate customer");
            System.out.print("\n[Y/N]: ");
            char confirm = sc.next().charAt(0);
            if(confirm=='Y'||confirm =='y'){
                
                try{
                    String host ="smtp.gmail.com" ;
                    String user = "liewmengchua@gmail.com";
                    String pass = "chua17831749519";
                    String to = corporateCustomer.get(choice).getEmail();
                    String from = "liewmengchua@gmail.com";
                    String subject = "Monthly Invoice";
                    String messageText = "Date:"+invoice.get(choice).getDate()+"\nFlowerName:"+invoice.get(choice).getFlowerName()
                            +"\nQuantity:"+invoice.get(choice).getQuantity()+"\nPrice:"+invoice.get(choice).getPrice()+
                            "\nAmount:"+invoice.get(choice).getAmount()+"\n====================================\n";
                    boolean sessionDebug = false;

                    Properties props = System.getProperties();

                    props.put("mail.smtp.starttls.enable", "true");
                    props.put("mail.smtp.host", host);
                    props.put("mail.smtp.port", "587");
                    props.put("mail.smtp.auth", "true");
                    props.put("mail.smtp.starttls.required", "true");

                    java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
                    Session mailSession = Session.getDefaultInstance(props, null);
                    mailSession.setDebug(sessionDebug);
                    Message msg = new MimeMessage(mailSession);
                    msg.setFrom(new InternetAddress(from));
                    InternetAddress[] address = {new InternetAddress(to)};
                    msg.setRecipients(Message.RecipientType.TO, address);
                    msg.setSubject(subject); msg.setSentDate(new Date());
                    msg.setText(messageText);

                    Transport transport=mailSession.getTransport("smtp");
                    transport.connect(host, user, pass);
                    transport.sendMessage(msg, msg.getAllRecipients());
                    transport.close();
                    System.out.println("invoice send successfully");
                }catch(Exception ex)
                {
                    System.out.println(ex);
                }
            
            }
            CustomerAndInvoiceMenu(corporateCustomer,invoice);
            
            
        }else{
            System.out.println("\nToday not the last day\n");
            System.out.println("If you want to to view the invoice list,please go change the date in the setting\n");   
            

//            //start from here to copy
//            System.out.println("\nInvoice List");
//            System.out.println("\n===========================");
//            System.out.println(invoice.toString());
//            System.out.println("Please choose the number that you want to generate invoice:");
//            
//            int choice =sc.nextInt();
//        
//            while(choice<0 || choice >corporateCustomer.getSize()){
//                System.out.println("\nInvalid number.Please enter again.");
//                System.out.println("Please choose the number that you want to generate invoice:");
//            
//                choice =sc.nextInt();
//            }
//            System.out.println("====================================");
//            System.out.println("Date:"+invoice.get(choice).getDate());
//            System.out.println("FlowerName:"+invoice.get(choice).getFlowerName());
//            System.out.println("Quantity:"+invoice.get(choice).getQuantity());
//            System.out.println("Price:"+invoice.get(choice).getPrice());
//            System.out.println("Amount:"+invoice.get(choice).getAmount());
//            System.out.println("====================================\n");
//            
//            //asking whether want to send email or not
//            System.out.println("\nDo you want to send the invoice to the corporate customer");
//            System.out.print("\n[Y/N]: ");
//            char confirm = sc.next().charAt(0);
//            if(confirm=='Y'||confirm =='y'){
//                
//                try{
//                    String host ="smtp.gmail.com" ;
//                    String user = "liewmengchua@gmail.com";
//                    String pass = "chua17831749519";
//                    String to = corporateCustomer.get(choice).getEmail();
//                    String from = "liewmengchua@gmail.com";
//                    String subject = "Monthly Invoice";
//                    String messageText = "Date:"+invoice.get(choice).getDate()+"\nFlowerName:"+invoice.get(choice).getFlowerName()
//                            +"\nQuantity:"+invoice.get(choice).getQuantity()+"\nPrice:"+invoice.get(choice).getPrice()+
//                            "\nAmount:"+invoice.get(choice).getAmount()+"\n====================================\n";
//                    boolean sessionDebug = false;
//
//                    Properties props = System.getProperties();
//
//                    props.put("mail.smtp.starttls.enable", "true");
//                    props.put("mail.smtp.host", host);
//                    props.put("mail.smtp.port", "587");
//                    props.put("mail.smtp.auth", "true");
//                    props.put("mail.smtp.starttls.required", "true");
//
//                    java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
//                    Session mailSession = Session.getDefaultInstance(props, null);
//                    mailSession.setDebug(sessionDebug);
//                    Message msg = new MimeMessage(mailSession);
//                    msg.setFrom(new InternetAddress(from));
//                    InternetAddress[] address = {new InternetAddress(to)};
//                    msg.setRecipients(Message.RecipientType.TO, address);
//                    msg.setSubject(subject); msg.setSentDate(new Date());
//                    msg.setText(messageText);
//
//                    Transport transport=mailSession.getTransport("smtp");
//                    transport.connect(host, user, pass);
//                    transport.sendMessage(msg, msg.getAllRecipients());
//                    transport.close();
//                    System.out.println("invoice send successfully");
//                }catch(Exception ex)
//                {
//                    System.out.println(ex);
//                }
//            
//            }
//            CustomerAndInvoiceMaintenance(corporateCustomer,invoice);
        }
    }

    private static void payment(LinkedInterface<CorporateCustomer> corporateCustomer, LinkedInterface<Invoice> invoice) {
        //detect whether is over 7th
        Calendar calendar = Calendar.getInstance(); 
        Calendar calendar1 = Calendar.getInstance();
        
        Date today = new Date();
        
        calendar.set(Calendar.DAY_OF_MONTH, 7);
        
        calendar1.set(Calendar.DAY_OF_MONTH, 1);
        
        Date test1 = calendar.getTime();
        Date test2 = calendar1.getTime();

        DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        System.out.println("\n\nToday            : " + sdf.format(today));

        System.out.println("Last Day of Month: " + sdf.format(test1));
        System.out.println("Last Day of Month: " + sdf.format(test2));
        
        if(today.before(test1)&&today.after(test2)){
            
            System.out.println("start from here to copy");
            
            
            
        }else{
            System.out.println("i lov u");
        
        
        }







//        Scanner sc = new Scanner(System.in);
//        System.out.println("\nPayment List");
//        System.out.println("\n===========================");
//        System.out.println(invoice.toString());
//        System.out.println("Please please chooose the number that you want to make payment");
//            
//        int choice =sc.nextInt();
//        
//        while(choice<0 || choice >corporateCustomer.getSize()){
//        System.out.println("\nInvalid number.Please enter again.");
//        System.out.println("Please chooose the number that you want to make payment"); 
//        choice =sc.nextInt();
//        
//        }
//        corporateCustomer.get(choice).setCreditLimit(0);
//        System.out.println("paid successfully"); 
//        CustomerAndInvoiceMaintenance(corporateCustomer,invoice);
    }
    
}
