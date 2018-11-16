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

import com.personiv.model.assets.Software;
import com.personiv.model.assets.SystemUnitSoftware;
import com.personiv.rowmapper.SystemUnitSoftwareMapper;

@Repository
@Transactional(readOnly = false)
public class SoftwareDao extends JdbcDaoSupport {
	
	private JdbcTemplate jdbcTemplate;

    @Autowired
    private DataSource dataSource;

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
        jdbcTemplate = getJdbcTemplate();
    }
    
    public List<Software> getSoftwares(){
    	String sql ="SELECT s.id,s.soft_desc softwareName,s.soft_user softwareUser,s.createdAt,s.updatedAt "
    			  + "  FROM softwares s";
    	return jdbcTemplate.query(sql,  new BeanPropertyRowMapper<Software>(Software.class));
    }
    
    public List<SystemUnitSoftware> getSystemUnitSoftwares(){
    	String sql ="SELECT su.id suId,su.computer_name suName,su.serial_number suSerial,su.asset_number suAsset,su.mac_address suMac, su.createdAt suCreated, su.updatedAt suUpdated," + 
	    			"	    soft.id softId, soft.soft_desc softDesc, soft.soft_user softUser, soft.createdAt softCreated, soft.updatedAt softUpdated,"+
	    			"       sus.createdAt susCreated,sus.updatedAt susUpdated, sus.id," + 
	    			"	    mo.id modelId, mo.name modelName, mo.createdAt modelCreated, mo.updatedAt modelUpdated" + 
	    			"  FROM cpu_softwares sus" + 
	    			"  JOIN softwares soft ON sus.soft_id = soft.id" + 
	    			"  JOIN system_units su ON sus.cpu_id = su.id" + 
	    			"  JOIN models mo ON su.model_id = mo.id";
    	return jdbcTemplate.query(sql, new SystemUnitSoftwareMapper());
    }

	public void addSoftware(Software software) {
		String sql = "INSERT INTO softwares(soft_desc,soft_user) VALUES(?,?)";
		jdbcTemplate.update(sql, new Object[] {software.getSoftwareName(),software.getSoftwareUser()});
		
	}

	public void updateSoftware(Software software) {
		String sql ="UPDATE softwares SET soft_desc =?, soft_user =?, updatedAt = CURRENT_TIMESTAMP WHERE id = ?";
		jdbcTemplate.update(sql,new Object[] {software.getSoftwareName(),software.getSoftwareUser(),software.getId()});
		
	}

	public void addSystemUnitSoftware(SystemUnitSoftware sus) {
		String sql ="INSERT INTO cpu_softwares(cpu_id,soft_id) VALUES(?,?)";
		jdbcTemplate.update(sql,new Object[] {
				sus.getSystemUnit().getId(),
				sus.getSoftware().getId()
		});
		
	}

	public void updateSystemUnitSoftware(SystemUnitSoftware sus) {
		String sql = "UPDATE cpu_softwares SET cpu_id =?, soft_id = ?, updatedAT = CURRENT_TIMESTAMP where id = ?";
		jdbcTemplate.update(sql,new Object[] {
				sus.getSystemUnit().getId(),
				sus.getSoftware().getId(),
				sus.getId()
		});
		
	}

	public void deleteSystemUnitSoftware(SystemUnitSoftware sus) {
		String sql ="DELETE FROM cpu_softwares WHERE id =?";
		jdbcTemplate.update(sql,new Object[] {sus.getId()});
		
	}
}
