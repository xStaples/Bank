package com.revature.dao.bankDao;

import com.revature.model.BankAccount;
import com.revature.model.Client;

public interface BankDao {
    public int createBankAccount(BankAccount bankAccount);
    public BankAccount transferMoney(Client sender, Client receiver);
}
