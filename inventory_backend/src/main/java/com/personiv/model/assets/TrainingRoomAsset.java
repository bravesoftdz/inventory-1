package com.personiv.model.assets;

import java.util.Date;

import com.personiv.model.locations.TrainingRoom;

import lombok.Data;

@Data
public class TrainingRoomAsset {
	private Long id;
	private TrainingRoom location;
	private SystemUnit systemUnit;
	private Monitor monitor1;
	private Monitor monitor2;
	private Keyboard keyboard;
	private Mouse mouse;
	private Headset headset;
	private UPS ups;
	private Date createdAt;
	private Date updatedAt;

}
