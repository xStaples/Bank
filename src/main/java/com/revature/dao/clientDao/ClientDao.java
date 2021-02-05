package com.revature.dao.clientDao;

import com.revature.exceptions.DatabaseConnectionException;
import com.revature.model.Client;

public interface ClientDao {
    public int createClient(Client client) throws DatabaseConnectionException;
    public boolean getLoginVerification(Client client);
    public Client applyAccount(int newBalance, String accountName);
    public Client getAccountInfo(Client client);
    public Client checkBalance(String username, int accountId);
    public Client makeDeposit (String accountName, int depositAmount);
    public Client makeWithdraw (String accountName, int withdrawAmount);

}
