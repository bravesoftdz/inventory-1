package com.personiv.model.assets;

import java.util.Date;

import lombok.Data;

@Data
public class Software {
	private Long id;
	private String softwareName;
	private String softwareUser;
	private Date createdAt;
	private Date updatedAt;
}
