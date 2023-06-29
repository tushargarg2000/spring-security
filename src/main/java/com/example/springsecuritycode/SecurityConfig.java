package com.example.springsecuritycode;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {


    @Bean
    //User information
    public UserDetailsService userDetailsService(){

//        UserDetails admin = User.withUsername("tushar")
//                .password(encoder.encode("Pwd1"))
//                .roles("ADMIN")
//                .build();
//
//        UserDetails user = User.withUsername("amit")
//                .password(encoder.encode("Pwd2"))
//                .roles("USER","ADMIN","HR")
//                .build();
//
//        System.out.println("The admin User details are"+admin);
//        System.out.println("The user User details are"+user);
        return new UserInfoUserDetailService();
        //return new InMemoryUserDetailsManager(admin,user);
    }

    //Second API : talks about to which all API we need to give security
    //And which all API should not be given security
    //By default all of them are given security

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)throws Exception{

        return httpSecurity.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("home/welcome","/home/user/add").permitAll()
                .and()
                .authorizeHttpRequests().requestMatchers("/home/**")
                .authenticated().and().formLogin().and().build();

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //Converting your password into encryption format and storing them
    //Vice versa also it manages : retreive the password from the db to check (it coverts back also)
    @Bean
    public AuthenticationProvider authenticationProvider(){

        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();

        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());

        return authenticationProvider;
    }


}
