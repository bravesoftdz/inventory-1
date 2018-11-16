package com.personiv.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.personiv.model.Role;
import com.personiv.model.User;


@Repository
@Transactional(readOnly = false)
public class UserDao extends JdbcDaoSupport{
	
	private JdbcTemplate jdbcTemplate;

    @Autowired
    private DataSource dataSource;
    
    @Bean
    BCryptPasswordEncoder passwordEncoder() {
    	return new BCryptPasswordEncoder();
    }
    
    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
        jdbcTemplate = getJdbcTemplate();
    }
    
    
	public User getUser(String username, String password) {
		String sql = "SELECT * FROM users WHERE username =? AND password =?";
		User user = jdbcTemplate.queryForObject(sql,new Object[] {username,password}, new BeanPropertyRowMapper<User>(User.class));

		
		return user;
	}


	public User findUserById(Integer id) {
		String sql = "SELECT * FROM users";
		User user = jdbcTemplate.queryForObject(sql,new Object[] {id}, new BeanPropertyRowMapper<User>(User.class));
	
		return user;
	}
	public List<User>getUsers(){
		String query ="SELECT * FROM users";
		List<User> users =jdbcTemplate.query(query,new BeanPropertyRowMapper<User>(User.class));
	
		return users;
	}


	public User getUserByUsername(String username) {
		String sql = "SELECT * FROM users WHERE username = ?";
		User user = jdbcTemplate.queryForObject(sql,new Object[] {username}, new BeanPropertyRowMapper<User>(User.class));	
		
		
		return user;
	}


	public List<Role> getRoles(Integer id) {
		String query ="Call _proc_getUserRolesById(?)";
		List<Role> roles =jdbcTemplate.query(query,new Object[] {id},new BeanPropertyRowMapper<Role>(Role.class));
		return roles;
	}


	public void addUser(User user) {
		String sql ="INSERT INTO users(fullName,username,password) VALUES(?,?,?)";
		jdbcTemplate.update(sql,new Object[] {
			user.getFullName(),
			user.getUsername(),
			passwordEncoder().encode(user.getPassword())
		});
		
	}


	
}
