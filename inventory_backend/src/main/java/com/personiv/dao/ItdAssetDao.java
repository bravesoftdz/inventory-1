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

import com.personiv.model.assets.ItdAsset;
import com.personiv.model.locations.ItdRoom;
import com.personiv.rowmapper.ItdAssetRowMapper;

@Repository
@Transactional(readOnly = false)
public class ItdAssetDao  extends JdbcDaoSupport{
	
	private JdbcTemplate jdbcTemplate;

    @Autowired
    private DataSource dataSource;

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
        jdbcTemplate = getJdbcTemplate();
    }
    
    
    public List<ItdAsset> getItdAssets(){
    	
     	String sql ="SELECT ta.id asId, ta.itr_id asLocId, ta.createdAt asCreated, ta.updatedAt asUpdated,     " + 
     			"     			    			        su.id suId, su.computer_name suName, su.description suDesc, su.serial_number suSerial, su.asset_number suAsset, su.status suStatus, su.warranty_exp suWarranty, su.mac_address suMac,su.createdAt suCreated, su.updatedAt suUpdated,    " + 
     			"     			    					suMo.id suModelId, suMo.name suModelName, suMo.createdAt suModelCreated, suMo.updatedAt suModelUpdated,     " + 
     			"     			    			        tr.id locId, tr.name locName, tr.createdAt locCreated, tr.updatedAt locUpdated,   " + 
     			"     			    					mon1.id monId, mon1.description monDesc, mon1.serial_number monSerial, mon1.asset_number monAsset,mon1.status monStatus, mon1.warranty_exp monWarranty, mon1.createdAt monCreated, mon1.updatedAt monUpdated,     " + 
     			"     			    					monMo.id momodelId, monMo.name momodelName, monMo.createdAt momoCreated, monMo.updatedAt momoUpdated,     " + 
     			"     			    					mon2.id monId2, mon2.description monDesc2, mon2.serial_number monSerial2, mon2.asset_number monAsset2,mon2.status monStatus2, mon2.warranty_exp monWarranty2, mon2.createdAt monCreated2, mon2.updatedAt monUpdated2,      " + 
     			"     			    					monMo2.id momodelId2, monMo2.name momodelName2, monMo2.createdAt momoCreated2, monMo2.updatedAt momoUpdated2,     " + 
     			"     			    					kb.id kbId, kb.description kbDesc, kb.serial_number kbSerial, kb.asset_number kbAsset, kb.status kbStatus,kb.warranty_exp kbWarranty,kb.createdAt kbCreated, kb.updatedAt kbUpdated,     " + 
     			"     			    					kbMo.id kbMoId, kbMo.name kbMoName, kbMo.createdAt kbMoCreated, kbMo.updatedAt kbMoUpdated,     " + 
     			"     			    					ms.id msId, ms.description msDesc,ms.serial_number msSerial, ms.asset_number msAsset, ms.status msStatus,ms.warranty_exp msWarranty, ms.createdAt msCreated, ms.updatedAt msUpdated,     " + 
     			"     			    					msMo.id msMoId, msMo.name msMoName, msMo.createdAt msMoCreated, msMo.updatedAt msMoUpdated,     " + 
     			"     			    					u.id uId, u.description uDesc, u.serial_number uSerial,u.asset_number uAsset, u.status uStatus, u.warranty_exp uWarranty, u.createdAt uCreated, u.updatedAt uUpdated,     " + 
     			"     			    					uMo.id uMoId, uMo.name uMoName, uMo.createdAt uMoCreated,uMo.updatedAt uMoUpdated     " + 
     			"     			    			   FROM it_room_assets ta     " + 
     			"     			    			   LEFT JOIN system_units su on ta.cpu_id = su.id     " + 
     			"     			    			   LEFT JOIN models suMo on su.model_id = suMo.id   " + 
     			"     			    			   LEFT JOIN it_rooms tr on ta.itr_id = tr.id     " + 
     			"     			    			   LEFT JOIN monitors mon1 on ta.monitor_id1 = mon1.id     " + 
     			"     			    			   LEFT JOIN models monMo on mon1.model_id = monMo.id     " + 
     			"     			    			   LEFT JOIN monitors mon2 on ta.monitor_id2 = mon2.id     " + 
     			"     			    			   LEFT JOIN models monMo2 on mon2.model_id = monMo2.id     " + 
     			"     			    			   LEFT JOIN keyboards kb on ta.keyboard_id = kb.id     " + 
     			"     			    			   LEFT JOIN models kbMo on kbMo.id = kb.model_id     " + 
     			"     			    			   LEFT JOIN mice ms on ta.mouse_id = ms.id     " + 
     			"     			    			   LEFT JOIN models msMo on msMo.id = ms.model_id     " + 
     			"     			    			   LEFT JOIN ups u on ta.cpu_id = u.id   " + 
     			"     			    			   LEFT JOIN models uMo on uMo.id = u.model_id      " + 
     			"     			    			  ORDER BY ta.id DESC";
    	
    	return jdbcTemplate.query(sql, new ItdAssetRowMapper());
    }


	public void addITDAsset(ItdAsset asset) {
		
		String sql ="INSERT INTO it_room_assets(itr_id, cpu_id, monitor_id1, monitor_id2, mouse_id, keyboard_id) VALUES(?,?,?,?,?,?)";
		jdbcTemplate.update(sql, new Object[] {
				asset.getLocation().getId(),
				asset.getSystemUnit().getId(),
				asset.getMonitor1().getId(),
				asset.getMonitor2().getId(),
				asset.getMouse().getId(),
				asset.getKeyboard().getId()
		});
		
	}


	public List<ItdRoom>  getITDRooms() {
		String sql ="SELECT * FROM it_rooms";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<ItdRoom>(ItdRoom.class));
		
	}
    
}
