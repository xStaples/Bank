package com.revature.ui.login;

import com.revature.ui.menu.Menu;

import org.apache.log4j.Logger;

public class Login implements Menu {

    private static Logger log = Logger.getLogger(Login.class);
    
    
    @Override
    public void display() {
        int choice = 0;
        do {
            System.out.println(" ===Login=== ");
            System.out.println("==============");
            System.out.println();
            System.out.println("1. Employee");
            System.out.println("2. Client");
            System.out.println("9. Back");

            try {
                choice = Integer.parseInt(userInput.nextLine());
            } catch (NumberFormatException e) {
                log.info("Invalid user input. Please input a valid number.");
            }

            switch (choice) {
                case 1:
                    Menu employeeLogin = new EmployeeLogin();
                    employeeLogin.display();
                    break;
                
                case 2:
                    Menu clientLogin = new ClientLogin();
                    clientLogin.display();
                    break;
                
                default:
                    log.info("Invalid selection. Please try again.");
            }
        } while (choice != 9);

    }

}
