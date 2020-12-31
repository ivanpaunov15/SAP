package com.ivan.ShopManagement.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "representatives")
public class SalesRepresentative implements User{
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotEmpty
    @NotNull
    @Size(min=2,message = "Username should be longer than 2 characters")
    @Column(name = "name")
    private String name;

    @NotEmpty
    @NotNull
    @Size(min=8,message = "Password should be longer than 8 characters")
    @Column(name = "password")
    private String password;

    @NotNull
    @Email
    @Column(name = "email")
    private String mail;

    private final String role = "REPRESENTATIVE";
    public SalesRepresentative(){

    }
    public SalesRepresentative(String name,String password, String mail) {
        this.name = name;
        this.password = password;
        this.mail = mail;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public long getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getRole() {
        return this.role;
    }

    @Override
    public void setEmail(String mail) {
        this.mail = mail;
    }

    @Override
    public String getEmail() {
        return this.mail;
    }
}

