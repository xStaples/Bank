package com.revature.dao.employeeDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.exceptions.DatabaseConnectionException;
import com.revature.model.BankAccount;
import com.revature.model.Client;
import com.revature.model.Employee;
import com.revature.util.ConnectionUtil;

import org.apache.log4j.Logger;

public class EmployeeDaoImpl implements EmployeeDao {

    public static Logger log = Logger.getLogger(EmployeeDaoImpl.class);

    @Override
    public int approveBankAccount(BankAccount pendingAccount) {
        int count = 0;

        try (Connection connection = ConnectionUtil.getConnection()) {

            String sql = "INSERT INTO bank.bankaccounts (account_number, account_type, account_owner, account_balance)"
                    + "VALUES(?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, pendingAccount.getAccountNum());
            preparedStatement.setString(2, pendingAccount.getAccountType());
            preparedStatement.setString(3, pendingAccount.getAccountOwner());
            preparedStatement.setDouble(4, pendingAccount.getAccountBalance());

            count = preparedStatement.executeUpdate();

        } catch (SQLException | DatabaseConnectionException e) {
            log.debug("CBA -- EDI -- " + e.getMessage());
            log.info("Something went wrong with the database--");
        }
        return count;
    }

    public int cleanPendingAccount(BankAccount pendingAccount) {
        int count = 0;

        try (Connection connection = ConnectionUtil.getConnection()) {
            String sql = "DELETE FROM bank.pendingAccounts WHERE account_number = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, pendingAccount.getAccountNum());
            count = preparedStatement.executeUpdate();

        } catch (SQLException | DatabaseConnectionException e) {
            log.debug(e.getMessage());
        }
        return count;
    }

    @Override
    public int denyBankAccount(BankAccount pendingAccount) throws DatabaseConnectionException {
        int count = 0;

        try (Connection connection = ConnectionUtil.getConnection()) {
            String sql = "DELETE FROM bank.pendingAccounts WHERE account_number = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, pendingAccount.getAccountNum());

            count = preparedStatement.executeUpdate();
            log.info(count + " count at denyaccount EDI");
        } catch (SQLException | DatabaseConnectionException e) {
            log.debug("DBA -- EDI -- " + e.getMessage());
            log.info("Something went wrong with the database--");
        }
        return count;
    }

    @Override
    public List<Client> getAllClients() throws DatabaseConnectionException {
        List<Client> allClientsList = new ArrayList<>();

        try (Connection connection = ConnectionUtil.getConnection()) {
            String sql = "SELECT * FROM bank.clients FULL JOIN bank.bankAccounts ON bank.clients.client_username = bank.bankaccounts.account_owner";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            
            if (rs.next()) {
                do {
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

                    Client client = new Client(id, firstName, lastName, age, gender, clientUsername, accountNumber, accountType,accountBalance, accountId);
                    allClientsList.add(client);
                } while (rs.next());
                
                log.debug(allClientsList+" EDAO");
            } else {
                log.info("No Clients Found");
            }
        } catch (Exception e) {
            log.info("Error connecting to database.");
            log.debug(e.getMessage());
        }

        return allClientsList;
    }

    @Override
    public Client getClientById(int id) throws DatabaseConnectionException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Client getClientByUserName(Client username) throws DatabaseConnectionException {

        Client client = null;

        try (Connection connection = ConnectionUtil.getConnection()) {
            String sql = "SELECT * FROM bank.clients FULL JOIN bank.bankaccounts ON bank.clients.client_username = bank.bankaccounts.account_owner WHERE bank.clients.client_username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username.getUsername());
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

                client = new Client(id, firstName, lastName, age, gender, clientUsername, accountNumber, accountType,
                        accountBalance, accountId);

            }

        } catch (Exception e) {
            log.debug(e.getMessage());
        }

        return client;
    }

    @Override
    public boolean getLoginVerification(Employee employee) {
        Employee employeeCheck = null;

        try (Connection connection = ConnectionUtil.getConnection()) {
            String sql = "SELECT employee_username, password FROM bank.employees WHERE employee_username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, employee.getUsername());
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                String employeeUsername = rs.getString("employee_username");
                String employeePassword = rs.getString("password");

                employeeCheck = new Employee(employeeUsername, employeePassword);
            }

        } catch (SQLException | DatabaseConnectionException e) {
            log.debug(e.getMessage());
        }

        return (employee.equals(employeeCheck));
    }

    @Override
    public Employee getAccountInfo(Employee employee) throws DatabaseConnectionException {
        Employee employeeInfo = null;
        try (Connection connection = ConnectionUtil.getConnection()) {
            String sql = "SELECT * FROM bank.clients WHERE employee_username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, employee.getUsername());
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                int age = rs.getInt("employee_age");
                String gender = rs.getString("employee_gender");
                String username = rs.getString("employee_username");
                String password = rs.getString("password");

                employeeInfo = new Employee(id, firstName, lastName, age, gender, username, password);
                employee = employeeInfo;
            } else {
                log.debug("No user found with that information");
            }
        } catch (SQLException | DatabaseConnectionException e) {
            log.debug(e.getMessage());
        }
        return employee;
    }

    @Override
    public BankAccount getApplicationInfo(BankAccount accountNumber) throws DatabaseConnectionException {
        BankAccount applicationInfo = null;
        log.debug(accountNumber + " top check before connection");
        try (Connection connection = ConnectionUtil.getConnection()) {
            String sql = "SELECT * FROM bank.pendingAccounts WHERE account_number = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, accountNumber.getAccountNum());

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                int accountNum = rs.getInt("account_number");
                String accountType = rs.getString("account_type");
                String accountOwner = rs.getString("account_owner");
                double accountBalance = rs.getDouble("account_balance");

                applicationInfo = new BankAccount(accountNum, accountType, accountOwner, accountBalance);
                log.debug(applicationInfo + " EDAO--gai");

            }
        } catch (SQLException | DatabaseConnectionException e) {
            log.debug(e.getMessage());
        }
        return applicationInfo;
    }

    @Override
    public List<BankAccount> getPendingApplications() {

        List<BankAccount> accountList = new ArrayList<BankAccount>();
        // ListIterator<BankAccount> accIterator = accountList.listIterator();

        try (Connection connection = ConnectionUtil.getConnection()) {
            String sql = "SELECT * FROM bank.pendingAccounts";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                do {
                    int accountNumber = rs.getInt("account_number");
                    String accountType = rs.getString("account_type");
                    String accountOwner = rs.getString("account_owner");
                    double accountBalance = rs.getInt("account_balance");

                    BankAccount pendingApp = new BankAccount(accountNumber, accountType, accountOwner, accountBalance);
                    accountList.add(pendingApp);
                } while (rs.next());
                

            } else {
                log.info("No Pending Applications");
            }
        } catch (Exception e) {
            log.info("Something went wrong with the database -- gpa-l");
            log.debug(e.getMessage());
        }
        return accountList;
    }

    @Override
    public BankAccount getPendingApplicationsByClientUsername(Client client) throws DatabaseConnectionException {

        return null;
    }

}
