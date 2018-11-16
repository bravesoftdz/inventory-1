package com.personiv.model.assets;

import java.util.Date;

import lombok.Data;

@Data
public class RFID {
	private Long id;
	private String badgeNumber;
	private String assetNumber;
	private Date createdAt;
	private Date updatedAt;
}
