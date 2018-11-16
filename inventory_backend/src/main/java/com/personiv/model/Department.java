package com.personiv.model;

import java.util.Date;

import lombok.Data;

@Data
public class Department {
	private Long id;
	private String name;
	private Date createdAt;
	private Date updatedAt;
}
