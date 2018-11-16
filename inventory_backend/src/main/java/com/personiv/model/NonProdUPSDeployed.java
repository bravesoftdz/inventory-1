package com.personiv.model;
import com.personiv.model.assets.UPS;

import lombok.Data;

@Data
public class NonProdUPSDeployed {
	private NonProdLocation location;
	private UPS ups;
	
}
