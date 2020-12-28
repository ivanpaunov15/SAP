package com.ivan.ShopManagement.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdministratorTest {

    @Test
    void setId() {
        Administrator administrator = new Administrator("ivan","12345678","test@gmail.com");
        administrator.setId(1);
        assertEquals(1,administrator.getId());
    }

    @Test
    void getId() {
        Administrator administrator = new Administrator("ivan","12345678","test@gmail.com");
        assertEquals(0,administrator.getId());
    }

    @Test
    void getName() {
        Administrator administrator = new Administrator("ivan","12345678","test@gmail.com");
        assertEquals("ivan",administrator.getName());
    }

    @Test
    void setName() {
        Administrator administrator = new Administrator("ivan","12345678","test@gmail.com");
        administrator.setName("gosho");
        assertEquals("gosho",administrator.getName());
    }

    @Test
    void getPassword() {
        Administrator administrator = new Administrator("ivan","12345678","test@gmail.com");
        assertEquals("12345678",administrator.getPassword());
    }

    @Test
    void setPassword() {
        Administrator administrator = new Administrator("ivan","12345678","test@gmail.com");
        administrator.setPassword("123456789");
        assertEquals("123456789",administrator.getPassword());
    }

    @Test
    void getRole() {
        Administrator administrator = new Administrator("ivan","12345678","test@gmail.com");
        assertEquals("ADMIN",administrator.getRole());
    }

    @Test
    void setEmail() {
        Administrator administrator = new Administrator("ivan","12345678","test@gmail.com");
        administrator.setEmail("test1@gmail.com");
        assertEquals("test1@gmail.com",administrator.getEmail());
    }

    @Test
    void getEmail() {
        Administrator administrator = new Administrator("ivan","12345678","test@gmail.com");
        assertEquals("test@gmail.com",administrator.getEmail());
    }

    @Test
    public void testDefaultConstructor(){
        Administrator administrator = new Administrator();

        assertNotNull(administrator);
    }
}