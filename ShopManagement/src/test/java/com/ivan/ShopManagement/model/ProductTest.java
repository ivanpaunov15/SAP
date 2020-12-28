package com.ivan.ShopManagement.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    void setId() {
        Product product = new Product("kubo",10.5,5,"white");
        product.setId(1);
        assertEquals(1,product.getId());
    }

    @Test
    void getId() {
        Product product = new Product("kubo",10.5,5,"white");
        assertEquals(0,product.getId());
    }

    @Test
    void getName() {
        Product product = new Product("kubo",10.5,5,"white");
        assertEquals("kubo",product.getName());
    }

    @Test
    void setName() {
        Product product = new Product("kubo",10.5,5,"white");
        product.setName("mbot");
        assertEquals("mbot",product.getName());
    }

    @Test
    void getPrice() {
        Product product = new Product("kubo",10.5,5,"white");
        assertEquals(10.5,product.getPrice());
    }

    @Test
    void setPrice() {
        Product product = new Product("kubo",10.5,5,"white");
        product.setPrice(11.5);
        assertEquals(11.5,product.getPrice());
    }

    @Test
    void getQuantity() {
        Product product = new Product("kubo",10.5,5,"white");
        assertEquals(5,product.getQuantity());
    }

    @Test
    void setQuantity() {
        Product product = new Product("kubo",10.5,5,"white");
        product.setQuantity(6);
        assertEquals(6,product.getQuantity());
    }

    @Test
    void getDescription() {
        Product product = new Product("kubo",10.5,5,"white");
        assertEquals("white",product.getDescription());
    }

    @Test
    void setDescription() {
        Product product = new Product("kubo",10.5,5,"white");
        product.setDescription("black");
        assertEquals("black",product.getDescription());
    }
    @Test
    public void testDefaultConstructor(){
        Product product = new Product();

        assertNotNull(product);
    }
}