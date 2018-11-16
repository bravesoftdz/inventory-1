package com.personiv.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.POIXMLException;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.personiv.model.assets.GeneralAsset;
import com.personiv.model.assets.ProdAssetStaging;
import com.personiv.model.assets.ProdMonitor;
import com.personiv.model.assets.ProdTransferStaging;

public class DocumentParser {
	
	
	public List<ProdAssetStaging> importFromFile(MultipartFile file){
		List<ProdAssetStaging> prodAssets = new ArrayList<ProdAssetStaging>();
		
		try {
			
			XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
			XSSFSheet worksheet = workbook.getSheetAt(0);
			
			for(int x= 1; x < worksheet.getPhysicalNumberOfRows(); x++) {
				XSSFRow row = worksheet.getRow(x);
				
				ProdAssetStaging asset = new ProdAssetStaging();
				
				String location = (row.getCell(0) == null) ? null : row.getCell(0).getStringCellValue();
				String systemUnit = (row.getCell(1) == null) ? null : row.getCell(1).getStringCellValue();
				
			
				asset.setLocation(location);
				asset.setSystemUnit(systemUnit);
				
				
				String monitorA = (row.getCell(2) == null) ? null : row.getCell(2).getStringCellValue();
				String monitorB = (row.getCell(3) == null) ? null : row.getCell(3).getStringCellValue();
				String keyboard = (row.getCell(4) == null) ? null : row.getCell(4).getStringCellValue();
				String mouse = (row.getCell(5) == null) ? null : row.getCell(5).getStringCellValue();
				String ups = (row.getCell(6) == null) ? null : row.getCell(6).getStringCellValue();
				
				asset.setMonitorA(monitorA);
				asset.setMonitorB(monitorB);
				asset.setKeyboard(keyboard);
				asset.setMouse(mouse);
				asset.setUps(ups);
				
				
				prodAssets.add(asset);
			
				
			}
			
		}catch (IOException e) {
			e.printStackTrace();
			
		}catch(POIXMLException ex) {
		
		}
		return prodAssets;
	}
	
	
	public List<ProdTransferStaging> importTransferFromFile(MultipartFile file){
		
		List<ProdTransferStaging> prodAssets = new ArrayList<ProdTransferStaging>();
		
		try {
			
			XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
			XSSFSheet worksheet = workbook.getSheetAt(0);
			
			for(int x= 1; x < worksheet.getPhysicalNumberOfRows(); x++) {
				XSSFRow row = worksheet.getRow(x);
				
				ProdTransferStaging asset = new ProdTransferStaging();
				
				String fromLocation = (row.getCell(0) == null) ? null : row.getCell(0).getStringCellValue();
				String toLocation = (row.getCell(1) == null) ? null : row.getCell(1).getStringCellValue();
				String systemUnit = (row.getCell(2) == null) ? null : row.getCell(2).getStringCellValue();				
				String monitorA = (row.getCell(3) == null) ? null : row.getCell(3).getStringCellValue();
				String monitorB = (row.getCell(4) == null) ? null : row.getCell(4).getStringCellValue();
				String keyboard = (row.getCell(5) == null) ? null : row.getCell(5).getStringCellValue();
				String mouse = (row.getCell(6) == null) ? null : row.getCell(6).getStringCellValue();
				String ups = (row.getCell(7) == null) ? null : row.getCell(7).getStringCellValue();
				
				asset.setFromLoc(fromLocation);
				asset.setToLoc(toLocation);
				asset.setSystemUnit(systemUnit);
				asset.setMonitorA(monitorA);
				asset.setMonitorB(monitorB);
				asset.setKeyboard(keyboard);
				asset.setMouse(mouse);
				asset.setUps(ups);
				
				
				prodAssets.add(asset);
			
				
			}
			
		}catch (IOException e) {
			e.printStackTrace();
			
		}catch(POIXMLException ex) {}
		return prodAssets;
	}

}
