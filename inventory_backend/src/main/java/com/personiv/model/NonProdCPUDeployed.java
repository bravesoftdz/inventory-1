package com.personiv.model;

import com.personiv.model.assets.SystemUnit;

import lombok.Data;

@Data
public class NonProdCPUDeployed {
	private NonProdLocation location;
	private SystemUnit cpu;
	
}
