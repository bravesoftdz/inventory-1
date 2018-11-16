package com.personiv.model.assets;

import java.util.Date;

import lombok.Data;
@Data
public class Asset {
	private Long id;
	private String description;
	private String serialNumber;
	private String assetNumber;
	private String status;
	private Model model;
	private Date warrantyExp;
	private String remarks;
	private Date createdAt;
	private Date updatedAt;
}
