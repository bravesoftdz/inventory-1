package com.personiv.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.personiv.model.assets.Mouse;
import com.personiv.rowmapper.MouseRowMapper;

@Repository
@Transactional(readOnly = false)
public class MouseDao extends JdbcDaoSupport{
	
	private JdbcTemplate jdbcTemplate;

    @Autowired
    private DataSource dataSource;

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
        jdbcTemplate = getJdbcTemplate();
    }

	public List<Mouse> getMice() {
		String sql ="SELECT ms.id  msId, ms.description  msDesc, ms.serial_number  msSerial,ms.asset_number  msAsset, ms.warranty_exp  msWarranty, ms.createdAt  msCreated,ms.updatedAt msUpdated,"
	  			  + "       m.id  modelId, m.name  modelName, m.createdAt  modelCreated, m.updatedAt  modelUpdated, "
	  			  + "       IF(pa.ms_id IS NOT NULL,'PROD USE', IF(ta.mouse_id IS NOT NULL,'TRAINING RM USE',IF(ita.mouse_id IS NOT NULL,'ITD USE','IN STOCK'))) as msStatus"
	  			  + "  FROM mice  ms"
	  			  + "  JOIN models m on ms.model_id = m.id"
	  			  + "  LEFT JOIN prod_assets pa on pa.ms_id = ms.id "  
	  			  + "  LEFT JOIN training_assets ta on ta.mouse_id = ms.id "  
	  			  + "  LEFT JOIN it_room_assets ita on ita.mouse_id = ms.id "
	  			  + " ORDER BY ms.id DESC";
		return jdbcTemplate.query(sql, new MouseRowMapper());
	}

	public List<Mouse> getAvailableMice() {
		String sql ="SELECT ms.id  msId, ms.description  msDesc, ms.serial_number  msSerial,ms.asset_number  msAsset, ms.warranty_exp  msWarranty, ms.createdAt  msCreated,ms.updatedAt msUpdated,"
	  			  + "       m.id  modelId, m.name  modelName, m.createdAt  modelCreated, m.updatedAt  modelUpdated, "
	  			  + "       IF(pa.mouse_id IS NOT NULL,'PROD USE', IF(ta.mouse_id IS NOT NULL,'TRAINING RM USE',IF(ita.mouse_id IS NOT NULL,'ITD USE','IN STOCK')))  msStatus"
	  			  + "  FROM mice  ms"
	  			  + "  JOIN models m on ms.model_id = m.id"
	  			  + "  LEFT JOIN prod_assets pa on pa.ms_id = ms.id "  
	  			  + "  LEFT JOIN training_assets ta on ta.mouse_id = ms.id "  
	  			  + "  LEFT JOIN it_room_assets ita on ita.mouse_id = ms.id "
				  +"  WHERE (pa.mouse_id IS NULL) AND "  
				  +"  		(ta.mouse_id IS NULL) AND " 
				  +"		(ita.mouse_id IS NULL)";
		return jdbcTemplate.query(sql, new MouseRowMapper());
	}

	public void updateMouse(Mouse mouse) {
		String sql = "UPDATE mice SET serial_number=?, asset_number=?, model_id=?, updatedAt =CURRENT_TIMESTAMP WHERE id =?";
		
		jdbcTemplate.update(sql, new Object[] {
				mouse.getSerialNumber(),
				mouse.getAssetNumber(),
				mouse.getModel().getId(),
				mouse.getId()
		});
		
	}

	public void addMouse(Mouse mouse) {
	String sql ="INSERT INTO mice(serial_number,asset_number,model_id) VALUES(?,?,?)";
		
		jdbcTemplate.update(sql, new Object[]{
				mouse.getSerialNumber(),
				mouse.getAssetNumber(),
				mouse.getModel().getId()
		});
		
	}
}
