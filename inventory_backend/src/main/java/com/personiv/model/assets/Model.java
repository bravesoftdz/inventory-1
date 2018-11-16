package com.personiv.model.assets;

import java.util.Date;

import lombok.Data;

@Data
public class Model {
	private Long id;
	private String name;
	private Date createdAt;
	private Date updatedAt;
}
