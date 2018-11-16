package com.personiv.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.personiv.model.assets.Model;
import com.personiv.model.assets.Software;
import com.personiv.model.assets.SystemUnit;
import com.personiv.model.assets.SystemUnitSoftware;

public class SystemUnitSoftwareMapper implements RowMapper<SystemUnitSoftware>{

	@Override
	public SystemUnitSoftware mapRow(ResultSet rs, int arg1) throws SQLException {
		SystemUnitSoftware sus = new SystemUnitSoftware();
		
		Model m = new Model();
		m.setId(rs.getLong("modelId"));
		m.setName(rs.getString("modelName"));
		m.setCreatedAt(rs.getTimestamp("modelCreated"));
		m.setUpdatedAt(rs.getTimestamp("modelUpdated"));
		
		SystemUnit s = new SystemUnit();
		s.setId(rs.getLong("suId"));
		s.setName(rs.getString("suName"));
		s.setSerialNumber(rs.getString("suSerial"));
		s.setAssetNumber(rs.getString("suAsset"));
		s.setMacAddress(rs.getString("suMac"));
		s.setModel(m);
		s.setCreatedAt(rs.getTimestamp("suCreated"));
		s.setUpdatedAt(rs.getTimestamp("suUpdated"));
		
		Software soft = new Software();
		
		soft.setId(rs.getLong("softId"));
		soft.setSoftwareName(rs.getString("softDesc"));
		soft.setSoftwareUser(rs.getString("softUser"));
		soft.setCreatedAt(rs.getTimestamp("softCreated"));
		soft.setUpdatedAt(rs.getTimestamp("softUpdated"));
		
		
		sus.setId(rs.getLong("id"));
		sus.setSoftware(soft);
		sus.setSystemUnit(s);
		sus.setUpdatedAt(rs.getTimestamp("susUpdated"));
		sus.setCreatedAt(rs.getTimestamp("susUpdated"));
		return sus;
	}

}
