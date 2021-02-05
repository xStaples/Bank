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

    public int createBankAccount(BankAccount bankAccount, Client client) {
        int count = 0;
        try (Connection connection = ConnectionUtil.getConnection()) {

            connection.setAutoCommit(false);
            count = bankDao.createBankAccount(bankAccount, client);
            connection.commit();

        } catch (SQLException | DatabaseConnectionException e) {
            log.debug(e.getMessage());
        }
        return count;
    }
}
