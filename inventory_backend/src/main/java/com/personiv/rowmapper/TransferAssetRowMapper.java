package com.personiv.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.personiv.model.assets.Keyboard;
import com.personiv.model.assets.Model;
import com.personiv.model.assets.Monitor;
import com.personiv.model.assets.Mouse;
import com.personiv.model.assets.ProductionAsset;
import com.personiv.model.assets.SystemUnit;
import com.personiv.model.assets.TransferAsset;
import com.personiv.model.assets.UPS;
import com.personiv.model.locations.ProductionArea;

public class TransferAssetRowMapper implements RowMapper<TransferAsset>{

	@Override
	public TransferAsset mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Long prodId1 = rs.getLong("prodId1");
		Long prodId2 = rs.getLong("prodId2");
		Long keyboardId = rs.getLong("kbId");
		Long mouseId = rs.getLong("msId");
		Long monitorId1 = rs.getLong("monId");
		Long monitorId2 = rs.getLong("monId2");
		Long systemUnitId = rs.getLong("suId");
		Long locationId = rs.getLong("locId");
		Long locationId2 = rs.getLong("locId2");
		Long upsId = rs.getLong("uId");
		
		TransferAsset assetLocation = new TransferAsset();
		
		assetLocation.setFromProdAssetId(prodId1);
		assetLocation.setToProdAssetId(prodId2);
		if(locationId != null) {
			ProductionArea loc = new ProductionArea();
			loc.setId(locationId);
			loc.setName(rs.getString("locName"));
			loc.setCreatedAt(rs.getDate("locCreated"));
			loc.setUpdatedAt(rs.getDate("locUpdated"));
			assetLocation.setFromLocation(loc);
			
		}
		
		if(locationId2 != null) {
			ProductionArea loc = new ProductionArea();
			loc.setId(locationId2);
			loc.setName(rs.getString("locName2"));
			loc.setCreatedAt(rs.getDate("locCreated2"));
			loc.setUpdatedAt(rs.getDate("locUpdated2"));
			assetLocation.setToLocation(loc);
			
		}
		
		if(monitorId1 != 0) {
			
			Monitor mon = new Monitor();
			
			mon.setId(rs.getLong("monId"));
			mon.setDescription(rs.getString("monDesc"));
			mon.setSerialNumber(rs.getString("monSerial"));
			mon.setAssetNumber(rs.getString("monAsset"));
			mon.setStatus(rs.getString("monStatus"));
			
			Model monmo = new Model();
			monmo.setId(rs.getLong("momodelId"));
			monmo.setName(rs.getString("momodelName"));
			monmo.setCreatedAt(rs.getTimestamp("momoCreated"));
			monmo.setUpdatedAt(rs.getTimestamp("momoUpdated"));
			
			mon.setModel(monmo);
			mon.setWarrantyExp(rs.getDate("monWarranty"));
			mon.setCreatedAt(rs.getTimestamp("monCreated"));
			mon.setUpdatedAt(rs.getTimestamp("monUpdated"));
			assetLocation.setMonitorA(mon);
		}
		
		if(monitorId2 != 0) {
			
			Monitor mon2 = new Monitor();
			
			mon2.setId(rs.getLong("monId2"));
			mon2.setDescription(rs.getString("monDesc2"));
			mon2.setSerialNumber(rs.getString("monSerial2"));
			mon2.setAssetNumber(rs.getString("monAsset2"));
			mon2.setStatus(rs.getString("monStatus2"));
			
			Model monmo2 = new Model();
			monmo2.setId(rs.getLong("momodelId2"));
			monmo2.setName(rs.getString("momodelName2"));
			monmo2.setCreatedAt(rs.getTimestamp("momoCreated2"));
			monmo2.setUpdatedAt(rs.getTimestamp("momoUpdated2"));
			
			mon2.setModel(monmo2);
			mon2.setWarrantyExp(rs.getDate("monWarranty2"));
			mon2.setCreatedAt(rs.getTimestamp("monCreated2"));
			mon2.setUpdatedAt(rs.getTimestamp("monUpdated2"));
			
			assetLocation.setMonitorB(mon2);
		}
		
		
		
		if(systemUnitId != 0) {
			SystemUnit s = new SystemUnit();
			s.setId(systemUnitId);
			s.setName(rs.getString("suName"));
			s.setDescription(rs.getString("suDesc"));
			s.setSerialNumber(rs.getString("suSerial"));
			s.setAssetNumber(rs.getString("suAsset"));
			s.setStatus(rs.getString("suStatus"));
			s.setMacAddress(rs.getString("suMac"));
			
			Model sumo = new Model();
			sumo.setId(rs.getLong("suModelId"));
			sumo.setName(rs.getString("suModelName"));
			sumo.setCreatedAt(rs.getTimestamp("suModelCreated"));
			sumo.setUpdatedAt(rs.getTimestamp("suModelUpdated"));
			
			s.setModel(sumo);
			
			s.setWarrantyExp(rs.getDate("suWarranty"));
			s.setCreatedAt(rs.getTimestamp("suCreated"));
			s.setUpdatedAt(rs.getTimestamp("suUpdated"));
			
			assetLocation.setSystemUnit(s);
		}
		
		if(keyboardId != 0) {
			
			Keyboard kb = new Keyboard();
			
			kb.setId(rs.getLong("kbId"));
			kb.setDescription(rs.getString("kbDesc"));
			kb.setSerialNumber(rs.getString("kbSerial"));
			kb.setAssetNumber(rs.getString("kbAsset"));
			kb.setStatus(rs.getString("kbStatus"));
			
			Model kmo = new Model();
			kmo.setId(rs.getLong("kbMoId"));
			kmo.setName(rs.getString("kbMoName"));
			kmo.setCreatedAt(rs.getTimestamp("kbMoCreated"));
			kmo.setUpdatedAt(rs.getTimestamp("kbMoUpdated"));
			
			kb.setModel(kmo);
			kb.setWarrantyExp(rs.getDate("kbWarranty"));
			kb.setCreatedAt(rs.getTimestamp("kbCreated"));
			kb.setUpdatedAt(rs.getTimestamp("kbUpdated"));
			
			assetLocation.setKeyboard(kb);
		}
		
		if(mouseId != 0) {
			Mouse ms = new Mouse();
			
			ms.setId(rs.getLong("msId"));
			ms.setDescription(rs.getString("msDesc"));
			ms.setSerialNumber(rs.getString("msSerial"));
			ms.setAssetNumber(rs.getString("msAsset"));
			ms.setStatus(rs.getString("msStatus"));
			
			Model msmo = new Model();
			msmo.setId(rs.getLong("msMoId"));
			msmo.setName(rs.getString("msMoName"));
			msmo.setCreatedAt(rs.getTimestamp("msMoCreated"));
			msmo.setUpdatedAt(rs.getTimestamp("msMoUpdated"));
			
			ms.setModel(msmo);
			ms.setWarrantyExp(rs.getDate("msWarranty"));
			ms.setCreatedAt(rs.getTimestamp("msCreated"));
			ms.setUpdatedAt(rs.getTimestamp("msUpdated"));
			
			assetLocation.setMouse(ms);
		}
		
		if(upsId != 0) {
			UPS ups = new UPS();
			
			ups.setId(rs.getLong("uId"));
			ups.setDescription(rs.getString("uDesc"));
			ups.setSerialNumber(rs.getString("uSerial"));
			ups.setAssetNumber(rs.getString("uAsset"));
			ups.setStatus(rs.getString("uStatus"));
			
			Model upsmo = new Model();
			upsmo.setId(rs.getLong("uMoId"));
			upsmo.setName(rs.getString("uMoName"));
			upsmo.setCreatedAt(rs.getTimestamp("uMoCreated"));
			upsmo.setUpdatedAt(rs.getTimestamp("uMoUpdated"));
			
			ups.setModel(upsmo);
			ups.setWarrantyExp(rs.getDate("uWarranty"));
			ups.setCreatedAt(rs.getTimestamp("uCreated"));
			ups.setUpdatedAt(rs.getTimestamp("uUpdated"));
			
			assetLocation.setUps(ups);
		}
		
	

		return assetLocation;
	}

}
