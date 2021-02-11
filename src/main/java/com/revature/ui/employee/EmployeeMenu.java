package com.revature.ui.employee;

import com.revature.model.Employee;
import com.revature.services.bankServices.BankServices;
import com.revature.services.clientServices.ClientServices;
import com.revature.services.employeeServices.EmployeeServices;
import com.revature.ui.menu.Menu;

import org.apache.log4j.Logger;

public class EmployeeMenu implements Menu {

    private static Logger log = Logger.getLogger(EmployeeMenu.class);
    public ClientServices clientServices;
    public BankServices bankServices;
    public EmployeeServices employeeServices;

    public EmployeeMenu(Employee employee){
        super();
        clientServices = new ClientServices();
        bankServices = new BankServices();
        employeeServices = new EmployeeServices();
    }

    
    @Override
    public void display() {
        int choice = 0;
        
        do {
            System.out.println();
            System.out.println(" ===Employee Menu === ");
            System.out.println(" ===================== ");
            System.out.println();
            System.out.println("1. Account Tools");
            System.out.println("2. Client Search");
            System.out.println("3. Logout");

            try {
                choice = Integer.parseInt(userInput.nextLine());
            } catch (Exception e) {
                log.debug(e.getMessage());
            }
            switch (choice) {
                case 1:
                    Menu pendingAppMenu = new PendingApplicationMenu();
                    pendingAppMenu.display();
                    break;
                case 2:
                    Menu clientSearchMenu = new ClientSearchMenu();
                    clientSearchMenu.display();
                    break;
            
                default:
                    break;
            }
        } while (choice != 3);
            
        

    }
    
}
