package com.revature.services.clientServices;

import java.sql.Connection;

import java.sql.SQLException;

import com.revature.dao.clientDao.ClientDao;
import com.revature.dao.clientDao.ClientDaoImpl;
import com.revature.exceptions.DatabaseConnectionException;
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
        try (Connection connection = ConnectionUtil.getConnection()){
            connection.setAutoCommit(false);
            count = clientDao.createClient(client);
            connection.commit();
        } catch (SQLException | DatabaseConnectionException e) {
            log.debug(e.getMessage());
        }
        return count;
    }

    public boolean getLoginVerification(Client client){
        return clientDao.getLoginVerification(client);
    }
    


    public Client checkBalances(String username, int accountId){
        return null;
    }
}
