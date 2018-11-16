package com.personiv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personiv.dao.TrainingRoomDao;
import com.personiv.model.locations.TrainingRoom;

@Service
public class TrainingRoomService {
	
	@Autowired
	private TrainingRoomDao trDao;
	
	
	public List<TrainingRoom> getTrainingRoom(){
		return trDao.getTrainingRooms();
	}
	
	public void addTrainingRoom(TrainingRoom room){
		 trDao.addTrainingRoom(room);
	}

	public void updateTrainingRoom(TrainingRoom room) {
		trDao.updateTrainingRoom(room);
		
	}
}
