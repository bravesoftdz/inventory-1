package com.personiv.model;

import java.util.List;

import com.personiv.model.assets.Keyboard;
import com.personiv.model.assets.Monitor;
import com.personiv.model.assets.Mouse;
import com.personiv.model.assets.SystemUnit;

import lombok.Data;

@Data
public class NonProdAssetLocation {
	private NonProdLocation location;
	private List<NonProdSystemUnit> systemUnits;
	private List<NonProdMonitor> monitors;
	private List<NonProdKeyboard> keyboards;
	private List<NonProdMouse> mice;
	private List<NonProdUps> ups;
}
