package com.personiv.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.personiv.mapextractor.AssetAccountabilityMapExtractor;
import com.personiv.mapextractor.NonProdAssetMapExtractor;
import com.personiv.model.NonProdAssetLocation;
import com.personiv.model.NonProdCPUDeployed;
import com.personiv.model.NonProdKeyboardDeployed;
import com.personiv.model.NonProdMonitorDeployed;
import com.personiv.model.NonProdMouseDeployed;
import com.personiv.model.NonProdUPSDeployed;

@Repository
@Transactional(readOnly = false)
public class NonProdAssetDao extends JdbcDaoSupport{
	
	private JdbcTemplate jdbcTemplate;

    @Autowired
    private DataSource dataSource;

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
        jdbcTemplate = getJdbcTemplate();
    }
    
    public List<NonProdAssetLocation> getNonProdAssets(){
    	String sql= "   SELECT np.id locId,np.loc_name locName, " + 
	    			"   	   nc.id nCpuid, su.id cpuId,su.asset_number cpuAsset, " + 
	    			"   	   nm.id nMonitorId,m.id monitorId, m.asset_number monitorAsset, " + 
	    			"   	   nk.id nKeyboardId, kb.id keyboardId, kb.asset_number keyboardAsset, " + 
	    			"   	   nms.id nMouseid, ms.id mouseId, ms.asset_number mouseAsset, " + 
	    			"   	   nu.id nUpsId,u.id upsId, u.asset_number upsAsset " + 
	    			"     FROM non_prod_loc np " + 
	    			"LEFT JOIN non_prod_cpu nc ON nc.np_id = np.id " + 
	    			"LEFT JOIN system_units su ON nc.cpu_id = su.id " + 
	    			"LEFT JOIN non_prod_monitors nm ON nm.np_id = np.id " + 
	    			"LEFT JOIN monitors m ON nm.mon_id = m.id " + 
	    			"LEFT JOIN non_prod_keyboard nk ON nk.np_id = np.id " + 
	    			"LEFT JOIN keyboards kb ON nk.kb_id = kb.id " + 
	    			"LEFT JOIN non_prod_mouse nms ON nms.np_id = np.id " + 
	    			"LEFT JOIN mice ms ON nms.ms_id = ms.id " + 
	    			"LEFT JOIN non_prod_ups nu ON nu.np_id = np.id " + 
	    			"LEFT JOIN ups u ON nu.up_id = u.id";
    	return jdbcTemplate.query(sql, new NonProdAssetMapExtractor());
    }
    
    public void addNonProdCPU(NonProdCPUDeployed npCPU) {
    	String sql ="INSERT INTO non_prod_cpu(cpu_id,np_id) VALUES(?,?)";
    	jdbcTemplate.update(sql, new Object[] {
    			npCPU.getCpu().getId(),
    			npCPU.getLocation().getId()
    	});
    	
    }
    public void addNonProdMonitor(NonProdMonitorDeployed npMonitor) {
    	String sql ="INSERT INTO non_prod_monitors(mon_id,np_id) VALUES(?,?)";
    	jdbcTemplate.update(sql, new Object[] {
    			npMonitor.getMonitor().getId(),
    			npMonitor.getLocation().getId()
    	});
    }
    public void addNonProdKeyboard(NonProdKeyboardDeployed npKeyboard) {
    	String sql ="INSERT INTO non_prod_keyboard(kb_id,np_id) VALUES(?,?)";
    	jdbcTemplate.update(sql, new Object[] {
    			npKeyboard.getKeyboard().getId(),
    			npKeyboard.getLocation().getId()
    	});
    }
    public void addNonProdMouse(NonProdMouseDeployed npMouse) {
    	String sql ="INSERT INTO non_prod_mouse(ms_id,np_id) VALUES(?,?)";
    	jdbcTemplate.update(sql, new Object[] {
    			npMouse.getMouse().getId(),
    			npMouse.getLocation().getId()
    	});
    }
    
    public void addNonProdUPS(NonProdUPSDeployed npUPS) {
    	String sql ="INSERT INTO non_prod_ups(up_id,np_id) VALUES(?,?)";
    	jdbcTemplate.update(sql, new Object[] {
    			npUPS.getUps().getId(),
    			npUPS.getLocation().getId()
    	});
    }

   public void deleteNonProdCPU(NonProdCPUDeployed npCPU) {
	   String sql ="DELETE FROM non_prod_cpu WHERE cpu_id = ? AND np_id =?";
	   	jdbcTemplate.update(sql, new Object[] {
	   			npCPU.getCpu().getId(),
	   			npCPU.getLocation().getId()
	   	});
    }
    public void deleteNonProdMonitor(NonProdMonitorDeployed npMonitor) {
    	String sql ="DELETE FROM non_prod_monitors WHERE mon_id = ? AND np_id =?";
  	   	jdbcTemplate.update(sql, new Object[] {
  	   		npMonitor.getMonitor().getId(),
  	   		npMonitor.getLocation().getId()
  	   	});
    }
    public void deleteNonProdKeyboard(NonProdKeyboardDeployed npKeyboard) {
    	String sql ="DELETE FROM non_prod_keyboard WHERE kb_id = ? AND np_id =?";
  	   	jdbcTemplate.update(sql, new Object[] {
  	   		npKeyboard.getKeyboard().getId(),
  	   		npKeyboard.getLocation().getId()
  	   	});
    }
    public void deleteNonProdMouse(NonProdMouseDeployed npMouse) {
    	String sql ="DELETE FROM non_prod_mouse WHERE ms_id = ? AND np_id =?";
  	   	jdbcTemplate.update(sql, new Object[] {
  	   		npMouse.getMouse().getId(),
  	   		npMouse.getLocation().getId()
  	   	});
    }
    
    public void deleteNonProdUPS(NonProdUPSDeployed npUPS) {
    	String sql ="DELETE FROM non_prod_ups WHERE up_id = ? AND np_id =?";
  	   	jdbcTemplate.update(sql, new Object[] {
  	   		npUPS.getUps().getId(),
  	   		npUPS.getLocation().getId()
  	   	});
    }
	public void addNonProdAssets(NonProdAssetLocation npLocation) {
		
		
	}
    
  

}
