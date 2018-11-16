package com.personiv.model.assets;

import java.util.Date;

import com.personiv.model.locations.ItdRoom;

import lombok.Data;

@Data
public class ItdAsset {
	private Long id;
	private ItdRoom location;
	private SystemUnit systemUnit;
	private Monitor monitor1;
	private Monitor monitor2;
	private Keyboard keyboard;
	private Mouse mouse;
	private UPS ups;
	private Date createdAt;
	private Date updatedAt;

}
