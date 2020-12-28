package com.ivan.ShopManagement.configuration;

import com.ivan.ShopManagement.services.RepresentativesDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@Order(2)
@EnableWebSecurity
public class RepresentativeConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    RepresentativesDetailsService representativeDetailServiceImpl;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(representativeDetailServiceImpl).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .antMatcher("/representative/**")
                .authorizeRequests()
                .antMatchers("/css/**", "/js/**", "/webjars/**").permitAll()
                .antMatchers("/representative/api/**").hasRole("REPRESENTATIVE")
                .anyRequest().authenticated()
                .and().formLogin().loginPage("/representative/login")
                .defaultSuccessUrl("/representative/api/dashboard", true)
                .failureUrl("/representative/accessdenied")
                .permitAll()
                .and().logout().logoutUrl("/representative/api/logout").logoutSuccessUrl("/")
                .and().exceptionHandling().accessDeniedPage("/representative/accessdenied");
        http.csrf().disable();
    }
}
