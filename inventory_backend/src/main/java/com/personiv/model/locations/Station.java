package com.personiv.model.locations;

import java.util.Date;

import com.personiv.model.Department;
import com.personiv.model.Tower;

import lombok.Data;

@Data
public class Station {
	
	private Long id;
	private String stationName;
	private String department;
	private String tower;
	private Date createdAt;
	private Date updatedAt;
}
