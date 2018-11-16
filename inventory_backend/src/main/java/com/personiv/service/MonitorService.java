package com.personiv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personiv.dao.MonitorDao;
import com.personiv.model.assets.Monitor;

@Service
public class MonitorService {
	@Autowired
	private MonitorDao monitorDao;
	
	public void addMonitor(Monitor monitor) {
		monitorDao.addMonitor(monitor);
	}
	public List<Monitor> getMonitors(){
		return monitorDao.getMonitors();
	}
	
	public List<Monitor> getAvailableMonitors(){
		return monitorDao.getAvailableMonitors();
	}

	public void updateMonitor(Monitor monitor) {
		monitorDao.updateMonitor(monitor);
		
	}
}
