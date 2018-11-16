package com.personiv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personiv.dao.NonProdLocationDao;
import com.personiv.model.NonProdLocation;

@Service
public class NonProdLocationService {
	
	@Autowired
	private NonProdLocationDao npDao;
	
	public List<NonProdLocation> getNonProdLocations(){
		return npDao.getNonProdLocations();
	}
	
	public void addNonProdLocation(NonProdLocation npLoc) {
		npDao.addNonProdLocation(npLoc);
	}
	
	public void updateNonProdLocation(NonProdLocation npLoc) {
		npDao.updateNonProdLocation(npLoc);
	}
}
