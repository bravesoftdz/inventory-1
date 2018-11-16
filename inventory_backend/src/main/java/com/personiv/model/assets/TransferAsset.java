package com.personiv.model.assets;

import java.util.Date;

import com.personiv.model.Department;
import com.personiv.model.locations.ProductionArea;

import lombok.Data;

@Data
public class TransferAsset {
	private Long fromProdAssetId; //row id of production assets table source
	private Long toProdAssetId; //row id of production assets table destination
	private ProductionArea fromLocation;
	private ProductionArea toLocation;
	private SystemUnit systemUnit;
	private Monitor monitorA;
	private Monitor monitorB;
	private Keyboard keyboard;
	private Mouse mouse;
	private UPS ups;
	private Date createdAt;
	private Date updatedAt;
}
