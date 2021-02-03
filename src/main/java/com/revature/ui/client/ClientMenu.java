package com.revature.ui.client;

import com.revature.services.clientServices.ClientServices;

import com.revature.ui.menu.Menu;

import org.apache.log4j.Logger;



public class ClientMenu implements Menu{

    private static Logger log = Logger.getLogger(ClientMenu.class);
    public ClientServices clientServices;

    public ClientMenu(){
        clientServices = new ClientServices();
    }

    @Override
    public void display() {
        int choice = 0;

        do {
            System.out.println();
            System.out.println(" ==Client Menu==");
            System.out.println("==================");
            System.out.println();
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
            
                default:
                    log.info("No valid choice entered, please try again");
            }
        } while (choice != 9);
    }
    
}
