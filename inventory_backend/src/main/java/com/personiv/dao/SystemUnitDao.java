package com.personiv.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.personiv.model.ChartData;
import com.personiv.model.PieChartData;
import com.personiv.model.assets.SystemUnit;
import com.personiv.rowmapper.SystemUnitRowMapper;

@Repository
@Transactional(readOnly = false)
public class SystemUnitDao extends JdbcDaoSupport{
	
	private JdbcTemplate jdbcTemplate;

    @Autowired
    private DataSource dataSource;

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
        jdbcTemplate = getJdbcTemplate();
    }
    
    public List<SystemUnit> getSystemUnits(){
    	String sql ="SELECT su.id suId, su.computer_name suName,su.description suDesc, su.serial_number suSerial,su.asset_number suAsset, su.warranty_exp suWarranty,su.mac_address suMac, su.createdAt suCreated,su.updatedAt suUpdated," + 
    			"           m.id modelId, m.name modelName, m.createdAt modelCreated, m.updatedAt modelUpdated," + 
    			"		    IF(pa.cpu_id IS NOT NULL,'PROD USE'," + 
    			"		    IF(ita.cpu_id IS NOT NULL,'ITD USE'," + 
    			"		    IF(ta.cpu_id IS NOT NULL,'TRAINING USE','IN STOCK'))) suStatus" + 
    			"      FROM system_units su" + 
    			"      JOIN models m on su.model_id = m.id" + 
    			"      LEFT JOIN prod_assets pa ON pa.cpu_id = su.id" + 
    			"      LEFT JOIN it_room_assets ita ON ita.cpu_id = su.id" + 
    			"      LEFT JOIN training_assets ta ON ta.cpu_id = su.id " + 
    			"     ORDER BY su.id DESC";
    	return jdbcTemplate.query(sql, new SystemUnitRowMapper());
    }

	public List<SystemUnit> getAvailableSystemUnits() {
	 	String sql = "SELECT su.id suId, su.computer_name suName,su.description suDesc, su.serial_number suSerial,su.asset_number suAsset, su.warranty_exp suWarranty,su.status suStatus,su.mac_address suMac, su.createdAt suCreated,su.updatedAt suUpdated,"
	 			   + "       m.id modelId, m.name modelName, m.createdAt modelCreated, m.updatedAt modelUpdated "
	 			   +"  FROM system_units su"  
	 			   +"  LEFT JOIN models m on su.model_id = m.id"
	 			   + " LEFT JOIN production_assets pa ON pa.cpu_id = su.id " 
 			       +"  LEFT JOIN it_room_assets itr ON itr.cpu_id  = su.id " 
 			       +"  LEFT JOIN training_assets ta ON ta.cpu_id = su.id " 
 			       +" WHERE pa.id IS NULL AND itr.id IS NULL AND ta.id IS NULL";
	 		return jdbcTemplate.query(sql, new SystemUnitRowMapper());
	}

	public void addSystemUnit(SystemUnit systemUnit) {
		String sql ="INSERT INTO system_units(serial_number,asset_number,computer_name,description,model_id,mac_address) VALUES(?,?,?,?,?,?)";
		jdbcTemplate.update(sql,new Object[] {
				systemUnit.getSerialNumber(),
				systemUnit.getAssetNumber(),
				systemUnit.getName(),
				systemUnit.getDescription(),
				systemUnit.getModel().getId(),
				systemUnit.getMacAddress()
		});
	}

	public void updateSystemUnit(SystemUnit systemUnit) {
		String sql ="UPDATE system_units SET serial_number =?, asset_number=?, computer_name=?,mac_address =?, model_id =?, updatedAt = CURRENT_TIMESTAMP WHERE id =?";
		jdbcTemplate.update(sql,new Object[] {
				systemUnit.getSerialNumber(),
				systemUnit.getAssetNumber(),
				systemUnit.getName(),
				systemUnit.getMacAddress(),
				systemUnit.getModel().getId(),
				systemUnit.getId()
		});
	}

	public SystemUnit getSystemUnit(Long id) {
	 	String sql = "SELECT su.id suId, su.computer_name suName,su.description suDesc, su.serial_number suSerial,su.asset_number suAsset, su.warranty_exp suWarranty,su.status suStatus,su.mac_address suMac, su.createdAt suCreated,su.updatedAt suUpdated,"
	 			   + "       m.id modelId, m.name modelName, m.createdAt modelCreated, m.updatedAt modelUpdated "
	 			   +"  FROM system_units su"  
	 			   +"  LEFT JOIN models m on su.model_id = m.id"
	 			   + " LEFT JOIN production_assets pa ON pa.cpu_id = su.id " 
 			       +"  LEFT JOIN it_room_assets itr ON itr.cpu_id  = su.id " 
 			       +"  LEFT JOIN training_assets ta ON ta.cpu_id = su.id " 
 			       +" WHERE su.id=?";
	 		return jdbcTemplate.queryForObject(sql,new Object[] {id}, new SystemUnitRowMapper());
		
		
	}
	public SystemUnit getSystemUnitByAssetTag(String assetTag) {
	 	String sql = "SELECT su.id suId, su.computer_name suName,su.description suDesc, su.serial_number suSerial,su.asset_number suAsset, su.warranty_exp suWarranty,su.status suStatus,su.mac_address suMac, su.createdAt suCreated,su.updatedAt suUpdated,"
	 			   + "       m.id modelId, m.name modelName, m.createdAt modelCreated, m.updatedAt modelUpdated "
	 			   +"  FROM system_units su"  
	 			   +"  LEFT JOIN models m on su.model_id = m.id"
	 			   + " LEFT JOIN production_assets pa ON pa.cpu_id = su.id " 
 			       +"  LEFT JOIN it_room_assets itr ON itr.cpu_id  = su.id " 
 			       +"  LEFT JOIN training_assets ta ON ta.cpu_id = su.id " 
 			       +" WHERE su.asset_number=?";
	 		return jdbcTemplate.queryForObject(sql,new Object[] {assetTag}, new SystemUnitRowMapper());
		
		
	}
	public List<PieChartData> getSystemUnitsGroupByDepartment() {
		String sql ="  SELECT l.department label,count(l.department) value " + 
					"    FROM prod_assets pa " + 
					"    JOIN system_units su ON pa.cpu_id = su.id " + 
					"    JOIN locations l ON pa.loc_id = l.id " + 
					"   GROUP BY l.department";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<PieChartData>(PieChartData.class));
	}
	
	public List<PieChartData> getSystemUnitsGroupByModel() {
		String sql="   SELECT m.name label,count(m.id) as value " + 
				"    FROM system_units su " + 
				"    JOIN models m ON su.model_id = m.id " + 
				"GROUP BY m.id";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<PieChartData>(PieChartData.class));
	}

	public List<ChartData> getSystemUnitsChartByModel() {
		String sql="   SELECT m.name,count(m.id) as y " + 
					"    FROM system_units su " + 
					"    JOIN models m ON su.model_id = m.id " + 
					"GROUP BY m.id";
		
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<ChartData>(ChartData.class));
	}
    
}
