package com.personiv.model;

import com.personiv.model.assets.Monitor;
import com.personiv.model.assets.SystemUnit;

import lombok.Data;

@Data
public class NonProdMonitorDeployed {
	private NonProdLocation location;
	private Monitor monitor;
}
