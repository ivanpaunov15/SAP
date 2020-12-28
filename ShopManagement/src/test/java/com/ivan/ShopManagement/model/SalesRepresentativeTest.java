package com.ivan.ShopManagement.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SalesRepresentativeTest {

    @Test
    void setId() {
        SalesRepresentative salesRepresentative = new SalesRepresentative("ivan","12345678","test@gmail.com");
        salesRepresentative.setId(1);
        assertEquals(1,salesRepresentative.getId());
    }

    @Test
    void getId() {
        SalesRepresentative salesRepresentative = new SalesRepresentative("ivan","12345678","test@gmail.com");
        assertEquals(0,salesRepresentative.getId());
    }

    @Test
    void getName() {
        SalesRepresentative salesRepresentative = new SalesRepresentative("ivan","12345678","test@gmail.com");
        assertEquals("ivan",salesRepresentative.getName());
    }

    @Test
    void setName() {
        SalesRepresentative salesRepresentative = new SalesRepresentative("ivan","12345678","test@gmail.com");
        salesRepresentative.setName("gosho");
        assertEquals("gosho",salesRepresentative.getName());
    }

    @Test
    void getPassword() {
        SalesRepresentative salesRepresentative = new SalesRepresentative("ivan","12345678","test@gmail.com");
        assertEquals("12345678",salesRepresentative.getPassword());
    }

    @Test
    void setPassword() {
        SalesRepresentative salesRepresentative = new SalesRepresentative("ivan","12345678","test@gmail.com");
        salesRepresentative.setPassword("123456789");
        assertEquals("123456789",salesRepresentative.getPassword());
    }

    @Test
    void getRole() {
        SalesRepresentative salesRepresentative = new SalesRepresentative("ivan","12345678","test@gmail.com");
        assertEquals("REPRESENTATIVE",salesRepresentative.getRole());
    }

    @Test
    void setEmail() {
        SalesRepresentative salesRepresentative = new SalesRepresentative("ivan","12345678","test@gmail.com");
        salesRepresentative.setEmail("test1@gmail.com");
        assertEquals("test1@gmail.com",salesRepresentative.getEmail());
    }

    @Test
    void getEmail() {
        SalesRepresentative salesRepresentative = new SalesRepresentative("ivan","12345678","test@gmail.com");
        assertEquals("test@gmail.com",salesRepresentative.getEmail());
    }

    @Test
    public void testDefaultConstructor(){
        SalesRepresentative salesRepresentative = new SalesRepresentative();

        assertNotNull(salesRepresentative);
    }
}