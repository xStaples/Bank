package com.revature.dao.bankDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.exceptions.DatabaseConnectionException;
import com.revature.model.BankAccount;
import com.revature.model.Client;
import com.revature.util.ConnectionUtil;

import org.apache.log4j.Logger;

public class BankDaoImpl implements BankDao {
    private static Logger log = Logger.getLogger(BankDaoImpl.class);

    @Override
    public int accountWithdraw(Client client, double withdrawAmount) {

        int updatedBalance = 0;

        if (withdrawAmount <= 0) {
            log.info("Invalid Withdraw Amount. Please try again.");

        } else {
            try (Connection connection = ConnectionUtil.getConnection()) {
                String sql = "UPDATE bank.bankAccounts SET account_balance = ? WHERE account_owner = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setDouble(1, client.getAccountBalance() - withdrawAmount);
                preparedStatement.setString(2, client.getUsername());

                updatedBalance = preparedStatement.executeUpdate();

            } catch (SQLException | DatabaseConnectionException e) {
                log.debug(e.getMessage());
            }
        }
        return updatedBalance;
    }

    @Override
    public int accountDeposit(Client client, double depositAmount) {
        int updatedBalance = 0;
        if (depositAmount <= 0) {
            log.info("Invalid Deposit Amount. Please try again.");

        } else {

            double deposit = client.getAccountBalance() + depositAmount;

            log.debug(deposit + " accountDeposit ln 52");

            try (Connection connection = ConnectionUtil.getConnection()) {
                String sql = "UPDATE bank.bankAccounts SET account_balance = ? WHERE account_owner = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setDouble(1, deposit);
                preparedStatement.setString(2, client.getUsername());

                updatedBalance = preparedStatement.executeUpdate();

            } catch (SQLException | DatabaseConnectionException e) {
                log.debug(e.getMessage());
            }

            // return updatedBalance;
        }
        return updatedBalance;
    }

    @Override
    public int submitClientAccountApp(BankAccount accountInfo) {
        int count = 0;
        try (Connection connection = ConnectionUtil.getConnection()) {

            int accountNumber = (int) (Math.random() * (99999) + 1);

            String sql = "INSERT INTO bank.pendingAccounts (account_status, account_number, account_type, account_owner, account_balance)"
                    + "VALUES (?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "Pending");
            preparedStatement.setInt(2, accountNumber);
            preparedStatement.setString(3, accountInfo.getAccountType());
            preparedStatement.setString(4, accountInfo.getAccountOwner());
            preparedStatement.setDouble(5, accountInfo.getAccountBalance());

            count = preparedStatement.executeUpdate();

        } catch (SQLException | DatabaseConnectionException e) {
            log.debug("GAI -- BDI -- " + e.getMessage());
            log.info("Something went wrong with the database--");
        }

        return count;
    }

    @Override
    public Client getClientInfo(Client client) throws DatabaseConnectionException {
        Client clientInfo = null;

        try (Connection connection = ConnectionUtil.getConnection()) {
            String sql = "SELECT * FROM bank.clients FULL JOIN bank.bankAccounts ON bank.clients.client_username = bank.bankaccounts.account_owner WHERE clients.client_username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, client.getUsername());
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                int age = rs.getInt("client_age");
                String gender = rs.getString("client_gender");
                String clientUsername = rs.getString("client_username");
                int accountNumber = rs.getInt("account_number");
                String accountType = rs.getString("account_type");
                double accountBalance = rs.getDouble("account_balance");
                int accountId = rs.getInt("account_id");

                clientInfo = new Client(id, firstName, lastName, age, gender, clientUsername, accountNumber,
                        accountType, accountBalance, accountId);

            }
        } catch (Exception e) {
            log.debug(e.getMessage());
        }
        return clientInfo;
    }

    @Override
    public int transferFunds(Client sendUser, Client receiveUser, double transferAmount) {
        int count = 0;

        try (Connection connection = ConnectionUtil.getConnection()) {
            String sql = "INSERT INTO bank.transfers (sender_account, receiver_account, transfer_amount)"
                    + "VALUES (?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, sendUser.getUsername());
            preparedStatement.setString(2, receiveUser.getUsername());
            preparedStatement.setDouble(3, transferAmount);

        } catch (SQLException | DatabaseConnectionException e) {

        }
        return count;
    }
}
