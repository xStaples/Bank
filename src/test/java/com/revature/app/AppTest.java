package com.revature.app;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.revature.dao.employeeDao.EmployeeDao;
import com.revature.dao.employeeDao.EmployeeDaoImpl;
import com.revature.model.Client;
import com.revature.services.bankServices.BankServices;
import com.revature.services.clientServices.ClientServices;
import com.revature.services.employeeServices.EmployeeServices;



class AppTest {

    EmployeeServices employeeServices = new EmployeeServices();
    BankServices bankServices = new BankServices();
    ClientServices clientServices = new ClientServices();
    Client client = new Client();

        
    @Test
    void testCreate(){
		assertEquals(0, clientServices.createClient(client));
    }

    @Test
    void testDeposit(){
        //Both Work. First one will return debug console to try again.
        assertEquals(0, bankServices.accountDeposit(client, -21));
        assertEquals(0, bankServices.accountDeposit(client, 25));
    }
    @Test
    void testWithdraw(){
        //Both Work. First one will return debug console to try again.
        assertEquals(0, bankServices.accountWithdraw(client, -21));
        assertEquals(0, bankServices.accountWithdraw(client, 25)); 
    }

    
}


