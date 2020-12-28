package com.ivan.ShopManagement.model.details;

import com.ivan.ShopManagement.model.Administrator;
import com.ivan.ShopManagement.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UsersDetailsTest {

    @Test
    void getAuthorities() {
        User administrator = new Administrator("Ivan","12345678","test@gmail.com");
        UsersDetails usersDetails = new UsersDetails(administrator);

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority("ADMIN"));
        authorities.add(new SimpleGrantedAuthority("REPRESENTATIVE"));


        assertEquals(authorities,usersDetails.getAuthorities());
    }

    @Test
    void getPassword() {
        User administrator = new Administrator("Ivan","12345678","test@gmail.com");
        UsersDetails usersDetails = new UsersDetails(administrator);

        assertEquals("12345678",usersDetails.getPassword());
    }

    @Test
    void getUsername() {
        User administrator = new Administrator("Ivan","12345678","test@gmail.com");
        UsersDetails usersDetails = new UsersDetails(administrator);
        assertEquals("Ivan",usersDetails.getUsername());
    }

    @Test
    void isAccountNonExpired() {
        User administrator = new Administrator("Ivan","12345678","test@gmail.com");
        UsersDetails usersDetails = new UsersDetails(administrator);
        assertEquals(true,usersDetails.isAccountNonExpired());
    }

    @Test
    void isAccountNonLocked() {
        User administrator = new Administrator("Ivan","12345678","test@gmail.com");
        UsersDetails usersDetails = new UsersDetails(administrator);
        assertEquals(true,usersDetails.isAccountNonLocked());
    }

    @Test
    void isCredentialsNonExpired() {
        User administrator = new Administrator("Ivan","12345678","test@gmail.com");
        UsersDetails usersDetails = new UsersDetails(administrator);
        assertEquals(true,usersDetails.isCredentialsNonExpired());
    }

    @Test
    void isEnabled() {
        User administrator = new Administrator("Ivan","12345678","test@gmail.com");
        UsersDetails usersDetails = new UsersDetails(administrator);
        assertEquals(true,usersDetails.isEnabled());
    }
}