package com.revature.dao.bankDao;

import com.revature.exceptions.DatabaseConnectionException;
import com.revature.model.BankAccount;
import com.revature.model.Client;

public interface BankDao {
    public int accountWithdraw(Client client, double withdrawAmount) throws DatabaseConnectionException;
    public int submitClientAccountApp(BankAccount accountApp);
    public Client getClientInfo(Client client) throws DatabaseConnectionException;
    public int accountDeposit(Client client, double withdrawAmount) throws DatabaseConnectionException;
    public int transferFunds(Client sendUser, Client receiveUser, double transferAmount) throws DatabaseConnectionException;
}
