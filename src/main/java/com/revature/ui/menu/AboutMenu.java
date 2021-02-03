package com.revature.ui.menu;

import org.apache.log4j.Logger;

public class AboutMenu implements Menu{
    private static Logger log = Logger.getLogger(AboutMenu.class);
    @Override
    public void display() {
        int choice = 0;
        do {
            System.out.println(" === About MBS ===");
            System.out.println("====================");
            System.out.println();
            System.out.println("Money Bags Staples (MBS) is a fictional bank. If you deposit your money here, I don't know why but thanks for the money!");
            System.out.println();
            System.out.println("9. Exit");
            
            try {
                choice = Integer.parseInt(userInput.nextLine());
            } catch (NumberFormatException e) {
                log.info("Invalid input. Please input valid number.");
            }

            
        } while (choice != 9);
        


    }
    
}
