package com.personiv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personiv.dao.LocationDao;
import com.personiv.model.locations.ProductionArea;

@Service
public class LocationService {
	
	@Autowired
	private LocationDao locDao;
	
	public List<ProductionArea> getLocations(){
		return locDao.getLocations();
	}
	
	public ProductionArea getLocation(Long id){
		return locDao.getLocation(id);
	}
	
	public void addLocation(ProductionArea station) {
		locDao.addLocation(station);
	}
	
	public void editLocation(ProductionArea station) {
		locDao.editLocation(station);
	}
	public void deleteLocation(Long id) {
		locDao.deleteLocation(id);
	}

	public List<ProductionArea> searchStation(String search) {
		return locDao.searchStation(search);
	}



	public List<ProductionArea> getAvailableLocations() {
		return locDao.getAvailableLocations();
	}
}
