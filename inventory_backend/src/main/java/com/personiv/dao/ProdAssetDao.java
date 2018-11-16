package com.personiv.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.personiv.mapextractor.ProdAssetMapExtractor;
import com.personiv.model.assets.GeneralAsset;
import com.personiv.model.assets.Monitor;
import com.personiv.model.assets.ProdAssetStaging;
import com.personiv.model.assets.ProdMonitor;
import com.personiv.model.assets.ProdTransferStaging;
import com.personiv.model.assets.ProductionAsset;
import com.personiv.model.assets.TrainingRoomAsset;
import com.personiv.model.assets.TransferAsset;
import com.personiv.rowmapper.ProductionAssetRowMapper;
import com.personiv.rowmapper.TransferAssetRowMapper;

@Repository
@Transactional(readOnly = false)
public class ProdAssetDao  extends JdbcDaoSupport{
	
	private JdbcTemplate jdbcTemplate;

    @Autowired
    private DataSource dataSource;

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
        jdbcTemplate = getJdbcTemplate();
    }
    
    public List<ProductionAsset> getProductionAssets(){
    	String sql ="             SELECT pa.id,su.id suId, su.computer_name suName, su.description suDesc, su.serial_number suSerial, su.asset_number suAsset, su.status suStatus, su.warranty_exp suWarranty, su.mac_address suMac,su.createdAt suCreated, su.updatedAt suUpdated,    " + 
    			"    			 		 suMo.id suModelId, suMo.name suModelName, suMo.createdAt suModelCreated, suMo.updatedAt suModelUpdated,     \r\n" + 
    		
    			"    			         loc.id locId, loc.name locName, loc.createdAt locCreated, loc.updatedAt locUpdated,   \r\n" + 
    		
    			"    			 		 mon1.id monId, mon1.description monDesc, mon1.serial_number monSerial, mon1.asset_number monAsset,mon1.status monStatus, mon1.warranty_exp monWarranty, mon1.createdAt monCreated, mon1.updatedAt monUpdated,    " + 
    			"    			 		 monMo.id momodelId, monMo.name momodelName, monMo.createdAt momoCreated, monMo.updatedAt momoUpdated,     \r\n" + 
    			
    			"    			 		 mon2.id monId2, mon2.description monDesc2, mon2.serial_number monSerial2, mon2.asset_number monAsset2,mon2.status monStatus2, mon2.warranty_exp monWarranty2, mon2.createdAt monCreated2, mon2.updatedAt monUpdated2,      " + 
    			"    			 		 monMo2.id momodelId2, monMo2.name momodelName2, monMo2.createdAt momoCreated2, monMo2.updatedAt momoUpdated2,     \r\n" + 
    		
    			"    			 		 kb.id kbId, kb.description kbDesc, kb.serial_number kbSerial, kb.asset_number kbAsset, kb.status kbStatus,kb.warranty_exp kbWarranty,kb.createdAt kbCreated, kb.updatedAt kbUpdated,     " + 
    			"    			 		 kbMo.id kbMoId, kbMo.name kbMoName, kbMo.createdAt kbMoCreated, kbMo.updatedAt kbMoUpdated,     \r\n" + 
    		
    			"    			 		 ms.id msId, ms.description msDesc,ms.serial_number msSerial, ms.asset_number msAsset, ms.status msStatus,ms.warranty_exp msWarranty, ms.createdAt msCreated, ms.updatedAt msUpdated,     " + 
    			"    			 		 msMo.id msMoId, msMo.name msMoName, msMo.createdAt msMoCreated, msMo.updatedAt msMoUpdated,     \r\n" + 
    		
    		
    			"    			 		 u.id uId, u.description uDesc, u.serial_number uSerial,u.asset_number uAsset, u.status uStatus, u.warranty_exp uWarranty, u.createdAt uCreated, u.updatedAt uUpdated,     " + 
    			"    			 		 uMo.id uMoId, uMo.name uMoName, uMo.createdAt uMoCreated,uMo.updatedAt uMoUpdated     " + 
    		
    			"    			   FROM prod_assets pa     " + 
    			"    			   LEFT JOIN system_units su on pa.cpu_id = su.id     " + 
    			"    			   LEFT JOIN models suMo on su.model_id = suMo.id   " + 
    			
    			"    			   LEFT JOIN locations loc on pa.loc_id = loc.id     " + 
    			
    			"    			   LEFT JOIN prod_asset_monitors pam on pa.id = pam.prod_asset_id " + 
    			"    			   LEFT JOIN monitors mon1 on pam.mon_id = mon1.id  AND pam.mon_index = 1    " + 
    			"    			   LEFT JOIN models monMo on mon1.model_id = monMo.id     " + 
    			
    			"    				LEFT JOIN prod_asset_monitors pam2 on pa.id = pam2.prod_asset_id " + 
    			"    			   LEFT JOIN monitors mon2 on pam2.mon_id = mon2.id AND pam2.mon_index =2     " + 
    			"    			   LEFT JOIN models monMo2 on mon2.model_id = monMo2.id     " + 
    			
    			"    			   LEFT JOIN keyboards kb on pa.kb_id = kb.id     " + 
    			"    			   LEFT JOIN models kbMo on kbMo.id = kb.model_id     " + 
    			
    			"    			   LEFT JOIN mice ms on pa.ms_id = ms.id     " + 
    			"    			   LEFT JOIN models msMo on msMo.id = ms.model_id     " + 
    			
    			
    			"    			   LEFT JOIN ups u on pa.ups_id = u.id   " + 
    			"    			   LEFT JOIN models uMo on uMo.id = u.model_id ";
//    			"                   GROUP BY pa.id";
    	return jdbcTemplate.query(sql,  new ProdAssetMapExtractor() );
    	//return jdbcTemplate.query(sql, new ProductionAssetRowMapper());
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

//	public void updateProductionAsset(ProductionAsset prodAsset) {
//		String sql ="UPDATE production_assets SET location_id=?, cpu_id=?, monitor_id1=?, monitor_id2=?, mouse_id=?,keyboard_id=? WHERE id =?";
//		
//		jdbcTemplate.update(sql,new Object[] {
//				prodAsset.getLocation().getId(),
//				prodAsset.getSystemUnit().getId(),
//				(prodAsset.getMonitor1().getId() == 0) ? null: prodAsset.getMonitor1().getId(),
//				(prodAsset.getMonitor2().getId() == 0) ? null: prodAsset.getMonitor2().getId(),
//				(prodAsset.getMouse().getId() == 0) ? null: prodAsset.getMouse().getId(),
//				(prodAsset.getKeyboard().getId() == 0) ? null: prodAsset.getKeyboard().getId(),
//				prodAsset.getId()
//		});
//		
//	}
	public void updateProductionAsset(ProductionAsset prodAsset) {
		String sql ="UPDATE prod_assets SET cpu_id =?, kb_id =?, ms_id =?, ups_id=?  WHERE id =?";
		
		//update all assets except monitor because they have one to many relationship
		jdbcTemplate.update(sql,new Object[] {
				prodAsset.getSystemUnit().getId(),
				(prodAsset.getKeyboard() == null) ? null: prodAsset.getKeyboard().getId(),
				(prodAsset.getMouse() == null) ? null: prodAsset.getMouse().getId(),
				(prodAsset.getUps() == null) ? null: prodAsset.getUps().getId(),
				prodAsset.getId()
		});
		
		//upadte monitor A
		String monSqlA = "UPDATE prod_asset_monitors SET mon_id = ? WHERE prod_asset_id =? AND mon_index =1";
		jdbcTemplate.update(monSqlA,new Object[] {
			(prodAsset.getMonitor1()== null) ? null : prodAsset.getMonitor1().getId(),
			prodAsset.getId()
		});

		//upadte monitor B
		String monSqlB = "UPDATE prod_asset_monitors SET mon_id = ? WHERE prod_asset_id =? AND mon_index =2";
		jdbcTemplate.update(monSqlB,new Object[] {
			(prodAsset.getMonitor2() == null) ? null : prodAsset.getMonitor2().getId(),
			prodAsset.getId()
		});
		
	}
	public void removeProductionAsset(ProductionAsset prodAsset) {
		String sql ="DELETE FROM production_assets WHERE id =?";
		jdbcTemplate.update(sql, new Object[] {prodAsset.getId()});
	}

	public void removeMonitor1(ProductionAsset prodAsset) {
		String sql ="UPDATE production_assets SET monitor_id1 = NULL WHERE id = ?";
				
		jdbcTemplate.update(sql, new Object[] {prodAsset.getId()});
	}
	
	public void removeMonitor2(ProductionAsset prodAsset) {
		String sql ="UPDATE production_assets SET monitor_id2 = NULL WHERE id = ?";
		jdbcTemplate.update(sql, new Object[] {prodAsset.getId()});
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

	public List<ProdAssetStaging> getProdAssets() {
		
		String sql ="SELECT pa.location, "+
					"       pa.cpu systemUnit "+
				    "  FROM prod_assets pa";
		
		String monitorSql ="SELECT pm.monitor,mon_num monitorIndex FROM prod_monitors pm WHERE pm.location =?";		
		List<ProdAssetStaging> prodAssets = jdbcTemplate.query(sql, new BeanPropertyRowMapper<ProdAssetStaging>(ProdAssetStaging.class));
		
		return prodAssets;
	}
	
	public void batchUpdate(List<ProductionAsset> assets) {
		String batchUpdate="UPDATE prod_assets SET  cpu_id =?, kb_id =?, ups_id =?, ms_id =? WHERE loc_id = ?";
	
		List<ProdMonitor> monitors = new ArrayList<ProdMonitor>();
		
		//convert into single array for easier batch insert
		for(ProductionAsset asset: assets) {
			ProdMonitor pm = new ProdMonitor();
			pm.setMonitor(asset.getMonitor1());
			pm.setProdAssetId(asset.getId());
			pm.setMonitorIndex(1);
			
			monitors.add(pm);
			ProdMonitor pm2 = new ProdMonitor();
			pm2.setMonitor(asset.getMonitor2());
			pm2.setProdAssetId(asset.getId());
			pm2.setMonitorIndex(2);
			monitors.add(pm2);
		}
		
		jdbcTemplate.batchUpdate(batchUpdate, new BatchPreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ProductionAsset asset = assets.get(i);
				
				
				
				if(asset.getSystemUnit() == null) {
					ps.setNull(1, java.sql.Types.INTEGER);
				}else {
					ps.setLong(1, asset.getSystemUnit() .getId());
				}
				
				if(asset.getKeyboard() == null) {
					ps.setNull(2, java.sql.Types.INTEGER);
				}else {
					ps.setLong(2, asset.getKeyboard().getId());
				}
				
				if(asset.getUps() == null) {
					ps.setNull(3, java.sql.Types.INTEGER);
				}else {
					ps.setLong(3, asset.getUps().getId());
				}
				
				if(asset.getMouse() == null) {
					ps.setNull(4, java.sql.Types.INTEGER);
				}else {
					ps.setLong(4, asset.getMouse().getId());
				}
				ps.setLong(5, asset.getLocation().getId());
				
				
				
			}

			@Override
			public int getBatchSize() {
				return assets.size();
			}
			
		});
		
		
		
		String monitorsSql ="UPDATE prod_asset_monitors  SET mon_id = ? WHERE prod_asset_id = ? AND mon_index = ?";
		
		jdbcTemplate.batchUpdate(monitorsSql, new BatchPreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ProdMonitor m = monitors.get(i);
				
			
				if(m.getMonitor() == null) {
					ps.setNull(1, java.sql.Types.INTEGER);
				}else {
					ps.setLong(1, m.getMonitor().getId());

					System.out.println(m.getProdAssetId()+","+m.getMonitor().getId()+","+m.getMonitorIndex());
				}
				ps.setLong(2, m.getProdAssetId());
				ps.setInt(3, m.getMonitorIndex());
				
			}

			@Override
			public int getBatchSize() {
				// TODO Auto-generated method stub
				return monitors.size();
			}
			
		});
		
		
	}
	/**
	 * TODO: insert to the tracker the current values of the rows to be updated before 
	 * updating the production assets
	 * 
	 * Create a list of production asset for single batch update
	 * Empty the source of the production asset then updated the location of the
	 * destination asset with the values of the source
	 * @param transferAssets
	 * assets from request
	 */
	public void batchTransferUpdate(List<TransferAsset> transferAssets) {
		List<ProductionAsset> prodAsset = new ArrayList<ProductionAsset>();		
		List<ProdMonitor> prodMonitors = new ArrayList<ProdMonitor>();
		
		for(TransferAsset asset: transferAssets) {
			ProductionAsset sourceAsset = new ProductionAsset();
			sourceAsset.setLocation(asset.getFromLocation());
			
			ProductionAsset  desAsset = new ProductionAsset();
			desAsset.setLocation(asset.getToLocation());
			desAsset.setSystemUnit(asset.getSystemUnit());
			desAsset.setKeyboard(asset.getKeyboard());
			desAsset.setUps(desAsset.getUps());
			
			
			
			//Separate batch update for monitors since this is a different (normalized) table
			
			ProdMonitor sourceMonitorA = new ProdMonitor();
			sourceMonitorA.setProdAssetId(asset.getFromProdAssetId());
			
			ProdMonitor sourceMonitorB = new ProdMonitor();
			sourceMonitorB.setProdAssetId(asset.getFromProdAssetId());
			
			
			ProdMonitor prodMonitorA = new ProdMonitor();
			prodMonitorA.setProdAssetId(asset.getToProdAssetId());
			prodMonitorA.setMonitor(asset.getMonitorA());
			prodMonitorA.setMonitorIndex(1);
			
			ProdMonitor prodMonitorB = new ProdMonitor();
			prodMonitorB.setProdAssetId(asset.getToProdAssetId());
			prodMonitorB.setMonitor(asset.getMonitorB());
			prodMonitorB.setMonitorIndex(2);
			
			prodMonitors.add(prodMonitorA);
			prodMonitors.add(prodMonitorB);
		}
		
		//batch update for transferring assets
		String prodTransfer ="UPDATE prod_assets SET cpu_id =?, kb_id =?, ups_id =?, ms_id =? where loc_id =?";
		jdbcTemplate.batchUpdate(prodTransfer, new BatchPreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ProductionAsset productionAsset = prodAsset.get(i);
				
				//delete if blank
				if(productionAsset.getSystemUnit() == null) {
					ps.setNull(1, java.sql.Types.INTEGER);
				}else {
					//update if has value
					ps.setLong(1, productionAsset.getSystemUnit().getId());
				}
				
				if(productionAsset.getKeyboard() == null) {
					ps.setNull(2, java.sql.Types.INTEGER);
				}else {
					ps.setLong(2, productionAsset.getKeyboard().getId());
				}
				
				if(productionAsset.getSystemUnit() == null) {
					ps.setNull(3, java.sql.Types.INTEGER);
				}else {
					ps.setLong(3, productionAsset.getSystemUnit().getId());
				}
				
				if(productionAsset.getMouse() == null) {
					ps.setNull(4, java.sql.Types.INTEGER);
				}else {
					ps.setLong(4, productionAsset.getMouse().getId());
				}
				ps.setLong(5, productionAsset.getLocation().getId());
			}

			@Override
			public int getBatchSize() {
				return prodAsset.size();
			}
			
		});
		
		//batch update for transferring monitors
		String monitorTransfer ="UPDATE prod_asset_monitors SET mon_id = ? WHERE prod_asset_id = ? AND mon_index =?";
		jdbcTemplate.batchUpdate(monitorTransfer, new BatchPreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ProdMonitor monitor = prodMonitors.get(i);
				
				if(monitor.getMonitor() == null) {
					ps.setNull(1, java.sql.Types.INTEGER);
				}else {
					ps.setLong(1, monitor.getMonitor().getId());
				}
				
				ps.setLong(2, monitor.getProdAssetId());
				ps.setInt(3, monitor.getMonitorIndex());
			}

			@Override
			public int getBatchSize() {
				return prodMonitors.size();
			}
			
		});
	}
	public List<ProductionAsset> batchUpload(List<ProdAssetStaging> assets){
		
		String batchUpdateStg ="INSERT INTO prod_asset_stg VALUES(?,?,?,?,?,?,?)";
		
		//insert into staging table
		jdbcTemplate.batchUpdate(batchUpdateStg, new BatchPreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ProdAssetStaging asset = assets.get(i);
			
				ps.setString(1, asset.getLocation());
				ps.setString(2, asset.getSystemUnit());
				ps.setString(3, asset.getMonitorA());
				ps.setString(4, asset.getMonitorB());
				ps.setString(5, asset.getKeyboard());
				ps.setString(6, asset.getMouse());
				ps.setString(7, asset.getUps());
			}

			@Override
			public int getBatchSize() {
				return assets.size();
			}
			
		});
		
		String batchSql ="	   SELECT pas.id,su.id suId, su.computer_name suName, su.description suDesc, su.serial_number suSerial, su.asset_number suAsset, su.status suStatus, su.warranty_exp suWarranty, su.mac_address suMac,su.createdAt suCreated, su.updatedAt suUpdated," + 
		    			"		      suMo.id suModelId, suMo.name suModelName, suMo.createdAt suModelCreated, suMo.updatedAt suModelUpdated, " + 
		    			
		    			"             loc.id locId, loc.name locName, loc.createdAt locCreated, loc.updatedAt locUpdated, "+
		    		
		    			
		    			"		       mon1.id monId, mon1.description monDesc, mon1.serial_number monSerial, mon1.asset_number monAsset,mon1.status monStatus, mon1.warranty_exp monWarranty, mon1.createdAt monCreated, mon1.updatedAt monUpdated, " + 
		    			"		       monMo.id momodelId, monMo.name momodelName, monMo.createdAt momoCreated, monMo.updatedAt momoUpdated, " + 
		
		    			"		       mon2.id monId2, mon2.description monDesc2, mon2.serial_number monSerial2, mon2.asset_number monAsset2,mon2.status monStatus2, mon2.warranty_exp monWarranty2, mon2.createdAt monCreated2, mon2.updatedAt monUpdated2,  " + 
		    			"		       monMo2.id momodelId2, monMo2.name momodelName2, monMo2.createdAt momoCreated2, monMo2.updatedAt momoUpdated2, " + 
		
		    			"		       kb.id kbId, kb.description kbDesc, kb.serial_number kbSerial, kb.asset_number kbAsset, kb.status kbStatus,kb.warranty_exp kbWarranty,kb.createdAt kbCreated, kb.updatedAt kbUpdated, " + 
		    			"		       kbMo.id kbMoId, kbMo.name kbMoName, kbMo.createdAt kbMoCreated, kbMo.updatedAt kbMoUpdated, " + 
		
		    			"		       ms.id msId, ms.description msDesc,ms.serial_number msSerial, ms.asset_number msAsset, ms.status msStatus,ms.warranty_exp msWarranty, ms.createdAt msCreated, ms.updatedAt msUpdated, " + 
		    			"		       msMo.id msMoId, msMo.name msMoName, msMo.createdAt msMoCreated, msMo.updatedAt msMoUpdated, " + 
		
		    		
		    			"		       u.id uId, u.description uDesc, u.serial_number uSerial,u.asset_number uAsset, u.status uStatus, u.warranty_exp uWarranty, u.createdAt uCreated, u.updatedAt uUpdated, " + 
		    			"		       uMo.id uMoId, uMo.name uMoName, uMo.createdAt uMoCreated,uMo.updatedAt uMoUpdated " + 
		    			"         FROM prod_asset_stg pa "+
		    			"    LEFT JOIN system_units su on pa.cpu = su.asset_number " + 
		    			"    LEFT JOIN models suMo on su.model_id = suMo.id   " + 
		    			"	 LEFT JOIN locations loc on pa.loc = loc.name  " + 
		    			"    LEFT JOIN prod_assets pas ON pas.loc_id = loc.id "+
		    			"	 LEFT JOIN monitors mon1 on pa.mon_a = mon1.asset_number  " + 
		    			"	 LEFT JOIN models monMo on mon1.model_id = monMo.id     " + 
		    			"	 LEFT JOIN monitors mon2 on pa.mon_b = mon2.asset_number" + 
		    			"	 LEFT JOIN models monMo2 on mon2.model_id = monMo2.id     " + 
		    			"	 LEFT JOIN keyboards kb on pa.keyboard = kb.asset_number" + 
		    			"	 LEFT JOIN models kbMo on kbMo.id = kb.model_id     " + 
		    			"	 LEFT JOIN mice ms on pa.mouse = ms.asset_number     " + 
		    			"	 LEFT JOIN models msMo on msMo.id = ms.model_id     " + 
		    			"	 LEFT JOIN ups u on pa.ups_ = u.asset_number   " + 
		    			"	 LEFT JOIN models uMo on uMo.id = u.model_id   " ; 
		    			 
		    			
		//get the data with foreign key joined with the staging table
		List<ProductionAsset> prodAssets = jdbcTemplate.query(batchSql, new ProductionAssetRowMapper());
		
		String clearTable ="TRUNCATE prod_asset_stg";
		
		//clear Staging table
		jdbcTemplate.update(clearTable);
		
	
		return prodAssets;
	}
	
	

	public List<TransferAsset> batchTransferUpload(List<ProdTransferStaging> prodAssets) {
		
		String transferStg ="INSERT INTO prod_transfer_stg(from_loc, to_loc, cpu ,mon_a, mon_b, keyboard, mouse ,ups ) VALUES(?,?,?,?,?,?,?,?)";
		jdbcTemplate.batchUpdate(transferStg, new BatchPreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ProdTransferStaging asset = prodAssets.get(i);
			    ps.setString(1, asset.getFromLoc());
			    ps.setString(2, asset.getToLoc());
			    ps.setString(3, asset.getSystemUnit());
			    ps.setString(4, asset.getMonitorA());
			    ps.setString(5, asset.getMonitorB());
			    ps.setString(6, asset.getKeyboard());
			    ps.setString(7, asset.getMouse());
			    ps.setString(8, asset.getUps());
			    
			}

			@Override
			public int getBatchSize() {
				return prodAssets.size();
			}
			
		});
		
		String batchSql ="     SELECT pas.id prodId1, pas2.id prodId2,"+
						"             su.id suId, su.computer_name suName, su.description suDesc, su.serial_number suSerial, su.asset_number suAsset, su.status suStatus, su.warranty_exp suWarranty, su.mac_address suMac,su.createdAt suCreated, su.updatedAt suUpdated," + 
		    			"		      suMo.id suModelId, suMo.name suModelName, suMo.createdAt suModelCreated, suMo.updatedAt suModelUpdated, " + 
		    			
		    			"             loc.id locId, loc.name locName, loc.createdAt locCreated, loc.updatedAt locUpdated, "+
		    			"             loc2.id locId2, loc2.name locName2, loc2.createdAt locCreated2, loc2.updatedAt locUpdated2, "+
		        		
		    			
		    			"		       mon1.id monId, mon1.description monDesc, mon1.serial_number monSerial, mon1.asset_number monAsset,mon1.status monStatus, mon1.warranty_exp monWarranty, mon1.createdAt monCreated, mon1.updatedAt monUpdated, " + 
		    			"		       monMo.id momodelId, monMo.name momodelName, monMo.createdAt momoCreated, monMo.updatedAt momoUpdated, " + 
		
		    			"		       mon2.id monId2, mon2.description monDesc2, mon2.serial_number monSerial2, mon2.asset_number monAsset2,mon2.status monStatus2, mon2.warranty_exp monWarranty2, mon2.createdAt monCreated2, mon2.updatedAt monUpdated2,  " + 
		    			"		       monMo2.id momodelId2, monMo2.name momodelName2, monMo2.createdAt momoCreated2, monMo2.updatedAt momoUpdated2, " + 
		
		    			"		       kb.id kbId, kb.description kbDesc, kb.serial_number kbSerial, kb.asset_number kbAsset, kb.status kbStatus,kb.warranty_exp kbWarranty,kb.createdAt kbCreated, kb.updatedAt kbUpdated, " + 
		    			"		       kbMo.id kbMoId, kbMo.name kbMoName, kbMo.createdAt kbMoCreated, kbMo.updatedAt kbMoUpdated, " + 
		
		    			"		       ms.id msId, ms.description msDesc,ms.serial_number msSerial, ms.asset_number msAsset, ms.status msStatus,ms.warranty_exp msWarranty, ms.createdAt msCreated, ms.updatedAt msUpdated, " + 
		    			"		       msMo.id msMoId, msMo.name msMoName, msMo.createdAt msMoCreated, msMo.updatedAt msMoUpdated, " + 
		
		    		
		    			"		       u.id uId, u.description uDesc, u.serial_number uSerial,u.asset_number uAsset, u.status uStatus, u.warranty_exp uWarranty, u.createdAt uCreated, u.updatedAt uUpdated, " + 
		    			"		       uMo.id uMoId, uMo.name uMoName, uMo.createdAt uMoCreated,uMo.updatedAt uMoUpdated " + 
		    			"         FROM prod_transfer_stg pa "+
		    			"    LEFT JOIN system_units su on pa.cpu = su.asset_number " + 
		    			"    LEFT JOIN models suMo on su.model_id = suMo.id   " + 
		    			"	 LEFT JOIN locations loc on pa.from_loc = loc.name  " + 
		    			"	 LEFT JOIN locations loc2 on pa.to_loc = loc2.name  " + 
		    			"    LEFT JOIN prod_assets pas ON pas.loc_id = loc.id "+
		    			"    LEFT JOIN prod_assets pas2 ON pas2.loc_id = loc2.id "+
		    			"	 LEFT JOIN monitors mon1 on pa.mon_a = mon1.asset_number  " + 
		    			"	 LEFT JOIN models monMo on mon1.model_id = monMo.id     " + 
		    			"	 LEFT JOIN monitors mon2 on pa.mon_b = mon2.asset_number" + 
		    			"	 LEFT JOIN models monMo2 on mon2.model_id = monMo2.id     " + 
		    			"	 LEFT JOIN keyboards kb on pa.keyboard = kb.asset_number" + 
		    			"	 LEFT JOIN models kbMo on kbMo.id = kb.model_id     " + 
		    			"	 LEFT JOIN mice ms on pa.mouse = ms.asset_number     " + 
		    			"	 LEFT JOIN models msMo on msMo.id = ms.model_id     " + 
		    			"	 LEFT JOIN ups u on pa.ups = u.asset_number   " + 
		    			"	 LEFT JOIN models uMo on uMo.id = u.model_id   " ; 
		
		List<TransferAsset> transferAssets = jdbcTemplate.query(batchSql, new TransferAssetRowMapper());
	
//		String clearTable ="TRUNCATE prod_transfer_stg";
//		
//		jdbcTemplate.update(clearTable);
		
		return transferAssets;
	}
	
}
