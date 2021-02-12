package com.revature.services.bankServices;

import java.sql.Connection;
import java.sql.SQLException;

import com.revature.dao.bankDao.BankDao;
import com.revature.dao.bankDao.BankDaoImpl;
import com.revature.exceptions.DatabaseConnectionException;
import com.revature.model.BankAccount;
import com.revature.model.Client;
import com.revature.util.ConnectionUtil;

import org.apache.log4j.Logger;

public class BankServices {
    private static Logger log = Logger.getLogger(BankServices.class);
    public BankDao bankDao;

    public BankServices() {
        bankDao = new BankDaoImpl();
    }

    public int submitApplicationInput(BankAccount accountInfo) {
        int count = 0;
        try (Connection connection = ConnectionUtil.getConnection()) {
            connection.setAutoCommit(false);
            count = bankDao.submitClientAccountApp(accountInfo);
            connection.commit();

        } catch (SQLException | DatabaseConnectionException e) {
            log.debug("Something went wrong with the datebase" + e.getMessage());
        }

        return count;
    }

    public int accountWithdraw(Client clientInfo, double withdrawAmount) {

        int updatedAccount = 0;
        try {
            updatedAccount = bankDao.accountWithdraw(clientInfo, withdrawAmount);
        } catch (DatabaseConnectionException e) {

            log.debug(e.getMessage() + " bs -- ln 44");
        }

        return updatedAccount;
    }


    public int accountDeposit(Client client, double depositAmount) {

        int updatedAccount = 0;
        try {
            updatedAccount = bankDao.accountDeposit(client, depositAmount);
        } catch (DatabaseConnectionException e) {

            log.debug(e.getMessage() + " bs -- ln 44");
        }

        return updatedAccount;
    }

    public Client getClientInfo(Client client) {
        Client clientInfo = null;
        try {
            clientInfo = bankDao.getClientInfo(client);
        } catch (DatabaseConnectionException e) {

            e.printStackTrace();
        }
        return clientInfo;
    }

    public int transferFunds(Client sender, Client recipient, double transferAmount) {
        int count = 0;
        try(Connection connection = ConnectionUtil.getConnection()) {
            
        } catch (Exception e) {

        }
        return count;
    }
}
