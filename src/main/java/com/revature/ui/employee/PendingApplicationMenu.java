package com.revature.ui.employee;

import java.util.Iterator;
import java.util.List;

import com.revature.exceptions.DatabaseConnectionException;
import com.revature.model.BankAccount;
import com.revature.services.employeeServices.EmployeeServices;
import com.revature.ui.menu.Menu;

import org.apache.log4j.Logger;

public class PendingApplicationMenu implements Menu {
    private static Logger log = Logger.getLogger(PendingApplicationMenu.class);
    public EmployeeServices employeeServices;
    List<BankAccount> pendingAppsList;

    public PendingApplicationMenu() {
        employeeServices = new EmployeeServices();
    }

    @Override
    public void display() {
        int choice = 0;

        try {
            pendingAppsList = employeeServices.employeeDao.getPendingApplications();
        } catch (DatabaseConnectionException e) {
            e.printStackTrace();
        }

        Iterator<BankAccount> pendingApp = pendingAppsList.iterator();

        do {
            System.out.println();
            System.out.println(" === Pending Applications === ");
            System.out.println("===============================");
            System.out.println();
            System.out.println("1. Get Applications");
            System.out.println("2. Approve Application");
            System.out.println("3. Deny Application");
            System.out.println("4. Back");

            try {
                choice = Integer.parseInt(userInput.nextLine());
            } catch (Exception e) {
                log.debug(e.getMessage());
            }

            switch (choice) {
                case 1:
                    while (pendingApp.hasNext()) {
                        BankAccount application = pendingApp.next();
                        log.info(pendingAppsList);
                    }
                    break;
                case 2:
                    BankAccount approvedAccountNum = accountNumberInput();
                    BankAccount approvedApplication = employeeServices.getApplicationInfo(approvedAccountNum);
                    employeeServices.approveBankAccount(approvedApplication);

                    break;
                case 3:
                    BankAccount deniedAccountNum = accountNumberInput();
                    BankAccount deniedApplication = employeeServices.getApplicationInfo(deniedAccountNum);
                    employeeServices.denyBankAccount(deniedApplication);
                    log.info("Account: " + deniedAccountNum + " has been denied.");
                    break;
                default:
                    break;
            }
        } while (choice != 4);

    }

    public BankAccount accountNumberInput() {
        BankAccount accountNumber = null;
        log.info("Enter Account Number");
        int accountNum = Integer.parseInt(userInput.nextLine());
        accountNumber = new BankAccount(accountNum);
        return accountNumber;
    }
}
