package com.personiv.model;

import lombok.Data;

@Data
public class PortConfig {
	private Integer switchPort;
	private String portPanel;
	private String vlan;
	private Switch trunkSwitch;
	private Integer trunkPort;
	private String department;
	private String remarks;
}
