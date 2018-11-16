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

import com.personiv.model.assets.TrainingRoomAsset;
import com.personiv.model.locations.TrainingRoom;
import com.personiv.service.TrainingAssetService;

@RestController
@CrossOrigin(origins = "*")
public class TrainingAssetController {

	@Autowired
	private TrainingAssetService trainingAssetService;
	
	@RequestMapping(value = "/training_assets", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<TrainingRoomAsset> getTrainingAssets(){
		return trainingAssetService.getTrainingAssets();
	}
	
	
	@RequestMapping(value = "/training_assets", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addTrainingRoomAsset(@RequestBody TrainingRoomAsset trainingAsset){
		trainingAssetService.addTrainingRoomAsset(trainingAsset);
		return ResponseEntity.ok(trainingAsset);
	}
	
	
	
}
