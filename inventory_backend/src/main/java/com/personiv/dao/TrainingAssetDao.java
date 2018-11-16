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

import com.personiv.model.assets.ProductionAsset;
import com.personiv.model.assets.TrainingRoomAsset;
import com.personiv.model.locations.TrainingRoom;
import com.personiv.rowmapper.ProductionAssetRowMapper;
import com.personiv.rowmapper.TrainingRoomAssetRowMapper;

@Repository
@Transactional(readOnly = false)
public class TrainingAssetDao  extends JdbcDaoSupport{
	
	private JdbcTemplate jdbcTemplate;

    @Autowired
    private DataSource dataSource;

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
        jdbcTemplate = getJdbcTemplate();
    }
    
    public List<TrainingRoomAsset> getTrainingRoomAssets(){
    	String sql ="SELECT ta.id asId, ta.tr_id asLocId, ta.createdAt asCreated, ta.updatedAt asUpdated,     " + 
    				"		su.id suId, su.computer_name suName, su.description suDesc, su.serial_number suSerial, su.asset_number suAsset, su.status suStatus, su.warranty_exp suWarranty, su.mac_address suMac,su.createdAt suCreated, su.updatedAt suUpdated,    " + 
    				"		suMo.id suModelId, suMo.name suModelName, suMo.createdAt suModelCreated, suMo.updatedAt suModelUpdated,     " + 
    				"		tr.id locId, tr.name locName, tr.createdAt locCreated, tr.updatedAt locUpdated,   " + 
    				"		mon1.id monId, mon1.description monDesc, mon1.serial_number monSerial, mon1.asset_number monAsset,mon1.status monStatus, mon1.warranty_exp monWarranty, mon1.createdAt monCreated, mon1.updatedAt monUpdated,     " + 
    				"		monMo.id momodelId, monMo.name momodelName, monMo.createdAt momoCreated, monMo.updatedAt momoUpdated,     " + 
    				"		mon2.id monId2, mon2.description monDesc2, mon2.serial_number monSerial2, mon2.asset_number monAsset2,mon2.status monStatus2, mon2.warranty_exp monWarranty2, mon2.createdAt monCreated2, mon2.updatedAt monUpdated2,      " + 
    				"		monMo2.id momodelId2, monMo2.name momodelName2, monMo2.createdAt momoCreated2, monMo2.updatedAt momoUpdated2,     " + 
    				"		kb.id kbId, kb.description kbDesc, kb.serial_number kbSerial, kb.asset_number kbAsset, kb.status kbStatus,kb.warranty_exp kbWarranty,kb.createdAt kbCreated, kb.updatedAt kbUpdated,     " + 
    				"		kbMo.id kbMoId, kbMo.name kbMoName, kbMo.createdAt kbMoCreated, kbMo.updatedAt kbMoUpdated,     " + 
    				"		ms.id msId, ms.description msDesc,ms.serial_number msSerial, ms.asset_number msAsset, ms.status msStatus,ms.warranty_exp msWarranty, ms.createdAt msCreated, ms.updatedAt msUpdated,     " + 
    				"		msMo.id msMoId, msMo.name msMoName, msMo.createdAt msMoCreated, msMo.updatedAt msMoUpdated,     " + 
    				"		hs.id hsId, hs.description hsDesc, hs.serial_number hsSerial, hs.asset_number hsAsset, hs.status hsStatus, hs.warranty_exp hsWarranty, hs.createdAt hsCreated, hs.updatedAt hsUpdated,     " + 
    				"		hsMo.id hsModelId, hsMo.name hsModelName, hsMo.createdAt hsModelCreated,hsMo.updatedAt hsModelUpdated,     " + 
    				"		u.id uId, u.description uDesc, u.serial_number uSerial,u.asset_number uAsset, u.status uStatus, u.warranty_exp uWarranty, u.createdAt uCreated, u.updatedAt uUpdated,     " + 
    				"		uMo.id uMoId, uMo.name uMoName, uMo.createdAt uMoCreated,uMo.updatedAt uMoUpdated     " + 
    				"  FROM training_assets ta     " + 
    				"  LEFT JOIN system_units su on ta.cpu_id = su.id     " + 
    				"  LEFT JOIN models suMo on su.model_id = suMo.id   " + 
	     			"  LEFT JOIN training_rooms tr on ta.tr_id = tr.id     " + 
	     			"  LEFT JOIN monitors mon1 on ta.monitor_id1 = mon1.id     " + 
	     			"  LEFT JOIN models monMo on mon1.model_id = monMo.id     " + 
	     			"  LEFT JOIN monitors mon2 on ta.monitor_id2 = mon2.id     " + 
	     			"  LEFT JOIN models monMo2 on mon2.model_id = monMo2.id     " + 
	     			"  LEFT JOIN keyboards kb on ta.keyboard_id = kb.id     " + 
	     			"  LEFT JOIN models kbMo on kbMo.id = kb.model_id     " + 
	     			"  LEFT JOIN mice ms on ta.mouse_id = ms.id     " + 
	     			"  LEFT JOIN models msMo on msMo.id = ms.model_id     " + 
	     			"  LEFT JOIN headsets hs on ta.headset_id = hs.id     " + 
	     			"  LEFT JOIN ups u on ta.cpu_id = u.id   " + 
	     			"  LEFT JOIN models uMo on uMo.id = u.model_id      " + 
	     			"  LEFT JOIN models hsMo on hsMo.id = hs.model_id    " + 
	     			" ORDER BY ta.id DESC";
    	
    	
    	return jdbcTemplate.query(sql, new TrainingRoomAssetRowMapper());
    }

	public void addProductionAsset(ProductionAsset prodAsset) {
		String sql ="INSERT INTO production_assets(location_id, cpu_id, monitor_id1, monitor_id2, mouse_id,keyboard_id) VALUES(?,?,?,?,?,?)";
		jdbcTemplate.update(sql,new Object[] {
				prodAsset.getLocation().getId(),
				prodAsset.getSystemUnit().getId(),
				(prodAsset.getMonitor1() ==null) ? null: prodAsset.getMonitor1().getId(),
				(prodAsset.getMonitor2() ==null) ? null: prodAsset.getMonitor2().getId(),
				(prodAsset.getMouse() == null) ? null: prodAsset.getMouse().getId(),
				(prodAsset.getKeyboard() == null) ? null: prodAsset.getKeyboard().getId()
		});
	}

	public void updateProductionAsset(ProductionAsset prodAsset) {
		String sql ="UPDATE production_assets SET location_id=?, cpu_id=?, monitor_id1=?, monitor_id2=?, mouse_id=?,keyboard_id=? WHERE id =?";
		
		jdbcTemplate.update(sql,new Object[] {
				prodAsset.getLocation().getId(),
				prodAsset.getSystemUnit().getId(),
				(prodAsset.getMonitor1().getId() == 0) ? null: prodAsset.getMonitor1().getId(),
				(prodAsset.getMonitor2().getId() == 0) ? null: prodAsset.getMonitor2().getId(),
				(prodAsset.getMouse().getId() == 0) ? null: prodAsset.getMouse().getId(),
				(prodAsset.getKeyboard().getId() == 0) ? null: prodAsset.getKeyboard().getId(),
				prodAsset.getId()
		});
		
	}
	
	public void removeProductionAsset(ProductionAsset prodAsset) {
		String sql ="DELETE FROM production_assets WHERE id =?";
		jdbcTemplate.update(sql, new Object[] {prodAsset.getId()});
	}

	public List<TrainingRoom> getTrainingRooms() {
		String sql ="SELECT * FROM training_rooms";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<TrainingRoom>(TrainingRoom.class));
	}

	public void addTrainingRoomAsset(TrainingRoomAsset trainingAsset) {
		
		String sql ="INSERT INTO training_assets(tr_id,cpu_id,monitor_id1,monitor_id2, mouse_id,keyboard_id) VALUES(?,?,?,?,?,?)";
		jdbcTemplate.update(sql,new Object[] {
				trainingAsset.getLocation().getId(),
				trainingAsset.getSystemUnit().getId(),
				trainingAsset.getMonitor1().getId(),
				trainingAsset.getMonitor2().getId(),
				trainingAsset.getMouse().getId(),
				trainingAsset.getKeyboard().getId()
		});
	}
	
//	public void addTrainingRoomAsset(TrainingRoomAsset trAsset) {
//		String sql ="INSERT INTO production_assets(location_id, cpu_id, monitor_id1, monitor_id2, mouse_id,keyboard_id) VALUES(?,?,?,?,?,?)";
//		jdbcTemplate.update(sql,new Object[] {
//				prodAsset.getLocation().getId(),
//				prodAsset.getSystemUnit().getId(),
//				(prodAsset.getMonitor1() ==null) ? null: prodAsset.getMonitor1().getId(),
//				(prodAsset.getMonitor2() ==null) ? null: prodAsset.getMonitor2().getId(),
//				(prodAsset.getMouse() == null) ? null: prodAsset.getMouse().getId(),
//				(prodAsset.getKeyboard() == null) ? null: prodAsset.getKeyboard().getId()
//		});
//	}
	
}
