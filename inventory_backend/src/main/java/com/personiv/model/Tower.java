package com.personiv.model;

import java.util.Date;

import lombok.Data;

@Data
public class Tower {
	private Long id;
	private String towerName;
	private Date createdAt;
	private Date updatedAt;
}
