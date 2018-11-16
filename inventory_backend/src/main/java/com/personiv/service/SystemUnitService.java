package com.personiv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personiv.dao.SystemUnitDao;
import com.personiv.model.ChartData;
import com.personiv.model.PieChartData;
import com.personiv.model.assets.SystemUnit;

@Service
public class SystemUnitService {
	@Autowired
	private SystemUnitDao systemUnitDao;
	
	public List<SystemUnit> getSystemUnits(){
		return systemUnitDao.getSystemUnits();
	}

	public List<SystemUnit> getAvailableSystemUnits() {
		return systemUnitDao.getAvailableSystemUnits();
	}

	public void addSystemmUnit(SystemUnit systemUnit) {
		systemUnitDao.addSystemUnit(systemUnit);		
	}

	public void updateSystemUnit(SystemUnit systemUnit) {
		systemUnitDao.updateSystemUnit(systemUnit);
		
	}
	
	public SystemUnit getSystemUnit(Long id) {
		return systemUnitDao.getSystemUnit(id);
		
	}

	public List<PieChartData> getSystemUnitsGroupByDepartment() {
		return systemUnitDao.getSystemUnitsGroupByDepartment();
	}
	public List<PieChartData> getSystemUnitsGroupByModel() {
		return systemUnitDao.getSystemUnitsGroupByModel();
	}

	public SystemUnit getSystemUnitByAssetTag(String assetTag) {
		return systemUnitDao.getSystemUnitByAssetTag(assetTag);
	}

	public List<ChartData> getSystemUnitsChartByModel() {
		return systemUnitDao.getSystemUnitsChartByModel();
	}
}
