package com.personiv.mapextractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.personiv.model.NonProdAssetLocation;
import com.personiv.model.NonProdKeyboard;
import com.personiv.model.NonProdLocation;
import com.personiv.model.NonProdMonitor;
import com.personiv.model.NonProdMouse;
import com.personiv.model.NonProdSystemUnit;
import com.personiv.model.NonProdUps;
import com.personiv.model.assets.Keyboard;
import com.personiv.model.assets.Monitor;
import com.personiv.model.assets.Mouse;
import com.personiv.model.assets.SystemUnit;
import com.personiv.model.assets.UPS;

public class NonProdAssetMapExtractor implements ResultSetExtractor <List<NonProdAssetLocation>>{

	@Override
	public List<NonProdAssetLocation> extractData(ResultSet rs) throws SQLException, DataAccessException {
		
		Map<Long, NonProdAssetLocation> nonProdMap = new HashMap<>();
		
		
		
		NonProdLocation location = null;
		
		
		List<NonProdSystemUnit> nonProdCpus = null;
		List<NonProdMonitor> nonProdMonitor = null;
		List<NonProdKeyboard> nonProdKeyboard = null;
		List<NonProdMouse> nonProdMouse= null;
		List<NonProdUps> nonProdUps = null;
		
		
		SystemUnit cpu = null;
		Monitor monitor = null;
		Keyboard keyboard = null;
		Mouse mouse = null;
		UPS ups = null;
		
		while(rs.next()) {
			
			Long locId = rs.getLong("locId");
			String locName = rs.getString("locName");
			
		
			
			Long cpuId = rs.getLong("cpuId");
			Long monitorId = rs.getLong("monitorId");
			Long keyboardId = rs.getLong("keyboardId");
			Long mouseId = rs.getLong("mouseId");
			Long upsId = rs.getLong("upsId");
			
			NonProdAssetLocation npLoc = nonProdMap.get(locId);
			
			NonProdSystemUnit npCpu = null;//cpuMap.get(locId);
			
			location = new NonProdLocation();
			location.setId(locId);
			location.setLocationName(locName);
			
			if(npLoc == null) {
				
				npLoc = new NonProdAssetLocation();
				
				
				
				nonProdMonitor = new ArrayList<>();
				nonProdKeyboard = new ArrayList<>();
				nonProdMouse = new ArrayList<>();
				nonProdUps = new ArrayList<>();
				nonProdCpus  = new ArrayList<>();
				npLoc.setSystemUnits(nonProdCpus);
				npLoc.setMonitors(nonProdMonitor);
				npLoc.setKeyboards(nonProdKeyboard);
				npLoc.setMice(nonProdMouse);
				npLoc.setUps(nonProdUps);

				npLoc.setLocation(location);
				nonProdMap.put(locId, npLoc);
				
			}
			
		
		
			

			if(cpuId != 0) {
				
				cpu = new SystemUnit();
				cpu.setId(rs.getLong("cpuId"));
				cpu.setAssetNumber(rs.getString("cpuAsset"));
				
				npCpu = new NonProdSystemUnit();
				npCpu.setId(rs.getLong("nCpuId"));
				npCpu.setSystemUnit(cpu);
				
				if(!npLoc.getSystemUnits().contains(npCpu)) {
					npLoc.getSystemUnits().add(npCpu);
				}
				
				
			}
			
			if(monitorId !=0) {
				monitor = new Monitor();
				monitor.setId(rs.getLong("monitorId"));
				monitor.setAssetNumber(rs.getString("monitorAsset"));
				
				NonProdMonitor npMon = new NonProdMonitor();
				npMon.setId(rs.getLong("nMonitorId"));
				npMon.setMonitor(monitor);
				
				if(!npLoc.getMonitors().contains(npMon)) {
					npLoc.getMonitors().add(npMon);
				}
				
				
			}
			
			if(keyboardId != 0) {
				keyboard = new Keyboard();
				keyboard.setId(rs.getLong("keyboardId"));
				keyboard.setAssetNumber(rs.getString("keyboardAsset"));
				
				NonProdKeyboard npKb = new NonProdKeyboard();
				npKb.setId(rs.getLong("nKeyboardId"));
				npKb.setKeyboard(keyboard);
				
				if(!npLoc.getKeyboards().contains(npKb))
					npLoc.getKeyboards().add(npKb);
				
				
			}
			
			if(mouseId != 0) {
				mouse = new Mouse();
				mouse.setId(mouseId);
				mouse.setAssetNumber(rs.getString("mouseAsset"));
				
				NonProdMouse npMouse = new NonProdMouse();
				npMouse.setId(rs.getLong("nMouseId"));
				npMouse.setMouse(mouse);
				
				if(!npLoc.getMice().contains(npMouse))
					npLoc.getMice().add(npMouse);
			}
			
			if(upsId != 0) {
				
				ups = new UPS();
				ups.setId(rs.getLong("upsId"));
				ups.setAssetNumber(rs.getString("upsAsset"));
				
				NonProdUps npUps = new NonProdUps();
				npUps.setId(rs.getLong("nUpsId"));
				npUps.setUps(ups);
				
				if(!npLoc.getUps().contains(npUps))
					npLoc.getUps().add(npUps);
			}
			
			
		
			
	
		}
		return new ArrayList<NonProdAssetLocation>(nonProdMap.values());
	}

}
