/*UserDetailsService means a central interface in Spring Security. It is a service 
 * to search "User account and such user's roles". It is used by the  Spring Security 
 * everytime when users log in the system. Therefore, you need 
 * to write a class to implement this interface.
 */

/*UserDetailsServiceImpl class which implements the UserDetailsService interface.
 *  The  UserDetailsServiceImpl class is annotated by   @Service to tell the 
 *   Spring "let's manage it as a Spring BEAN".
 */

package com.example.raj.ex5.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.raj.ex5.dao.AppRoleDAO;
import com.example.raj.ex5.dao.AppUserDAO;
import com.example.raj.ex5.model.AppUser;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	 	@Autowired
	    private AppUserDAO appUserDAO;
	 
	    @Autowired
	    private AppRoleDAO appRoleDAO;
	 
	    @Override
	    public UserDetails loadUserByUsername(String userName)throws UsernameNotFoundException{
	    	
	    	AppUser appUser = this.appUserDAO.findUserAccount(userName);
	    	
	    	 if (appUser == null) {
	             System.out.println("User not found! " + userName);
	             throw new UsernameNotFoundException("User " + userName + " was not found in the database");
	         }
	    	   System.out.println("Found User: " + appUser);
	    	   // [ROLE_USER, ROLE_ADMIN,..]
	           List<String> roleNames = this.appRoleDAO.getRoleNames(appUser.getUserId());
	    
	           List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
	           
	           
	           if (roleNames != null) {
	               for (String role : roleNames) {
	                   // ROLE_USER, ROLE_ADMIN,..
	                   GrantedAuthority authority = new SimpleGrantedAuthority(role);
	                   grantList.add(authority);
	               }
	           }
	           
	           UserDetails userDetails = (UserDetails) new User(appUser.getUserName(), //
	                   appUser.getEncryptedPassword(), grantList);
	           
	           return userDetails;
	   }
	    
}
