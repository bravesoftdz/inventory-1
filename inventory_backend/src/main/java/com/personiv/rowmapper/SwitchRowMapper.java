package com.personiv.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.personiv.model.Switch;
import com.personiv.model.assets.Model;

public class SwitchRowMapper implements RowMapper<Switch>{

	@Override
	public Switch mapRow(ResultSet rs, int arg1) throws SQLException {
		Switch s = new Switch();
		
		s.setId(rs.getLong("id"));
		s.setName(rs.getString("name"));
		s.setAssetNumber(rs.getString("assetNumber"));
		s.setSerialNumber(rs.getString("serialNumber"));
		s.setIpAddress(rs.getString("ipAddress"));
		s.setMacAddress(rs.getString("macAddress"));
		s.setCreatedAt(rs.getTimestamp("sCreated"));
		s.setUpdatedAt(rs.getTimestamp("sUpdated"));
		
		Model m = new Model();
		m.setId(rs.getLong("mId"));
		m.setName(rs.getString("mName"));
		m.setCreatedAt(rs.getTimestamp("mCreated"));
		m.setUpdatedAt(rs.getTimestamp("mUpdated"));
		s.setModel(m);
		return s;
	}

}
