package com.personiv.model;

import java.util.Date;

import lombok.Data;

@Data
public class NonProdLocation {
	private Long id;
	private String locationName;
	private Date createdAt;
	private Date updatedAt;
}
