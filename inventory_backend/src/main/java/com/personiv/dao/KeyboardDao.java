package com.personiv.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.personiv.model.assets.Keyboard;
import com.personiv.rowmapper.KeyboardRowMapper;
import com.personiv.rowmapper.MonitorRowMapper;


@Repository
@Transactional(readOnly = false)
public class KeyboardDao extends JdbcDaoSupport{
	
	private JdbcTemplate jdbcTemplate;

    @Autowired
    private DataSource dataSource;

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
        jdbcTemplate = getJdbcTemplate();
    }
    
    public List<Keyboard> getKeyboards(){
    	String sql ="SELECT kb.id  kbId, kb.description  kbDesc, kb.serial_number  kbSerial,kb.asset_number  kbAsset, kb.warranty_exp  kbWarranty, kb.createdAt  kbCreated,kb.updatedAt kbUpdated," + 
    			"           m.id  modelId, m.name  modelName, m.createdAt  modelCreated, m.updatedAt  modelUpdated," +  
    			"		    IF(pa.kb_id IS NOT NULL,'PROD USE', " + 
    			"           IF(ta.keyboard_id IS NOT NULL,'TRAINING USE', " + 
    			"           IF(ir.keyboard_id IS NOT NULL,'ITD USE','IN STOCK'))) as kbStatus " + 
    			"     FROM  keyboards  kb" + 
    			"      JOIN models m on kb.model_id = m.id" + 
    			"      LEFT JOIN prod_assets pa ON kb.id = pa.kb_id" + 
    			"      LEFT JOIN training_assets ta ON kb.id = ta.keyboard_id" + 
    			"      LEFT JOIN it_room_assets ir ON kb.id = ir.keyboard_id"+ 
    			"  ORDER BY kb.id DESC";
    	return jdbcTemplate.query(sql, new KeyboardRowMapper());
    }

	public Keyboard getKeyboard(Long id) {
    	String sql ="SELECT kb.id  kbId, kb.description  kbDesc, kb.serial_number  kbSerial,kb.asset_number  kbAsset, kb.warranty_exp  kbWarranty,kb.status  kbStatus, kb.createdAt  kbCreated,kb.updatedAt kbUpdated,"
	  			  + "       m.id  modelId, m.name  modelName, m.createdAt  modelCreated, m.updatedAt  modelUpdated "
	  			  + "  FROM keyboards  kb"
	  			  + "  JOIN models m on kb.model_id = m.id"
	  			  + " WHERE kb.id = ?";
    	return jdbcTemplate.queryForObject(sql,new Object[] {id}, new KeyboardRowMapper());
	}

	public List<Keyboard> getAvailableKeyboards() {
	 	String sql ="SELECT kb.id  kbId, kb.description  kbDesc, kb.serial_number  kbSerial,kb.asset_number  kbAsset, kb.warranty_exp  kbWarranty,kb.status  kbStatus, kb.createdAt  kbCreated,kb.updatedAt kbUpdated," + 
		 			"		 m.id modelId, m.name modelName, m.createdAt modelCreated, m.updatedAt modelUpdated " + 
		 			"  FROM keyboards kb " + 
		 			"  JOIN models m on m.id = kb.model_id " + 
		 			"  LEFT JOIN production_assets pa ON kb.id = pa.keyboard_id" + 
		 			"  LEFT JOIN training_assets ta ON kb.id = ta.keyboard_id" + 
		 			"  LEFT JOIN it_room_assets ir ON kb.id = ir.keyboard_id" + 
		 			"  WHERE (pa.keyboard_id IS NULL) AND " + 
		 			"  		  (ta.keyboard_id IS NULL) AND " + 
		 			"		  (ir.keyboard_id IS NULL) ";
	 	
	return jdbcTemplate.query(sql, new KeyboardRowMapper());
	}

	public void updateKeyboard(Keyboard keyboard) {
		String sql = "UPDATE keyboards SET serial_number=?, asset_number=?, model_id=? WHERE id =?";
		
		jdbcTemplate.update(sql, new Object[] {
				keyboard.getSerialNumber(),
				keyboard.getAssetNumber(),
				keyboard.getModel().getId(),
				keyboard.getId()
		});
		
	}

	public void addKeyboard(Keyboard keyboard) {
		
		String sql ="INSERT INTO keyboards(serial_number,asset_number,model_id) VALUES(?,?,?)";
		
		jdbcTemplate.update(sql, new Object[]{
				keyboard.getSerialNumber(),
				keyboard.getAssetNumber(),
				keyboard.getModel().getId()
		});
		
	}

	public void deleteKeyboard(Keyboard keyboard) {
		String sql ="DELETE FROM keyboards WHERE id=?";
		jdbcTemplate.update(sql,new Object[] {keyboard.getId()});
		
	}
}
