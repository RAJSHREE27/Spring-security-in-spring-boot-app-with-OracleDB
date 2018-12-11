/*
 * DAO (Data Access Object) classes are ones used to 
 * access to a database, for example, Query, Insert, Update, Delete. 
 * The  DAO classes are usually annotated by @Repository to tell
 *  the Spring "let's manage them as Spring BEANs".
The  AppUserDAO class is used to manipulate with the APP_USER table. 
It has a method for finding an user in the database corresponding to an username.
 */

package com.example.raj.ex5.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.raj.ex5.mapper.AppUserMapper;
import com.example.raj.ex5.model.AppUser;

@Repository
@Transactional
public class AppUserDAO extends JdbcDaoSupport {
	
	@Autowired
	public AppUserDAO(DataSource dataSource) {
		this.setDataSource(dataSource);
	}
	
	public AppUser findUserAccount(String userName) {
		String sql = AppUserMapper.BASE_SQL + "where u.User_Name = ? ";
		
		Object[] params = new Object[] {userName};
		AppUserMapper mapper = new AppUserMapper();
		
		try {
			AppUser userInfo =this.getJdbcTemplate().queryForObject(sql, params, mapper);
			return userInfo;
		}catch(EmptyResultDataAccessException e) {
			return null;
		}
		
	}
}
