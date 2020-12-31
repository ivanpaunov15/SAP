package com.ivan.ShopManagement.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = "clients")
public class Client {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotEmpty
    @NotNull
    @Size(min=3,message = "Username should be longer than 3 characters")
    @Column(name = "name")
    private String name;

    @NotNull
    @NotEmpty
    @Email
    @Column(name = "email")
    private String mail;

    public Client(){ }

    public Client(String name, String mail) {
        this.name = name;
        this.mail = mail;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
