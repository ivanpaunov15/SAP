package com.ivan.ShopManagement.services;

import com.ivan.ShopManagement.dao.SaleRepresentativeRepository;
import com.ivan.ShopManagement.model.SalesRepresentative;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RepresentativesDetailsService implements UserDetailsService {

    @Autowired
    private SaleRepresentativeRepository saleRepresentativeRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        List<SalesRepresentative> usersList = saleRepresentativeRepository.findByName(userName);

        if (usersList != null && usersList.size() == 1) {
            SalesRepresentative salesRepresentative = usersList.get(0);

            List<String> roleList = new ArrayList<String>();
            roleList.add(salesRepresentative.getRole());

            return User.builder()
                    .username(salesRepresentative.getName())
                    //change here to store encoded password in db
                    .password( bCryptPasswordEncoder.encode(salesRepresentative.getPassword()) )
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
