package com.revature.dao.clientDao;

import java.util.List;

import com.revature.exceptions.DatabaseConnectionException;
import com.revature.model.BankAccount;
import com.revature.model.Client;

public interface ClientDao {
    public int createClient(Client client) throws DatabaseConnectionException;
    public boolean getLoginVerification(Client client) throws DatabaseConnectionException;
    public Client getAccountInfo(Client client);
    public List<BankAccount> checkBalance(Client username) throws DatabaseConnectionException;

}
