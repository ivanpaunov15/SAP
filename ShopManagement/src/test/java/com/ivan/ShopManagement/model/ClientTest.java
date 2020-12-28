package com.ivan.ShopManagement.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {

    @Test
    void setId() {
        Client client = new Client("ivan","test@gmail.com");
        client.setId(1);
        assertEquals(1,client.getId());
    }

    @Test
    void getId() {
        Client client = new Client("ivan","test@gmail.com");
        assertEquals(0,client.getId());
    }

    @Test
    void getName() {
        Client client = new Client("ivan","test@gmail.com");
        assertEquals("ivan",client.getName());
    }

    @Test
    void setName() {
        Client client = new Client("ivan","test@gmail.com");
        client.setName("gosho");
        assertEquals("gosho",client.getName());
    }

    @Test
    void getMail() {
        Client client = new Client("ivan","test@gmail.com");
        assertEquals("test@gmail.com",client.getMail());
    }

    @Test
    void setMail() {
        Client client = new Client("ivan","test@gmail.com");
        client.setMail("test1@gmail.com");
        assertEquals("test1@gmail.com",client.getMail());
    }

    @Test
    public void testDefaultConstructor(){
       Client client = new Client();

        assertNotNull(client);
    }
}