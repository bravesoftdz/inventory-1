package com.personiv.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.personiv.model.User;
import com.personiv.service.UserService;
import com.personiv.utils.JwtTokenUtil;

@RestController
@CrossOrigin(origins = "*")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@RequestMapping(path = "/users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<User> getUsers(){
		List<User> users = userService.getUsers();
		
		for(User user: users) {
			user.setPassword(null);
		}
		return users;
	}
	@RequestMapping(path = "/users", method = RequestMethod.POST, consumes= MediaType.APPLICATION_JSON_VALUE ,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addUser(@RequestBody User user){
		userService.addUser(user);
		return ResponseEntity.ok(user);
	}
	
	@RequestMapping(path = "/test-user", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getClaimsFromToken(String token){
		
		jwtTokenUtil.getUsernameFromToken(token);
		return ResponseEntity.badRequest().body(null);
	}
}
