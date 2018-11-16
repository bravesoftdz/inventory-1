package com.personiv.model;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class Accountability {
	private Long id;
	private String serialNumber;
	private String assetNumber;
	private String assetType;
	private String attachment;
	private String remarks;
	private Date createdAt;
	private Date updatedAt;
}
