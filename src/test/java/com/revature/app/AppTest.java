package com.revature.app;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.revature.model.Client;

class AppTest {

    @Test
    void testApp() {
        assertEquals(1, 1);

    }
}

class newUserTest{
    @Test
    void testNewUser(){
        Client client = new Client();
        client.setFirstName("Dalton");
        assertEquals(client, client);
    }
}
