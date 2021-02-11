package com.revature.ui.employee;

import java.util.List;

import com.revature.model.Client;
import com.revature.services.employeeServices.EmployeeServices;
import com.revature.ui.menu.Menu;

import org.apache.log4j.Logger;

public class ClientSearchMenu implements Menu {
    private static Logger log = Logger.getLogger(ClientSearchMenu.class);
    EmployeeServices employeeServices;

    public ClientSearchMenu() {
        employeeServices = new EmployeeServices();

    }

    @Override
    public void display() {
        int choice = 0;

        do {
            System.out.println();
            System.out.println(" === Client Search === ");
            System.out.println("========================");
            System.out.println();
            System.out.println("1. Search by Client ID");
            System.out.println("2. Search by Client Username");
            System.out.println("3. Get all Client Accounts");
            System.out.println("4. Back");

            try {
                choice = Integer.parseInt(userInput.nextLine());
            } catch (Exception e) {
                log.info("Invalid input. Please, try again.");
                log.debug(e.getMessage());
            }

            switch (choice) {
                case 1:

                    break;

                case 2:
                    Client usernameResult = null;
                    Client username = getClientUsernameInput();
                    usernameResult = employeeServices.getClientByUserName(username);
                    System.out.println();
                    System.out.println(" === RESULTS === ");
                    System.out.println("==================");
                    System.out.println();
                    log.info(usernameResult);

                    log.debug("Results: " + usernameResult);
                    break;

                case 3:
                    List<Client> allClients = employeeServices.getAllClients();
                    for (Client client : allClients) {
                        log.info(client);
                    }
                    
                    break;
                default:
                    break;
            }
        } while (choice != 4);

    }

    public Client getClientUsernameInput() {
        Client client = null;
        System.out.println("Enter Client Username: ");
        String username = userInput.nextLine();
        client = new Client(username);
        return client;
    }

}
