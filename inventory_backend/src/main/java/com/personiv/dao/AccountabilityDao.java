package com.personiv.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.personiv.mapextractor.AssetAccountabilityMapExtractor;
import com.personiv.model.Accountability;
import com.personiv.model.AssetAccountable;
import com.personiv.model.AssetAccountabilities;
import com.personiv.model.RawAsset;
import com.personiv.rowmapper.AssetAccountableRowMapper;
import com.personiv.service.FileService;

@Repository
@Transactional(readOnly = false)
public class AccountabilityDao extends JdbcDaoSupport {
	
	private JdbcTemplate jdbcTemplate;

    @Autowired
    private DataSource dataSource;
    
    @Autowired
    private FileService fileService;

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
        jdbcTemplate = getJdbcTemplate();
    }
    
    public List <AssetAccountabilities> getAccountabilities(){
    	String sql ="SELECT acc.id acc_id, ast.id as_acc_id, emp_name, acc.emp_id,acc.created_at, "+
    			    "       ast.asset_number, ast.serial_number,ast.asset_type, ast.attachment, ast.remarks,ast.updated_at " + 
    			    "  FROM accountability acc " + 
    			    "  LEFT JOIN asset_acc ast ON ast.acc_id = acc.id";
    	
    	
    	
//    	List <AssetAccountability> acc  =jdbcTemplate.query(sql, new BeanPropertyRowMapper<AssetAccountability>(AssetAccountability.class));
   
    	return jdbcTemplate.query(sql, new AssetAccountabilityMapExtractor());
    	
   
    }
    
    public List<Accountability> getAccountabilitiesByEmployee(){
    	String sql ="";
    	return null;
    }

	public void addAccountability(Accountability acc) {
//		String sql ="INSERT INTO accountability(employee,it_staff) VALUES(?,?)";
//		
//		String sql2 ="INSERT INTO asset_accountability(acc_id,asset_tag,item_desc) VALUES(?,?,?)";
//		
//		KeyHolder keyHolder = new GeneratedKeyHolder();
//		
//		jdbcTemplate.update( new PreparedStatementCreator() {
//
//			@Override
//			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
//				PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);	
//				ps.setString(1, acc.getEmployee());
//				ps.setString(2, acc.getIt_staff());
//				return ps;
//			}
//			
//		},keyHolder); //create a prepared statement creator to retrieve the id of the row created
//		
//		acc.setId((Long)keyHolder.getKey()); //assign this id to be inserted to the bridge table 
//		
//		jdbcTemplate.batchUpdate(sql2, new AccountabilityBatchPreparedStatement(acc.getId(),acc.getAccountableAssets()));
//		
//		
	}
	
	public void addAccountableAsset(AssetAccountable acc) {
		String sql ="INSERT INTO asset_acc(acc_id,asset_number,serial_number,asset_type) VALUES(?,?,?,?)";		
		jdbcTemplate.update(sql,new Object[] {
				acc.getAccId(),
				acc.getAccountability().getAssetNumber(),
				acc.getAccountability().getSerialNumber(),
				acc.getAccountability().getAssetType()}
		);
		
	}
	


	public void updateAccountable(AssetAccountable acc) {
		String sql = "UPDATE asset_accountability SET asset_tag =?, item_desc =?, date_returned =?, status =? WHERE id=?";
		
		
		
	}

	public void deleteAccountable(AssetAccountable acc) {
	
	}

	public List<RawAsset> getAllAssets() {
		String sql ="   SELECT cpu.asset_number assetNumber, cpu.serial_number serialNumber, 'CPU' as 'assetType' " + 
					"     FROM system_units cpu " + 
					"UNION ALL " + 
					"   SELECT m.asset_number,m.serial_number serialNumber, 'MONITOR' as 'assetType' " + 
					"     FROM monitors m " + 
					"UNION ALL " + 
					"   SELECT kb.asset_number, kb.serial_number serialNumber,'KEYBOARD' as 'assetType' " + 
					"     FROM keyboards kb " + 
					"UNION ALL " + 
					"   SELECT mc.asset_number, mc.serial_number serialNumber,'MOUSE' as 'assetType' " + 
					"     FROM mice mc " + 
					"UNION ALL " + 
					"   SELECT hs.asset_number, hs.serial_number serialNumber,'HEADSET' as 'assetType' " + 
					"     FROM headsets hs " + 
					"UNION ALL " + 
					"   SELECT r.asset_number, r.asset_number serialNumber,'RFID' as 'assetType' " + 
					"     FROM rfids r ";
	return jdbcTemplate.query(sql, new BeanPropertyRowMapper<RawAsset>(RawAsset.class));
	}

	public AssetAccountable getAssetAccountableById(Long id) {
		String sql ="SELECT acc.id accId, ast.id actId, emp_name employeeName, acc.emp_id employeeNumber, "+
			    "       ast.asset_number assetNumber,ast.serial_number serialNumber, ast.asset_type assetType, ast.attachment, ast.remarks, ast.created_at createdAt, ast.updated_at updatedAt " + 
			    "  FROM accountability acc " + 
			    "  JOIN asset_acc ast ON ast.acc_id = acc.id "+
			    " WHERE ast.id = ?";
		return jdbcTemplate.queryForObject(sql,new Object[] {id}, new AssetAccountableRowMapper());
	}

	public void uploadAttachment(Accountability acc, MultipartFile file) throws IOException {
		UUID uuid = UUID.randomUUID();
    	String fileName = (file ==null)? null : uuid.toString() +"."+ FilenameUtils.getExtension(file.getOriginalFilename());
    	String path="/home/opt/tomcat/inventory_uploads/";
    	
    	String sql ="UPDATE asset_acc SET attachment = ?, updated_at = CURRENT_TIMESTAMP WHERE id = ?";
    	jdbcTemplate.update(sql,new Object[] {fileName,acc.getId()});
    	fileService.uploadFile(file, path,fileName);
	}

	public Resource downloadAttachment(String filename) {
		String path="/home/opt/tomcat/inventory_uploads/"+filename;
		return fileService.loadFile(path);
	}

	public void updateRemarks(Accountability acc) {
		String sql="UPDATE asset_acc SET remarks = ?, updated_at =  CURRENT_TIMESTAMP WHERE id =?";
		jdbcTemplate.update(sql, new Object[] {acc.getRemarks(),acc.getId()});
		
	}
    
    
}
