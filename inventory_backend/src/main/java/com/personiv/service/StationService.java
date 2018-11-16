package com.personiv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personiv.dao.StationDao;
import com.personiv.model.locations.Station;

@Service
public class StationService {
	
	
	@Autowired
	private StationDao stationDao;
	
	public void addStation(Station station) {
		stationDao.addStation(station);
	}

	public List<Station> getStations() {
		return stationDao.getStations();
	}
	
	public void updateStation(Station station) {
		stationDao.updateStation(station);
	}
	
}
