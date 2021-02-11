package com.revature.ui.client;

import com.revature.model.BankAccount;
import com.revature.model.Client;
import com.revature.services.bankServices.BankServices;
import com.revature.services.clientServices.ClientServices;
import com.revature.ui.bank.WithdrawAndDeposit;
import com.revature.ui.menu.Menu;

import org.apache.log4j.Logger;

public class ClientMenu implements Menu {

    private static Logger log = Logger.getLogger(ClientMenu.class);
    private Client client = null;
    public ClientServices clientServices;
    public BankServices bankServices;

    public ClientMenu(Client client) {
        super();
        this.client = client;
        clientServices = new ClientServices();
        bankServices = new BankServices();
    }

    @Override
    public void display() {
        int choice = 0;
        Client clientInfo = bankServices.getClientInfo(client);
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
                    Menu clientAccountBalances = new ClientBalancesMenu(clientInfo);
                    clientAccountBalances.display();
                    break;

                case 2:
                    BankAccount application = getApplicationInput();
                    bankServices.submitApplicationInput(application);
                    break;
                
                case 3:
                    Menu withdrawAndDepositMenu = new WithdrawAndDeposit(clientInfo);
                    withdrawAndDepositMenu.display();
                    break;

                case 4:
                    Menu transferMoneyMenu = new TransferMoneyMenu(clientInfo);
                    transferMoneyMenu.display();
                    break;
                default:
                    break;
            }
        } while (choice != 9);
    }


    public BankAccount getApplicationInput() {
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

            }
            switch (choice) {
                case 1:
                    System.out.println();
                    log.info("  Let's set up your new Checking Account! ");
                    accountType = "Checking";
                    accountOwner = client.getUsername();
                    log.info("Please add funds to your new checking account: ");
                    accountBalance = Double.parseDouble(userInput.nextLine());
                    accountInfo = new BankAccount(accountType, accountOwner, accountBalance);

                    if (accountInfo != null) {
                        log.info("Your Account has been submitted. Please wait while someone reviews your account!");
                        log.info("Thank you for your application!");
                    }

                    break;
                case 2:
                    System.out.println();
                    log.info("  Let's set up your new Savings Account! ");
                    accountType = "Savings";
                    accountOwner = client.getUsername();
                    log.info("Please add funds to your new checking account: ");
                    accountBalance = Double.parseDouble(userInput.nextLine());
                    accountInfo = new BankAccount(accountType, accountOwner, accountBalance);

                    if (accountInfo != null) {
                        log.info("Your Account has been submitted. Please wait while someone reviews your account!");
                        log.info("Thank you for your application!");
                    }
                    break;
            }
        } while (choice != 9);

        return accountInfo;
    }
}
