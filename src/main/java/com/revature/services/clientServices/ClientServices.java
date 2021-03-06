package com.revature.services.clientServices;

import java.sql.Connection;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.dao.clientDao.ClientDao;
import com.revature.dao.clientDao.ClientDaoImpl;
import com.revature.exceptions.DatabaseConnectionException;
import com.revature.model.BankAccount;
import com.revature.model.Client;
import com.revature.util.ConnectionUtil;

import org.apache.log4j.Logger;

public class ClientServices {
    private static Logger log = Logger.getLogger(ClientServices.class);
    public ClientDao clientDao;

    public ClientServices() {
        clientDao = new ClientDaoImpl();
    }

    public int createClient(Client client) {
        int count = 0;
        try (Connection connection = ConnectionUtil.getConnection()) {
            connection.setAutoCommit(false);
            count = clientDao.createClient(client);
            connection.commit();
        } catch (SQLException | DatabaseConnectionException e) {
            log.debug(e.getMessage());
        }
        return count;
    }

    public boolean getLoginVerification(Client client) {
        boolean verified = false;
        try {
            verified = clientDao.getLoginVerification(client);
        } catch (DatabaseConnectionException e) {
            e.printStackTrace();
        }
        return verified;
    }

    public Client getAccountInfo(Client client) {
        return clientDao.getAccountInfo(client);
    }

    public List<BankAccount> checkBalances(Client client) {
        List<BankAccount> clientAccounts = new ArrayList<>();

        try {
            clientAccounts = clientDao.checkBalance(client);

        } catch (DatabaseConnectionException e) {
            log.info("Error connecting to database.");
        }
        return clientAccounts;
    }
}
