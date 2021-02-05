package com.revature.ui.client;

import com.revature.model.BankAccount;
import com.revature.model.Client;
import com.revature.services.bankServices.BankServices;
import com.revature.services.clientServices.ClientServices;
import com.revature.ui.bank.ApplicationMenu;
import com.revature.ui.menu.Menu;

import org.apache.log4j.Logger;



public class ClientMenu implements Menu{

    private static Logger log = Logger.getLogger(ClientMenu.class);
    private Client client = null;
    public ClientServices clientServices;
    public BankServices bankServices;
    

    public ClientMenu(Client client){
        super();
        this.client = client;
        clientServices = new ClientServices();
        bankServices = new BankServices();
    }

    @Override
    public void display() {
        int choice = 0;
        Client clientInfo = clientServices.getAccountInfo(client);
        log.debug(clientInfo);
        do {
            System.out.println();
            System.out.println(" ==Client Menu==");
            System.out.println("==================");
            System.out.println();
            System.out.println("Welcome back, " + clientInfo.getUsername());
            System.out.println("1. Check Balances");
            System.out.println("2. Apply for Account");
            System.out.println("3. Make Withdrawal or Deposit");
            System.out.println("4. Transfer Money");
            System.out.println("9. Exit");

            try {
                choice = Integer.parseInt(userInput.nextLine());
            } catch (NumberFormatException e) {
                log.debug(e.getMessage());
            }

            switch (choice) {
                case 1:
                    
                    break;
            
                case 2:
                    BankAccount accountAppl = getApplicationInput();
                    Menu applMenu = new ApplicationMenu(accountAppl);
                    applMenu.display();
                    break;
                default:
                    break;
            }
        } while (choice != 9);
    }
    

    public BankAccount getApplicationInput(){
        BankAccount accountInfo = null;
        int choice = 0;
        String accountType;
        String accountOwner;
        double accountBalance;
        do {
            System.out.println();
            System.out.println("What kind of account");
            System.out.println();
            System.out.println("1. Checking Account");
            System.out.println("2. Savings Account");
            System.out.println("9. Cancel");
            try {
                choice = Integer.parseInt(userInput.nextLine());
            } catch (Exception e) {
                //TODO: handle exception
            }
            switch (choice) {
                case 1:
                    accountType = "Checking Account";
                    accountOwner = client.getUsername();
                    accountBalance = Double.parseDouble(userInput.nextLine());
                    accountInfo = new BankAccount(accountType, accountOwner, accountBalance);
                    log.debug(accountInfo);
                    break;
                case 2:
                    accountType = "Savings Account";
                    accountOwner = client.getUsername();
                    accountBalance = Double.parseDouble(userInput.nextLine());
                    accountInfo = new BankAccount(accountType, accountOwner, accountBalance);
                    log.debug(accountInfo);
                    break;
                default:
                    break;
            }
        } while (choice != 9);
        System.out.println("Thank You For Your Application");

        return accountInfo;
    }
}
