package com.revature.dao.employeeDao;

import java.util.List;

import com.revature.exceptions.DatabaseConnectionException;
import com.revature.model.BankAccount;
import com.revature.model.Client;
import com.revature.model.Employee;

public interface EmployeeDao {
    public int approveBankAccount(BankAccount pendingAccount) throws DatabaseConnectionException;
    public int cleanPendingAccount(BankAccount pendingAccount) throws DatabaseConnectionException;
    public int denyBankAccount(BankAccount pendingAccount) throws DatabaseConnectionException;
    public List<BankAccount> getPendingApplications() throws DatabaseConnectionException;
    public BankAccount getApplicationInfo(BankAccount AccountNumber) throws DatabaseConnectionException;
    public BankAccount getPendingApplicationsByClientUsername(Client client) throws DatabaseConnectionException;
    public boolean getLoginVerification(Employee employee) throws DatabaseConnectionException;
    public Employee getAccountInfo(Employee employee) throws DatabaseConnectionException;
    public List<Client> getAllClients() throws DatabaseConnectionException;
    public Client getClientById(int id) throws DatabaseConnectionException;
    public Client getClientByUserName(Client username) throws DatabaseConnectionException;
}
