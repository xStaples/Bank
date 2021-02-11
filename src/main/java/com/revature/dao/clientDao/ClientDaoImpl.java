package com.revature.dao.clientDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.exceptions.DatabaseConnectionException;
import com.revature.model.BankAccount;
import com.revature.model.Client;
import com.revature.util.ConnectionUtil;

import org.apache.log4j.Logger;

public class ClientDaoImpl implements ClientDao {
    private static Logger log = Logger.getLogger(ClientDaoImpl.class);

    @Override
    public int createClient(Client client) throws DatabaseConnectionException {
        int createCount = 0;

        try (Connection connection = ConnectionUtil.getConnection()) {
            String sql = "INSERT INTO bank.clients (first_name, last_name, client_age, client_gender, client_username, password)"
                    + "VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, client.getFirstName());
            preparedStatement.setString(2, client.getLastName());
            preparedStatement.setInt(3, client.getAge());
            preparedStatement.setString(4, client.getGender());
            preparedStatement.setString(5, client.getUsername());
            preparedStatement.setString(6, client.getPassword());

            createCount = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseConnectionException("Something went wrong connecting to the Database");
        }
        return createCount;
    }

    @Override
    public boolean getLoginVerification(Client client) {

        Client clientCheck = null;

        try (Connection connection = ConnectionUtil.getConnection()) {
            String sql = "SELECT client_username, password FROM bank.clients WHERE client_username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, client.getUsername());
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                String clientUsername = rs.getString("client_username");
                String clientPassword = rs.getString("password");

                clientCheck = new Client(clientUsername, clientPassword);

            }

        } catch (SQLException | DatabaseConnectionException e) {
            log.debug(e.getMessage());
        }

        return (client.equals(clientCheck));
    }


    public Client getAccountInfo(Client client){
        Client clientInfo = null;
        try (Connection connection = ConnectionUtil.getConnection()) {
            String sql = "SELECT * FROM bank.clients WHERE client_username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, client.getUsername());
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                int age = rs.getInt("client_age");
                String gender = rs.getString("client_gender");
                String username = rs.getString("client_username");
                String password = rs.getString("password");
                

                clientInfo = new Client(id, firstName, lastName, age, gender, username, password);
                client = clientInfo;
            } else {
                log.debug("No user found with that information");
            }
        } catch (Exception e) {
            //TODO: handle exception
        }
        return client;
    }


    @Override
    public List<BankAccount> checkBalance(Client client) {
        List<BankAccount> clientAccounts = new ArrayList<>();

        try (Connection connection = ConnectionUtil.getConnection()) {
            String sql = "SELECT * FROM bank.bankAccounts WHERE account_owner = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, client.getUsername());
            ResultSet rs = preparedStatement.executeQuery();
            
            if (rs.next()) {
                do {
                    String accountOwner = rs.getString("account_owner");
                    int accountNumber = rs.getInt("account_number");
                    String accountType = rs.getString("account_type");
                    double accountBalance = rs.getDouble("account_balance");


                    BankAccount clientAccount = new BankAccount (accountNumber, accountType, accountOwner, accountBalance);
                    clientAccounts.add(clientAccount);
                } while (rs.next());
                
                log.debug(clientAccounts+" EDAO");
            } else {
                log.info("No Clients Found");
            }
        } catch (Exception e) {
            log.info("Error connecting to database.");
            log.debug(e.getMessage());
        }

        return clientAccounts;
    }

}
