package com.personiv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personiv.dao.MouseDao;
import com.personiv.model.assets.Mouse;
@Service
public class MouseService {

	@Autowired
	private MouseDao mouseDao;
	
	public List<Mouse> getMouses() {
		return mouseDao.getMice();
	}

	public List<Mouse> getAvailableMouses() {
		return mouseDao.getAvailableMice();
	}

	public void updateMouse(Mouse mouse) {
		mouseDao.updateMouse(mouse);
	}

	public void addMouse(Mouse mouse) {
		mouseDao.addMouse(mouse);
	}

}
