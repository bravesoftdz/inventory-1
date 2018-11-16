package com.personiv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personiv.dao.SwitchConfigDao;
import com.personiv.model.SwitchConfig;
import com.personiv.model.SwitchPortConfig;

@Service
public class SwitchConfigService {
	@Autowired
	private SwitchConfigDao switchConfDao;
	
	public void addSwitchConfig(SwitchConfig config) {
		switchConfDao.addSwitchConfig(config);
	}

	public List<SwitchConfig> getSwitchConfigs() {
		return switchConfDao.getSwitchConfigs();
	}

	public void updateSwitchConfig(SwitchPortConfig config) {
		switchConfDao.updateSwitchConfig(config);
		
	}
}
