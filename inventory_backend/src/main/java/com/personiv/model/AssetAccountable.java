package com.personiv.model;

import java.util.Date;

import lombok.Data;

@Data
public class AssetAccountable {
	private Long accId;
	private String employeeName;
	private String employeeNumber;
	private Accountability accountability;
}
