package com.personiv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personiv.dao.UPSDao;
import com.personiv.model.assets.UPS;


@Service
public class UPSService {
	@Autowired
	private UPSDao upsDao;

	public List<UPS> getUPSs() {

		return upsDao.getUPSs();
	}	
	
	public UPS getUPS(Long id) {

		return upsDao.getUPS(id);
	}

	public List<UPS> getAvailableUPS() {
		return upsDao.getAvailableUPS();
	}

	public void updateUPS(UPS ups) {
		upsDao.updateUPS(ups);
		
	}

	public void addUPS(UPS ups) {
		upsDao.addUPS(ups);
		
	}

	public void deleteUPS(UPS ups) {
		upsDao.deleteUPS(ups);
		
	}
}
