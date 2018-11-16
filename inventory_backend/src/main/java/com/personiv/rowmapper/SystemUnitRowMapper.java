package com.personiv.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.personiv.model.assets.Model;
import com.personiv.model.assets.SystemUnit;

public class SystemUnitRowMapper implements RowMapper<SystemUnit>{

	@Override
	public SystemUnit mapRow(ResultSet rs, int arg1) throws SQLException {

		
		Model m = new Model();
		m.setId(rs.getLong("modelId"));
		m.setName(rs.getString("modelName"));
		m.setCreatedAt(rs.getTimestamp("modelCreated"));
		m.setUpdatedAt(rs.getTimestamp("modelUpdated"));
		
		SystemUnit s = new SystemUnit();
		s.setId(rs.getLong("suId"));
		s.setName(rs.getString("suName"));
		s.setDescription(rs.getString("suDesc"));
		s.setSerialNumber(rs.getString("suSerial"));
		s.setAssetNumber(rs.getString("suAsset"));
		s.setStatus(rs.getString("suStatus"));
		s.setMacAddress(rs.getString("suMac"));
		s.setModel(m);
		s.setWarrantyExp(rs.getDate("suWarranty"));
		s.setCreatedAt(rs.getTimestamp("suCreated"));
		s.setUpdatedAt(rs.getTimestamp("suUpdated"));
		return s;
	}

}
