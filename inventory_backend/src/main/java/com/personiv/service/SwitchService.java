package com.personiv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personiv.dao.SwitchDao;
import com.personiv.model.Switch;

@Service
public class SwitchService {
	
	@Autowired
	private SwitchDao switchDao;
	
	public List<Switch> getSwitches(){
		return switchDao.getSwitches();
	}
	
	public Switch getSwitch(Long id) {
		return switchDao.getSwitch(id);
		
	}
	
	public void addSwitch(Switch sw) {
		switchDao.addSwitch(sw);
	}
	
	public void editSwitch(Switch sw) {
		switchDao.editSwitch(sw);
	}
	
	public void deleteSwitch(Long id) {
		switchDao.deleteSwitch(id);
	}
}
