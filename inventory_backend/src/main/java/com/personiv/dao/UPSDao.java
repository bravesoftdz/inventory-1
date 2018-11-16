package com.personiv.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.personiv.model.assets.UPS;
import com.personiv.rowmapper.UPSRowMapper;


@Repository
@Transactional(readOnly = false)
public class UPSDao extends JdbcDaoSupport{
	
	private JdbcTemplate jdbcTemplate;

    @Autowired
    private DataSource dataSource;

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
        jdbcTemplate = getJdbcTemplate();
    }
    
    public List<UPS> getUPSs(){
    	String sql ="SELECT up.id  upId, up.description  upDesc, up.serial_number  upSerial,up.asset_number  upAsset, up.warranty_exp  upWarranty,up.status  upStatus, up.createdAt  upCreated,up.updatedAt upUpdated,"
    			  + "       m.id  modelId, m.name  modelName, m.createdAt  modelCreated, m.updatedAt  modelUpdated "
    			  + "  FROM  ups  up"
    			  + "  JOIN models m on up.model_id = m.id"
    			  + " ORDER BY up.id DESC";
    	return jdbcTemplate.query(sql, new UPSRowMapper());
    }

	public UPS getUPS(Long id) {
    	String sql ="SELECT up.id  upId, up.description  upDesc, up.serial_number  upSerial,up.asset_number  upAsset, up.warranty_exp  upWarranty,up.status  upStatus, up.createdAt  upCreated,up.updatedAt upUpdated,"
	  			  + "       m.id  modelId, m.name  modelName, m.createdAt  modelCreated, m.updatedAt  modelUpdated "
	  			  + "  FROM ups  up"
	  			  + "  JOIN models m on up.model_id = m.id"
	  			  + " WHERE up.id = ?";
    	return jdbcTemplate.queryForObject(sql,new Object[] {id}, new UPSRowMapper());
	}

	public List<UPS> getAvailableUPS() {
		// TODO Auto-generated method stub
		return null;
	}

	public void updateUPS(UPS ups) {
		String sql = "UPDATE ups SET serial_number=?, asset_number=?, status=?, model_id=?, updatedAt = CURRENT_TIMESTAMP WHERE id =?";
		
		jdbcTemplate.update(sql, new Object[] {
				ups.getSerialNumber(),
				ups.getAssetNumber(),
				ups.getStatus(),
				ups.getModel().getId(),
				ups.getId()
		});
		
	}

	public void addUPS(UPS ups) {
		
		String sql ="INSERT INTO ups(serial_number,asset_number,model_id) VALUES(?,?,?)";
		
		jdbcTemplate.update(sql, new Object[]{
				ups.getSerialNumber(),
				ups.getAssetNumber(),
				ups.getModel().getId()
		});
		
	}

	public void deleteUPS(UPS ups) {
		String sql ="DELETE FROM ups WHERE id=?";
		jdbcTemplate.update(sql,new Object[] {ups.getId()});
		
	}
}
