package com.personiv.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.personiv.model.assets.Keyboard;
import com.personiv.model.assets.Model;

public class KeyboardRowMapper implements RowMapper<Keyboard>{

	@Override
	public Keyboard mapRow(ResultSet rs, int arg1) throws SQLException {
		

		
		Model m = new Model();
		m.setId(rs.getLong("modelId"));
		m.setName(rs.getString("modelName"));
		m.setCreatedAt(rs.getTimestamp("modelCreated"));
		m.setUpdatedAt(rs.getTimestamp("modelUpdated"));
		
		Keyboard kb = new Keyboard();
		
		kb.setId(rs.getLong("kbId"));
		kb.setDescription(rs.getString("kbDesc"));
		kb.setSerialNumber(rs.getString("kbSerial"));
		kb.setAssetNumber(rs.getString("kbAsset"));
		kb.setStatus(rs.getString("kbStatus"));
		
		kb.setModel(m);
		kb.setWarrantyExp(rs.getDate("kbWarranty"));
		kb.setCreatedAt(rs.getTimestamp("kbCreated"));
		kb.setUpdatedAt(rs.getTimestamp("kbUpdated"));
		return kb;
	}

}
