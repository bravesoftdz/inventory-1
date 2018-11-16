package com.personiv.mapextractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.personiv.model.PortConfig;
import com.personiv.model.Switch;
import com.personiv.model.SwitchConfig;

public class SwitchConfigMapExtractor implements ResultSetExtractor <List<SwitchConfig>>{

	@Override
	public List<SwitchConfig> extractData(ResultSet rs) throws SQLException, DataAccessException {
		
		Map<Long, SwitchConfig> switchMap = new HashMap<>();
		
		while(rs.next()) {
			Long id = rs.getLong("id");
			Long switchId = rs.getLong("switchId");
			String portPanel = rs.getString("panel");
			String switchName = rs.getString("switchName");
			Integer switchPort = rs.getInt("switchPort");
			String vlan = rs.getString("vlan");
			
			String trunkSwitch = rs.getString("trunkName");
			Integer trunkPort = rs.getInt("trunkPort");
			String department = rs.getString("department");
			String remarks = rs.getString("remarks");
			
			
			Switch source = new Switch();
			source.setName(switchName);
			source.setId(switchId);
			
			SwitchConfig switchConfig = switchMap.get(switchId);
			
			PortConfig  portConfig = new PortConfig();
			
			
			portConfig.setSwitchPort(switchPort);
			portConfig.setPortPanel(portPanel);
			portConfig.setVlan(vlan);
			portConfig.setTrunkPort(trunkPort);
			portConfig.setDepartment(department);
			portConfig.setRemarks(remarks);
			
			
			
			if(switchConfig == null) {
				switchConfig = new SwitchConfig();
				switchConfig.setId(id);
				
				switchMap.put(switchId,switchConfig);
			}
			
			List<PortConfig> portConfigs = switchConfig.getPortConfigs();
			
			if(portConfigs == null) {
				portConfigs = new ArrayList<PortConfig>();
				switchConfig.setPortConfigs(portConfigs);
			}
			
			if(trunkSwitch != null) {
				Switch trSwitch = new Switch();
				trSwitch.setName(trunkSwitch);
				portConfig.setTrunkSwitch(trSwitch);
			}
			
			portConfigs.add(portConfig);
			
			Switch sw = new Switch();
			sw.setName(switchName);

			switchConfig.setSource(source);
			
		}
		return new ArrayList<SwitchConfig>(switchMap.values());
	}

}
