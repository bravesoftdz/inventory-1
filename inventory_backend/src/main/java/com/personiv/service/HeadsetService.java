package com.personiv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personiv.dao.HeadsetDao;
import com.personiv.model.assets.Headset;


@Service
public class HeadsetService {
	@Autowired
	private HeadsetDao hsDao;

	public List<Headset> getHeadsets() {

		return hsDao.getHeadsets();
	}	
	
	public Headset getHeadset(Long id) {

		return hsDao.getHeadset(id);
	}

	public List<Headset> getAvailableHeadsets() {
		return hsDao.getAvailableHeadsets();
	}

	public void updateHeadset(Headset keyboard) {
		hsDao.updateHeadset(keyboard);
		
	}

	public void addHeadset(Headset keyboard) {
		hsDao.addHeadset(keyboard);
		
	}

	public void deleteHeadset(Headset keyboard) {
		hsDao.deleteHeadset(keyboard);
		
	}
}
