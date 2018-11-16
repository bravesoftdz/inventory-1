package com.personiv.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.personiv.model.locations.TrainingRoom;
import com.personiv.service.TrainingRoomService;

@RestController
@CrossOrigin(origins = "*")
public class TrainingRoomController {
	
	@Autowired
	private TrainingRoomService trService;
	
	@RequestMapping(value = "/training_rooms", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getTrainingRooms(){
		return ResponseEntity.ok(trService.getTrainingRoom());
	}
	
	@RequestMapping(value = "/training_rooms", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addTrainingRoom(@RequestBody TrainingRoom room){
		trService.addTrainingRoom(room);
		return ResponseEntity.ok(room);
	}
	
	@RequestMapping(value = "/training_rooms", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateTrainingRoom(@RequestBody TrainingRoom room){
		trService.updateTrainingRoom(room);
		return ResponseEntity.ok(room);
	}
	
}
