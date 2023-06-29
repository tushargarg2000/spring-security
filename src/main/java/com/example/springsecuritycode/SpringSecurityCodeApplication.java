package com.example.springsecuritycode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SpringSecurityCodeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityCodeApplication.class, args);

		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		System.out.println(passwordEncoder.encode("password1"));
		System.out.println(passwordEncoder.encode("tushar123"));

	}



}
