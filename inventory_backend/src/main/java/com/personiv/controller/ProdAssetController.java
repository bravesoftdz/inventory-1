package com.personiv.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.personiv.model.ErrorResponse;
import com.personiv.model.assets.ProdAssetStaging;
import com.personiv.model.assets.ProdTransferStaging;
import com.personiv.model.assets.ProductionAsset;
import com.personiv.model.assets.TransferAsset;
import com.personiv.service.ProdAssetService;
import com.personiv.utils.DocumentParser;

@RestController
@CrossOrigin(origins = "*")
public class ProdAssetController {
	
	@Autowired
	private ProdAssetService aslService;
	
	@RequestMapping(value = "/prod_assets", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getProdAssets() {
		return ResponseEntity.ok(aslService.getProdAssets());
	}
	

	
	@RequestMapping(value = "/production_assets", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ProductionAsset> getProductionAssets() {
		return aslService.getProductionAssets();
	}
	
	@RequestMapping(value = "/production_assets", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addProductionAsset(@RequestBody ProductionAsset prodAsset) {
		
		aslService.addProductionAsset(prodAsset);
		return ResponseEntity.ok(prodAsset);
	}
	
	@RequestMapping(value = "/production_assets", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateProductionAsset(@RequestBody ProductionAsset prodAsset) {
		
		try{

			aslService.updateProductionAsset(prodAsset);
			return ResponseEntity.ok(prodAsset);
		}catch(DuplicateKeyException e) {
			return ResponseEntity.status(422).body(new ErrorResponse("Duplicate entry"));
		}catch(Exception e) {
			return ResponseEntity.status(500).body(new ErrorResponse("Failed executing request"));
		}
	}
	
	@RequestMapping(value = "/production_assets/removeMonitor1", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> removeMonitor1(@RequestBody ProductionAsset prodAsset) {
		
		aslService.removeMonitor1(prodAsset);
		return ResponseEntity.ok(prodAsset);
	}
	
	@RequestMapping(value = "/production_assets/removeMonitor2", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> removeMonitor2(@RequestBody ProductionAsset prodAsset) {
		
		aslService.removeMonitor2(prodAsset);
		return ResponseEntity.ok(prodAsset);
	}
	
	@RequestMapping(value = "/production_assets/deploy/batch_upload", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> batchUpload(@RequestParam(value="file", required = false) MultipartFile file) throws IOException{
	try {
			
			//System.out.println(data);
			if(file != null) {
				DocumentParser parser = new DocumentParser();
				
				List<ProdAssetStaging> prodAssets = parser.importFromFile(file);
				
				
				return ResponseEntity.ok(aslService.batchUpload(prodAssets));
			}else {
				return ResponseEntity.status(422).body(new ErrorResponse("no file uploaded"));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(500).body(new ErrorResponse("Something went wrong"));
		}
	}
	
	@RequestMapping(value = "/production_assets/transfer/batch_upload", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> batchTransferUpload(@RequestParam(value="file", required = false) MultipartFile file) throws IOException{
	try {
			
			//System.out.println(data);
			if(file != null) {
				DocumentParser parser = new DocumentParser();
				
				List<ProdTransferStaging> prodAssets = parser.importTransferFromFile(file);
				
				
				return ResponseEntity.ok(aslService.batchTransferUpload(prodAssets));
			}else {
				return ResponseEntity.status(422).body(new ErrorResponse("no file uploaded"));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(500).body(new ErrorResponse("Something went wrong"));
		}
	}
	
	
	@RequestMapping(value = "/production_assets/deploy/batch_update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> batchUpdate(@RequestBody List<ProductionAsset> prodAsset) {
		
		aslService.batchUpdate(prodAsset);
		return ResponseEntity.ok(prodAsset);
	}
	
	@RequestMapping(value = "/production_assets/transfer/batch_update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> batchTransferUpdate(@RequestBody List<TransferAsset> prodAsset) {
		
		aslService.batchTransferUpdate(prodAsset);
		return ResponseEntity.ok(prodAsset);
	}


}
