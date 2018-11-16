package com.personiv.model.locations;

import java.util.Date;

import lombok.Data;

@Data
public class ItdRoom {
	private Long id;
	private String name;
	private Date createdAt;
	private Date updatedAt;
}
