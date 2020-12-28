package com.ivan.ShopManagement.model;

public interface User {
    public void setId(long id);

    public long getId();

    public String getName();

    public void setName(String name);

    public String getPassword();

    public void setPassword(String password);

    public String getRole();

    public void setEmail(String mail);

    public String getEmail();
}
