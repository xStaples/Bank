package com.revature.ui.login;

import com.revature.model.Employee;
import com.revature.services.employeeServices.EmployeeServices;
import com.revature.ui.employee.EmployeeMenu;
import com.revature.ui.menu.Menu;

import org.apache.log4j.Logger;

public class EmployeeLogin implements Menu {
    private static Logger log = Logger.getLogger(EmployeeLogin.class);
    boolean loginVerification;
    EmployeeServices es;

    public EmployeeLogin() {
        es = new EmployeeServices();
    }

    @Override
    public void display() {
        Employee loginInfo = getLoginInput();

        loginVerification = es.getLoginVerification(loginInfo);
        if (loginVerification) {
            Menu employeeMenu = new EmployeeMenu(loginInfo);
            employeeMenu.display();
        } else {
            log.info("Invalid Username or Password. Please Try again");
            log.debug("Invalid Username/Password -- Username: " + loginInfo.getUsername() + "-- Password: "
                    + loginInfo.getPassword());
        }

    }

    public Employee getLoginInput() {
        System.out.println("Enter Username: ");
        String usernameInput = userInput.nextLine();
        System.out.println("Enter Password: ");
        String passwordInput = userInput.nextLine();
        Employee employeeInput = new Employee(usernameInput, passwordInput);
        return employeeInput;
    }
}
