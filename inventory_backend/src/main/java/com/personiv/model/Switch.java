package com.personiv.model;

import java.util.Date;

import com.personiv.model.assets.Model;

import lombok.Data;

@Data
public class Switch {
	private Long id;
	private String name;
	private String serialNumber;
	private String assetNumber;
	private String macAddress;
	private Model model;
	private String ipAddress;
	private Date createdAt;
	private Date updatedAt;
	
}
