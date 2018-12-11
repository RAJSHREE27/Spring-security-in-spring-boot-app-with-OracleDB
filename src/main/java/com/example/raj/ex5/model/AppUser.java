package com.example.raj.ex5.model;

import lombok.Data;

@Data
public class AppUser {
	
	private long userId;
	private String userName;
	private String encryptedPassword;
	
	
	public AppUser(long userId, String userName, String encryptedPassword) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.encryptedPassword = encryptedPassword;
	}

	@Override
	public String toString() {
        return this.userName + "/" + this.encryptedPassword;
    }
	
}
