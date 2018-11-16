package com.personiv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personiv.dao.KeyboardDao;
import com.personiv.model.assets.Keyboard;


@Service
public class KeyboardService {
	@Autowired
	private KeyboardDao kbDao;

	public List<Keyboard> getKeyboards() {

		return kbDao.getKeyboards();
	}	
	
	public Keyboard getKeyboard(Long id) {

		return kbDao.getKeyboard(id);
	}

	public List<Keyboard> getAvailableKeyboards() {
		return kbDao.getAvailableKeyboards();
	}

	public void updateKeyboard(Keyboard keyboard) {
		kbDao.updateKeyboard(keyboard);
		
	}

	public void addKeyboard(Keyboard keyboard) {
		kbDao.addKeyboard(keyboard);
		
	}

	public void deleteKeyboard(Keyboard keyboard) {
		kbDao.deleteKeyboard(keyboard);
		
	}
}
