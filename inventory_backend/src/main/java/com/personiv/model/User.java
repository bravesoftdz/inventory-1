package com.personiv.model;

import java.sql.Date;
import java.util.Set;

import lombok.Data;

@Data
public class User {
	private Integer id;
	private String fullName;
	private String username;
	private String password;
	private String role;
	private boolean accountNonLocked;
	private boolean accountNonExpired;
	private boolean credentialsNonExpired;
	private boolean enabled;
	private Date createdAt;
	private Date updatedAt;
}
