package com.example.ecommerce.config;



import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class Config {
    @Autowired
    EntityManager entityManager;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {



        http.authorizeHttpRequests(config ->
                config
                        .requestMatchers("/", "/login", "/**.css", "/images/**", "/about-us", "/Create-account" , "/shop").permitAll() // Public pages
                        .anyRequest().authenticated()


                )
                .formLogin(login ->

                        login
                                .loginPage("/login")
                                .loginProcessingUrl("/authenticateTheUser")
                                .defaultSuccessUrl("/user-page", true)
                                .permitAll()
                )



                .logout(logout -> logout
                       //.logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .permitAll()
                );



        return http.build();
    }

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {

        JdbcUserDetailsManager theUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        theUserDetailsManager.setUsersByUsernameQuery("select email, password, enabled from users where email=?");
        theUserDetailsManager.setAuthoritiesByUsernameQuery("select email, role from users where email=?");
        return theUserDetailsManager;

    }


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



/*

    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        // works well
        UserDetails user1 = User.builder()
                .username("123")
                .password("{noop}123")
                .build();

        UserDetails user2 = User.builder()
                .username("mohamad")
                .password("{noop}mohamad")
                .build();

        return new InMemoryUserDetailsManager(user1, user2);
    }

 */




}
