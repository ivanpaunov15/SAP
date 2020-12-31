package com.ivan.ShopManagement.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;


@Entity
@Table(name="sales")
public class Sale {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    @NotNull
    @Column(name="id_representative")
    private long salesRepresentativeID;

    @NotEmpty
    @NotNull
    @Size(min=2,message = "Username should be longer than 3 characters")
    @Column(name = "representative")
    private String salesRepresentative;

    @NotEmpty
    @NotNull
    @Size(min=3,message = "Client should be longer than 3 characters")
    @Column(name = "client")
    private String client;

    @NotEmpty
    @NotNull
    @Size(min=3,message = "Product should be longer than 3 characters")
    @Column(name = "product")
    private String product;


    @NotNull
    @Column(name = "price")
    private double price;

    @NotNull
    @Column(name = "quantity")
    private int quantity;

    @Column(name = "date")
    private Date date;

    public Sale(){

    }

    public Sale(long salesRepresentativeID, String salesRepresentative, String client, String product, double price, int quantity, Date date) {
        this.salesRepresentativeID = salesRepresentativeID;
        this.salesRepresentative = salesRepresentative;
        this.client = client;
        this.product = product;
        this.price = price;
        this.quantity = quantity;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSalesRepresentativeID() {
        return salesRepresentativeID;
    }

    public void setSalesRepresentativeID(long salesRepresentativeID) {
        this.salesRepresentativeID = salesRepresentativeID;
    }

    public String getSalesRepresentative() {
        return salesRepresentative;
    }

    public void setSalesRepresentative(String salesRepresentative) {
        this.salesRepresentative = salesRepresentative;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
