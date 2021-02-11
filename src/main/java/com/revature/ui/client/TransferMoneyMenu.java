package com.revature.ui.client;

import com.revature.model.Client;
import com.revature.services.bankServices.BankServices;
import com.revature.ui.menu.Menu;

import org.apache.log4j.Logger;

public class TransferMoneyMenu implements Menu {
    private static Logger log = Logger.getLogger(TransferMoneyMenu.class);
    BankServices bankServices;
    Client receiver = null;
    double transferAmount;
    private Client sender;
    
    public TransferMoneyMenu(Client sender) {
        super();
        this.sender = sender;
        bankServices = new BankServices();
    }

    @Override
    public void display() {
        int choice = 0;

        do {
            System.out.println();
            log.info(" === Transfer Funds === ");
            log.info("=========================");
            System.out.println();
            log.info("1. Send Money");
            log.info("2. Receive Money");
            log.info("3. Back");

            try {
                choice = Integer.parseInt(userInput.nextLine());
            } catch (Exception e) {

            }

            switch (choice) {
                case 1:
                    log.info("Send Money");
                    receiver = getReceiverAccountInput();
                    transferAmount = getTransferAmount();
                    bankServices.transferFunds(sender, receiver, transferAmount);
                    break;
            
                case 2:
                    log.info("Receive Money");
                    break;
                default:
                    break;
            }
        } while (choice != 3);

    }
    

    public Client getReceiverAccountInput(){
        Client receiveUser = null;
        log.info("Enter Recipient Username: ");
        String recipientUsername = userInput.nextLine();
        receiveUser = new Client(recipientUsername);
        return receiveUser;
    }
    public double getTransferAmount(){
        log.info("Enter Amount of Money to transfer");
        double amount = Double.parseDouble(userInput.nextLine());
        return amount;
    }
}
