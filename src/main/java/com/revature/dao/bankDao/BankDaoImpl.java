package com.revature.dao.bankDao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.SQLException;

import com.revature.exceptions.DatabaseConnectionException;
import com.revature.model.BankAccount;
import com.revature.model.Client;
import com.revature.util.ConnectionUtil;

import org.apache.log4j.Logger;

public class BankDaoImpl implements BankDao {
    private static Logger log = Logger.getLogger(BankDaoImpl.class);
    @Override
    public int createBankAccount(BankAccount bankAccount, Client client) {
        int count = 0;

        try (Connection connection = ConnectionUtil.getConnection()) {
            int accountNumber = (int) (Math.random() * (1 - 99999) + 1);
            String sql = "INSERT INTO bank.bankaccounts " + "VALUES(?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, accountNumber);
            preparedStatement.setString(2, bankAccount.getAccountType());
            preparedStatement.setString(3, bankAccount.getAccountOwner());
            preparedStatement.setDouble(4, bankAccount.getAccountBalance());

        } catch (SQLException | DatabaseConnectionException e) {
            log.debug("BDI -- " + e.getMessage());
        }
        return count;
    }

    @Override
    public BankAccount transferMoney(Client sender, Client receiver) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int accountWithdraw(Client client, String accountType, int withdrawAmount) {

        return 0;
    }

}
