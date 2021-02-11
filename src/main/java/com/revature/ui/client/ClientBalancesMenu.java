package com.revature.ui.client;


import java.util.List;

import com.revature.model.Client;
import com.revature.model.BankAccount;
import com.revature.services.clientServices.ClientServices;
import com.revature.ui.menu.Menu;

import org.apache.log4j.Logger;

public class ClientBalancesMenu implements Menu {
    private static Logger log = Logger.getLogger(ClientBalancesMenu.class);
    private Client client = null;
    ClientServices clientServices;

    public ClientBalancesMenu(Client client) {
        super();
        this.client = client;
        clientServices = new ClientServices();
    }

    @Override
    public void display() {

        System.out.println();
        log.info(" === Account Balances === ");
        log.info("==========================");
        System.out.println();
        List<BankAccount> allAccounts = clientServices.checkBalances(client);
            for (BankAccount account : allAccounts) {
                log.info(account);
            }

    }

}
