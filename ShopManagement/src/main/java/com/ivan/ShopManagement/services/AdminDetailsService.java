package com.ivan.ShopManagement.services;

import com.ivan.ShopManagement.dao.AdminRepository;
import com.ivan.ShopManagement.model.Administrator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Primary
public class AdminDetailsService implements UserDetailsService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        List<Administrator> usersList = adminRepository.findByName(userName);

        if (usersList != null && usersList.size() == 1) {
             Administrator administrators = usersList.get(0);

            List<String> roleList = new ArrayList<String>();
            roleList.add(administrators.getRole());

            return User.builder()
                    .username(administrators.getName())
                    //change here to store encoded password in db
                    .password( bCryptPasswordEncoder.encode(administrators.getPassword()) )
                    .disabled(false)
                    .accountExpired(false)
                    .accountLocked(false)
                    .credentialsExpired(false)
                    .roles(roleList.toArray(new String[0]))
                    .build();
        } else {
            throw new UsernameNotFoundException("User Name is not Found");
        }
    }
}
