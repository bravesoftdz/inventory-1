package com.personiv.model.assets;

import java.util.Date;

import lombok.Data;

@Data
public class SystemUnitSoftware {
	private Long id;
	private SystemUnit systemUnit;
	private Software software;
	private Date createdAt;
	private Date updatedAt;
}
