package com.revature.ui.menu;

import com.revature.model.Client;
import com.revature.services.clientServices.ClientServices;
import com.revature.ui.login.Login;

import org.apache.log4j.Logger;

public class MainMenu implements Menu{
    private static Logger log = Logger.getLogger(MainMenu.class);
    public ClientServices cs;
    public MainMenu(){
        cs = new ClientServices();
    }
    


    @Override
    public void display() {
        int choice = 0;

        do {
            System.out.println();
            System.out.println(" ===Main Menu=== ");
            System.out.println("==================");
            System.out.println();
            System.out.println("1. Log in");
            System.out.println("2. New Customer");
            System.out.println("3. About");
            System.out.println("9. Exit");

            try {
                choice = Integer.parseInt(userInput.nextLine());
            } catch (NumberFormatException e) {
                log.debug(e.getMessage());
            }

            switch (choice) {
                case 1:
                    Menu loginMenu = new Login();
                    loginMenu.display();
                    break;
                case 2:
                    Client client = getNewClientInput();
                    cs.createClient(client);
                    break;
                case 3:
                    Menu aboutMenu = new AboutMenu();
                    aboutMenu.display();
                default:
                    break;
            }
        } while (choice != 9);

    }
    

    public Client getNewClientInput(){
        Client client = null;
        while(true){
            System.out.println("Enter first name: ");
            String firstName = Menu.userInput.nextLine();
            System.out.println("Enter last name: ");
            String lastName = Menu.userInput.nextLine();
            System.out.println("Enter Age: ");
            int age = Integer.parseInt(Menu.userInput.nextLine());
            System.out.println("Enter Gender: ");
            String gender = Menu.userInput.nextLine();
            System.out.println("Create a username: ");
            String username = Menu.userInput.nextLine();
            System.out.println("Create a password: ");
            String password = Menu.userInput.nextLine();

            client = new Client(firstName, lastName, age, gender, username, password);
            return client;
        }
        
    }
}
