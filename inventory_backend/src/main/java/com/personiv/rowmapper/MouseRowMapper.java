package com.personiv.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.personiv.model.assets.Model;
import com.personiv.model.assets.Mouse;

public class MouseRowMapper implements RowMapper<Mouse>{

	@Override
	public Mouse mapRow(ResultSet rs, int arg1) throws SQLException {
		
		Model m = new Model();
		m.setId(rs.getLong("modelId"));
		m.setName(rs.getString("modelName"));
		m.setCreatedAt(rs.getTimestamp("modelCreated"));
		m.setUpdatedAt(rs.getTimestamp("modelUpdated"));
		
		Mouse mo = new Mouse();
		
		mo.setId(rs.getLong("msId"));
		mo.setDescription(rs.getString("msDesc"));
		mo.setSerialNumber(rs.getString("msSerial"));
		mo.setAssetNumber(rs.getString("msAsset"));
		mo.setStatus(rs.getString("msStatus"));
		mo.setModel(m);
		mo.setWarrantyExp(rs.getDate("msWarranty"));
		mo.setCreatedAt(rs.getTimestamp("msCreated"));
		mo.setUpdatedAt(rs.getTimestamp("msUpdated"));
		return mo;
	}

}
