package com.revature.services.employeeServices;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.dao.employeeDao.EmployeeDao;
import com.revature.dao.employeeDao.EmployeeDaoImpl;
import com.revature.exceptions.DatabaseConnectionException;
import com.revature.model.BankAccount;
import com.revature.model.Client;
import com.revature.model.Employee;
import com.revature.util.ConnectionUtil;

import org.apache.log4j.Logger;

public class EmployeeServices {

    public EmployeeDao employeeDao;
    private static Logger log = Logger.getLogger(EmployeeServices.class);

    public EmployeeServices() {
        employeeDao = new EmployeeDaoImpl();
    }

    public int approveBankAccount(BankAccount pendingAccount) {
        int approved = 0;
        int updPendDB = 0;
        try (Connection connection = ConnectionUtil.getConnection()) {
            connection.setAutoCommit(false);
            approved = employeeDao.approveBankAccount(pendingAccount);
            updPendDB = employeeDao.cleanPendingAccount(pendingAccount);
            connection.commit();

        } catch (SQLException | DatabaseConnectionException e) {
            log.info("Something went wrong connecting to database. Please, try again.");
            log.debug(e.getMessage());
        }
        return approved;
    }

    public int denyBankAccount(BankAccount deniedAccountNum) {
        int count = 0;

        try (Connection connection = ConnectionUtil.getConnection()) {
            connection.setAutoCommit(false);
            count = employeeDao.denyBankAccount(deniedAccountNum);
            connection.commit();

        } catch (SQLException | DatabaseConnectionException e) {
            log.info("Something went wrong connecting to database. Please, try again.");
            log.debug(e.getMessage());
        }

        return count;
    }

    public boolean getLoginVerification(Employee loginInfo) {
        boolean verified = false;
        try {
            verified = employeeDao.getLoginVerification(loginInfo);
        } catch (DatabaseConnectionException e) {

            log.debug("Log in verification failed" + e.getMessage());
        }
        return verified;
    }

    public BankAccount getApplicationInfo(BankAccount application) {
        BankAccount applicationInfo = null;
        try {
            applicationInfo = employeeDao.getApplicationInfo(application);
            log.debug("ES--gai--" + applicationInfo);

        } catch (DatabaseConnectionException e) {
        }

        return applicationInfo;
    }

    public Client getClientByUserName(Client username) {
        Client client = null;

        try {
            client = employeeDao.getClientByUserName(username);
            log.debug(client + "ES -- gcbun");
        } catch (DatabaseConnectionException e) {

            e.printStackTrace();
        }

        return client;
    }

    public List<Client> getAllClients() {
        List<Client> clientList = new ArrayList<>();

        try {
            clientList = employeeDao.getAllClients();

        } catch (DatabaseConnectionException e) {
            log.info("Error connecting to database.");
        }

        return clientList;
    }
}
