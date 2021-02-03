package com.revature.dao.clientDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.exceptions.DatabaseConnectionException;
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
    public boolean getLoginVerification(Client client){
        
        Client clientCheck = null;

        try (Connection connection = ConnectionUtil.getConnection()){
            String sql = "SELECT client_username, password FROM bank.clients WHERE client_username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, client.getUsername());
            ResultSet rs = preparedStatement.executeQuery();

            if(rs.next()){
                String clientUsername = rs.getString("client_username");
                String clientPassword = rs.getString("password");

                clientCheck = new Client(clientUsername, clientPassword);
                
            }
            
        } catch (SQLException | DatabaseConnectionException e) {
            log.debug(e.getMessage());
        }
        
        
        
        return (client.equals(clientCheck));
    }
    @Override
    public Client applyAccount(int newBalance, String accountName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Client checkBalance(String username, int accountId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Client makeDeposit(String accountName, int depositAmount) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Client makeWithdraw(String accountName, int withdrawAmount) {
        // TODO Auto-generated method stub
        return null;
    }

}
