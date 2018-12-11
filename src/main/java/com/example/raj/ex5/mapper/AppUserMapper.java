//The  AppUserMapper class is used for mapping the columns in the
//APP_USER table with the fields in the  AppUser class
//(Based on the query statement).

package com.example.raj.ex5.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.raj.ex5.model.AppUser;

public class AppUserMapper implements RowMapper<AppUser> {
	
	
	//query
	public static final String BASE_SQL
					="Select u.User_Id, u.User_Name, u.Encryted_Password From App_User u ";
	
	
	
	@Override
	public AppUser mapRow(ResultSet rs, int rowNum) throws SQLException {

		  Long userId = rs.getLong("User_Id");
	      String userName = rs.getString("User_Name");
	      String encryptedPassword = rs.getString("Encryted_Password");
		
		return new AppUser(userId,userName,encryptedPassword);
	}
	
	
	
	
}
