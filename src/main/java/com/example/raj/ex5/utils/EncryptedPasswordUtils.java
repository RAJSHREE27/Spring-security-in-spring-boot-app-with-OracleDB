package com.example.raj.ex5.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncryptedPasswordUtils {
	
	public static String encyptePassword(String password) {
		
		 BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	        return encoder.encode(password);
		
	}
	
	 public static void main(String[] args) {
	        String password = "123";
	        String encrytedPassword = encyptePassword(password);
	 
	        System.out.println("Encryted Password: " + encrytedPassword);
	    }
	
	
}
