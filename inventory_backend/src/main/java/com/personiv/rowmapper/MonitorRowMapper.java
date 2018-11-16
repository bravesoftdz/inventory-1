package com.personiv.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.personiv.model.assets.Model;
import com.personiv.model.assets.Monitor;

public class MonitorRowMapper implements RowMapper<Monitor>{

	@Override
	public Monitor mapRow(ResultSet rs, int arg1) throws SQLException {

		
		Model m = new Model();
		m.setId(rs.getLong("modelId"));
		m.setName(rs.getString("modelName"));
		m.setCreatedAt(rs.getTimestamp("modelCreated"));
		m.setUpdatedAt(rs.getTimestamp("modelUpdated"));
		
		Monitor mo = new Monitor();
		
		mo.setId(rs.getLong("moId"));
		mo.setDescription(rs.getString("moDesc"));
		mo.setSerialNumber(rs.getString("moSerial"));
		mo.setAssetNumber(rs.getString("moAsset"));
		mo.setStatus(rs.getString("moStatus"));
		mo.setModel(m);
		mo.setWarrantyExp(rs.getDate("moWarranty"));
		mo.setCreatedAt(rs.getTimestamp("moCreated"));
		mo.setUpdatedAt(rs.getTimestamp("moUpdated"));
		return mo;
	}

}
