package com.personiv.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.personiv.mapextractor.SwitchConfigMapExtractor;
import com.personiv.model.PortConfig;
import com.personiv.model.SwitchConfig;
import com.personiv.model.SwitchPortConfig;

@Repository
@Transactional(readOnly = false)
public class SwitchConfigDao extends JdbcDaoSupport{
	
	private JdbcTemplate jdbcTemplate;

    @Autowired
    private DataSource dataSource;

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
        jdbcTemplate = getJdbcTemplate();
    }
    
    public List<SwitchConfig> getSwitchConfigs(){
    	String sql ="    SELECT sc.id, " + 
	    			"            s.id switchId, "+
	    			"            sc.panel, "+
	    			"    	     s.name switchName, " + 
	    			"    	     sc.port switchPort, " + 
	    			"    	     sc.vlan, " + 
	    			"    	     st.name trunkName, " + 
	    			"    	     sc.trunk_port trunkPort," + 
	    			"    	     sc.department, " + 
	    			"    	     sc.remarks " + 
	    			"      FROM switch_configs sc " + 
	    			"      JOIN switches s ON sc.switch_id = s.id " + 
	    			" LEFT JOIN switches st ON sc.trunk_id = st.id " + 
	    			" ORDER BY sc.id,sc.port";
    	return jdbcTemplate.query(sql, new SwitchConfigMapExtractor());
    }
    
    public SwitchConfig getSwitchConfigById(int id) {
    	return null;
    }
    
    public void addSwitchConfig(SwitchConfig config) {
    	String sql="INSERT INTO switch_configs(switch_id,port,panel,vlan,trunk_id,trunk_port,department,remarks) VALUES(?,?,?,?,?,?,?,?)";
    	jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				PortConfig portConfig = config.getPortConfigs().get(i);
				ps.setLong(1, config.getSource().getId());
				ps.setInt(2, portConfig.getSwitchPort());
				ps.setString(3, portConfig.getPortPanel());
				ps.setString(4, portConfig.getVlan());
				
				if(portConfig.getTrunkSwitch() == null) {
					ps.setNull(5, Types.INTEGER);
				}else {
					ps.setLong(5, portConfig.getTrunkSwitch().getId());
				}
				
				if(portConfig.getTrunkPort() == null) {
					ps.setNull(6, Types.VARCHAR);
				}else {

					ps.setInt(6, portConfig.getTrunkPort());
				}
				if(portConfig.getDepartment() == null) {
					ps.setNull(7, Types.VARCHAR);
					
				}else {
					ps.setString(7, portConfig.getDepartment());
				}
				
				if(portConfig.getRemarks() == null) {
					ps.setNull(8, Types.VARCHAR);
				}else {
					ps.setString(8, portConfig.getRemarks());
				}
				
				
			}

			@Override
			public int getBatchSize() {
				return config.getPortConfigs().size();
			}
    		
    	});
    }

	public void updateSwitchConfig(SwitchPortConfig config) {
		String sql ="UPDATE switch_configs SET panel =?, vlan = ?,trunk_id = ?, trunk_port =?, department =?,remarks = ? WHERE switch_id = ? AND port =? ";
		
		
		jdbcTemplate.update(sql, new Object[] {
				config.getPortConfig().getPortPanel(),
				config.getPortConfig().getVlan(),
				config.getPortConfig().getTrunkSwitch() == null ? null : config.getPortConfig().getTrunkSwitch().getId(),
				config.getPortConfig().getTrunkPort(),
				config.getPortConfig().getDepartment(),
				config.getPortConfig().getRemarks(),
				config.getSource().getId(),
				config.getPortConfig().getSwitchPort()
		});
	}
    
    
    

}
