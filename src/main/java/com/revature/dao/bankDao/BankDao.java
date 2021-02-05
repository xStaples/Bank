package com.revature.dao.bankDao;

import com.revature.exceptions.DatabaseConnectionException;
import com.revature.model.BankAccount;
import com.revature.model.Client;

public interface BankDao {
    public int createBankAccount(BankAccount bankAccount, Client client) throws DatabaseConnectionException;
    public BankAccount transferMoney(Client sender, Client receiver);
    public int accountWithdraw(Client client, String accountType, int withdrawAmount);
}
