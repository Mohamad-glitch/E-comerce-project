package com.example.ecommerce.config;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class Config {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(config ->
                config
                        .requestMatchers("/", "/login", "/**.css", "/images/**", "/about-us").permitAll() // Public pages
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






}
