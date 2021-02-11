package com.revature.ui.bank;


import com.revature.model.Client;
import com.revature.services.bankServices.BankServices;
import com.revature.ui.menu.Menu;

import org.apache.log4j.Logger;

public class WithdrawAndDeposit implements Menu {
    private static Logger log = Logger.getLogger(WithdrawAndDeposit.class);
    BankServices bankServices;
    private Client clientInfo;

    public WithdrawAndDeposit(Client clientInfo) {
        super();
        this.clientInfo = clientInfo;
        bankServices = new BankServices();
    }

    @Override
    public void display() {
        log.debug(clientInfo + "WithdrawDeposit Menu");
        int choice = 0;
        do {
            System.out.println();
            log.info(" === Withdraw and Deposit === ");
            log.info("================================");
            System.out.println();
            log.info("What would you like to do?");
            log.info("1. Withdraw Money");
            log.info("2. Deposit Money");
            log.info("3. Back");

            try {
                choice = Integer.parseInt(userInput.nextLine());
            } catch (Exception e) {
                log.debug(e.getMessage() + " No go on the choice, bud.");
            }
            switch (choice) {
                case 1:
                    log.info("How much would you like to withdraw?");
                    double withdrawAmount = amountInput();
                    int accountWithdraw = bankServices.accountWithdraw(clientInfo, withdrawAmount);

                    break;
                case 2:
                    log.info("How much would you like to deposit?");
                    double depositAmount = amountInput();
                    int accountDeposit = bankServices.accountDeposit(clientInfo, depositAmount);

                    break;
                default:
                    break;
            }
        } while (choice != 3);
        

    }

    public double amountInput(){

        double amount = 0;

        try {
            amount = Double.parseDouble(userInput.nextLine());   
        } catch (Exception e) {

        }
        if (amount < 0) {
            log.info("Input cannot be negative.");
            amount = 0;
        }
        return amount;
    }
}
