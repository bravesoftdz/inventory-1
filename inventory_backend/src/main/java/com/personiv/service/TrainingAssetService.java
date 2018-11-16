package com.personiv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personiv.dao.TrainingAssetDao;
import com.personiv.model.assets.TrainingRoomAsset;
import com.personiv.model.locations.TrainingRoom;

@Service
public class TrainingAssetService {
	
	@Autowired
	private TrainingAssetDao trainingAssetDao;
	
	public List<TrainingRoomAsset> getTrainingAssets(){
		return trainingAssetDao.getTrainingRoomAssets();
	}



	public List<TrainingRoom> getTrainingRooms() {
		return trainingAssetDao.getTrainingRooms();
	}

	public void addTrainingRoomAsset(TrainingRoomAsset trainingAsset) {
		trainingAssetDao.addTrainingRoomAsset(trainingAsset);
		
	}
}
