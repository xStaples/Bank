package com.revature.ui.bank;

import com.revature.model.BankAccount;
import com.revature.services.bankServices.BankServices;
import com.revature.ui.menu.Menu;

import org.apache.log4j.Logger;

public class ApplicationMenu implements Menu {
    private static Logger log = Logger.getLogger(ApplicationMenu.class);
    private BankAccount accountApplication;
    BankServices bankServices;

    public ApplicationMenu(BankAccount accountApplication) {
        super();
        this.accountApplication = accountApplication;
        bankServices = new BankServices();
    }

    @Override
    public void display() {
        log.info(accountApplication + " Received");
        
    }
    
}
