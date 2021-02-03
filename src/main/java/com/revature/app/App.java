package com.revature.app;

import com.revature.dao.clientDao.ClientDao;
import com.revature.dao.clientDao.ClientDaoImpl;
import com.revature.exceptions.DatabaseConnectionException;
import com.revature.model.Client;
import com.revature.ui.menu.MainMenu;

import org.apache.log4j.Logger;

public class App {
    private static Logger log = Logger.getLogger(App.class);

    public static void main(String[] args) {
        MainMenu mainMenu = new MainMenu();
        mainMenu.display();
    }
}
