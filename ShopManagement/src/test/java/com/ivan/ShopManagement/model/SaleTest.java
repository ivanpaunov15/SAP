package com.ivan.ShopManagement.model;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class SaleTest {

    @Test
    void getId(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        Date date = new Date();
        Sale sale = new Sale(1,"Ivan","Georgi","Mbot",10.5,5,date);

        assertEquals(0,sale.getId());
    }

    @Test
    void setId(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        Date date = new Date();
        Sale sale = new Sale(1,"Ivan","Georgi","Mbot",10.5,5,date);
        sale.setId(1);

        assertEquals(1,sale.getId());
    }

    @Test
    void getSalesRepresentativeID() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        Date date = new Date();
        Sale sale = new Sale(1,"Ivan","Georgi","Mbot",10.5,5,date);

        assertEquals(1,sale.getSalesRepresentativeID());
    }

    @Test
    void setSalesRepresentativeID() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        Date date = new Date();
        Sale sale = new Sale(1,"Ivan","Georgi","Mbot",10.5,5,date);
        sale.setSalesRepresentativeID(2);

        assertEquals(2,sale.getSalesRepresentativeID());
    }

    @Test
    void getSalesRepresentative() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        Date date = new Date();
        Sale sale = new Sale(1,"Ivan","Georgi","Mbot",10.5,5,date);

        assertEquals("Ivan",sale.getSalesRepresentative());
    }

    @Test
    void setSalesRepresentative() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        Date date = new Date();
        Sale sale = new Sale(1,"Ivan","Georgi","Mbot",10.5,5,date);
        sale.setSalesRepresentative("Pesho");

        assertEquals("Pesho",sale.getSalesRepresentative());
    }

    @Test
    void getClient() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        Date date = new Date();
        Sale sale = new Sale(1,"Ivan","Georgi","Mbot",10.5,5,date);

        assertEquals("Georgi",sale.getClient());
    }

    @Test
    void setClient() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        Date date = new Date();
        Sale sale = new Sale(1,"Ivan","Georgi","Mbot",10.5,5,date);
        sale.setClient("Martin");

        assertEquals("Martin",sale.getClient());
    }

    @Test
    void getProduct() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        Date date = new Date();
        Sale sale = new Sale(1,"Ivan","Georgi","Mbot",10.5,5,date);

        assertEquals("Mbot",sale.getProduct());
    }

    @Test
    void setProduct() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        Date date = new Date();
        Sale sale = new Sale(1,"Ivan","Georgi","Mbot",10.5,5,date);
        sale.setProduct("Lego");

        assertEquals("Lego",sale.getProduct());
    }

    @Test
    void getPrice() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        Date date = new Date();
        Sale sale = new Sale(1,"Ivan","Georgi","Mbot",10.5,5,date);

        assertEquals(10.5,sale.getPrice());
    }

    @Test
    void setPrice() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        Date date = new Date();
        Sale sale = new Sale(1,"Ivan","Georgi","Mbot",10.5,5,date);
        sale.setPrice(11.5);

        assertEquals(11.5,sale.getPrice());
    }

    @Test
    void getQuantity() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        Date date = new Date();
        Sale sale = new Sale(1,"Ivan","Georgi","Mbot",10.5,5,date);

        assertEquals(5,sale.getQuantity());
    }

    @Test
    void setQuantity() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        Date date = new Date();
        Sale sale = new Sale(1,"Ivan","Georgi","Mbot",10.5,5,date);
        sale.setQuantity(6);

        assertEquals(6,sale.getQuantity());
    }

    @Test
    void getDate() throws ParseException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        Date date = new SimpleDateFormat( "yyyy/MM/dd" ).parse( "2020/12/25" );;
        Sale sale = new Sale(1,"Ivan","Georgi","Mbot",10.5,5,date);

        assertEquals(new SimpleDateFormat( "yyyy/MM/dd" ).parse( "2020/12/25" ),sale.getDate());
    }

    @Test
    void setDate() throws ParseException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        Date date = new Date();
        Sale sale = new Sale(1,"Ivan","Georgi","Mbot",10.5,5,date);
        sale.setQuantity(6);
        sale.setDate(new SimpleDateFormat( "yyyy/MM/dd" ).parse( "2020/12/26" ));

        assertEquals(new SimpleDateFormat( "yyyy/MM/dd" ).parse( "2020/12/26" ),sale.getDate());
    }

    @Test
    public void testDefaultConstructor(){
      Sale sale = new Sale();

        assertNotNull(sale);
    }
}