package com.revature.ui.login;



import com.revature.model.Client;
import com.revature.services.clientServices.ClientServices;
import com.revature.ui.client.ClientMenu;
import com.revature.ui.menu.Menu;

import org.apache.log4j.Logger;

public class ClientLogin implements Menu {
    boolean loginVerification;
    private static Logger log = Logger.getLogger(ClientLogin.class);

    ClientServices cs;

    public ClientLogin(){
        cs = new ClientServices();
    }


//interfaces for bank transactions 
    @Override
    public void display() {
        Client loginInfo = getLoginInput();
    
        loginVerification = cs.getLoginVerification(loginInfo);
        if (loginVerification) {
            Menu clientMenu = new ClientMenu(loginInfo);
            clientMenu.display();
        } else {
            log.info("Username or Password invalid");
        }
        
    }
    
    public Client getLoginInput(){
        System.out.println("Enter Username: ");
        String usernameInput = userInput.nextLine();
        System.out.println("Enter Password: ");
        String passwordInput = userInput.nextLine();
        Client clientInput = new Client(usernameInput, passwordInput);
        return clientInput;
    }
}
