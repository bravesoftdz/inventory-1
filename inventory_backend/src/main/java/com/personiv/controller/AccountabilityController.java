package com.personiv.controller;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.personiv.model.Accountability;
import com.personiv.model.AssetAccountable;
import com.personiv.model.EmptyResponse;
import com.personiv.model.ErrorResponse;
import com.personiv.service.AccountabilityService;
import com.personiv.utils.DocumentBuilder;
import com.personiv.utils.JwtTokenUtil;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
@RestController
@CrossOrigin(origins = "*")
public class AccountabilityController {

	private final Log logger = LogFactory.getLog(this.getClass());
	
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    
	@Autowired
	private AccountabilityService accService;
	
	
	@RequestMapping(value = {"/accountability","/accountability/"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAccountabilities() {
		return ResponseEntity.ok(accService.getAccountabilities());
	}
	
	@RequestMapping(value = "/accountability", method = RequestMethod.PUT,consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)	
	public ResponseEntity<?> addAccountableAsset(@RequestBody AssetAccountable acc){
		
		accService.addAccountableAsset(acc);
		return ResponseEntity.ok(acc);
	}
	@RequestMapping(value = "/accountability", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)	
	public ResponseEntity<?> addAccountability(@RequestBody Accountability acc){
		
		accService.addAccountability(acc);
		return ResponseEntity.ok(acc);
	}
	@RequestMapping(value = "/accountability/all_assets", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)	
	public ResponseEntity<?>getAllAssets(){
		
		
		return ResponseEntity.ok(accService.getAllAssets());
	}
	
	@RequestMapping(value = "/accountability/add_accountable", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)	
	public ResponseEntity<?>addAccountable(@RequestBody AssetAccountable acc){
		
		accService.addAccountable(acc);
		return ResponseEntity.ok(acc);
	}
	
	@RequestMapping(value = "/accountability/update_remarks", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)	
	public ResponseEntity<?> updateAccountable(@RequestBody Accountability acc){
		
		accService.updateRemarks(acc);
		return ResponseEntity.ok(acc);
	}
	
	@RequestMapping(value = "/accountability/delete_accountable", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)	
	public ResponseEntity<?> deleteAccountable(@RequestBody AssetAccountable acc){
		
		accService.deleteAccountable(acc);
		return ResponseEntity.ok(acc);
	}
	@RequestMapping(path = "/accountability/uploadAttachment", method = RequestMethod.POST)
	public ResponseEntity<?> uploadAttachment(@RequestParam(value="file", required = false) MultipartFile file,@RequestParam(value="data") String data) throws IOException{
		
		try {
			
			//System.out.println(data);
			if(data == null) return ResponseEntity.status(422).body(new ErrorResponse("No data sent"));
			ObjectMapper mapper = new ObjectMapper();
			
			Accountability acc = mapper.readValue(data, Accountability.class);
			
			accService.uploadAttachment(acc,file);
			return ResponseEntity.ok(acc);
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(500).body(new ErrorResponse("Something went wrong"));
		}
	}
   @RequestMapping(path = "/accountability/downloadAttachment/{filename:.+}", method = RequestMethod.GET)
    public ResponseEntity<?> dowloadAttachment(@PathVariable("filename") String filename,HttpServletRequest req,HttpServletResponse response) {
    		
    		 
    		
    	Cookie[] cookies = req.getCookies();
    	
    	
    	if(cookies != null) {
    		for(Cookie cookie: req.getCookies()) {
				if(cookie.getName().equals("inventory-token")) {
					  try {
			            	String token = cookie.getValue();
			            	
			                jwtTokenUtil.getUsernameFromToken(token);
			                
//			                String p ="/home/opt/tomcat/inventory_uploads/"+filename;
			                
			                
			        		Resource file = accService.downloadAttachment(filename);//fileService.loadFile(p);
			        		
	
			        		return ResponseEntity.ok()
			        				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
			        				.body(file);
			                
			            } catch (IllegalArgumentException e) {
			                logger.error("an error occured during getting username from token", e);
			            } catch (ExpiredJwtException e) {
			                logger.warn("the token is expired and not valid anymore", e);
			            }catch(MalformedJwtException e) {
			            	logger.error("Malformed token", e);
			            }catch(Exception e) {
			            	logger.error("Exception",e);
			            }
				}
			}
    	}
    	return ResponseEntity.status(403).body(null);
    }
	@RequestMapping(path = "/accountability/generatePdf", method = RequestMethod.POST)
	public ResponseEntity<?> genereatePdf(@RequestBody Accountability acc){
		
		try {
			DocumentBuilder docBuilder = new DocumentBuilder();
			docBuilder.testGenerate(acc);
			return ResponseEntity.ok(acc);
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(500).body(new ErrorResponse("Something went wrong"));
		}
	}
	
	
//	@RequestMapping(value = "/accountability/download", method = RequestMethod.GET)	
//	public void download(HttpServletResponse response,@RequestBody Accountability acc){
//		
//		DocumentBuilder docBuilder = null;
//        try {
//        	docBuilder = new DocumentBuilder();
//        	byte bytes[] = docBuilder.createTemplate(acc);
//    		response.getOutputStream().write(bytes);
//    		response.setContentType("application/octet-stream");
//    		response.setHeader("Content-Disposition", "attachment; filename=rest-with-spring.docx");
//    		
//    		response.getOutputStream().close();
//    		
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//            
//        } 
//		
//		
//		//accService.updateAccountable(acc);
//		//return ResponseEntity.ok(null);
//	}
	
	@RequestMapping(value = "/accountability/download/{id}", method = RequestMethod.GET)	
	public HttpEntity<byte[]> download(@PathVariable("id") Long id){
		
		
		AssetAccountable acc = null;
		
		try {
			acc = accService.getAssetAccountableById(id);
			System.out.println(acc);
		}catch(EmptyResultDataAccessException e) {
			return null;
		}
		
		
		DocumentBuilder docBuilder = null;
   
    	docBuilder = new DocumentBuilder();
    	byte bytes[] = docBuilder.createTemplate(acc);


        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_PDF);
        header.set(HttpHeaders.CONTENT_DISPOSITION,
                       "attachment; filename=accountability.docx");
        header.setContentLength(bytes.length);

        return new HttpEntity<byte[]>(bytes, header);
    		
     
		
		
		//accService.updateAccountable(acc);
		//return ResponseEntity.ok(null);
	}
	
	@RequestMapping(value = "/accountability/asset_accountable/{id}", method = RequestMethod.GET)	
	public ResponseEntity<?> getAssetAccountableById(@PathVariable("id") Long id){
		
		
		AssetAccountable acc = null;
		
		try {
			acc = accService.getAssetAccountableById(id);
			return ResponseEntity.ok(acc);
		}catch(EmptyResultDataAccessException e) {
			return ResponseEntity.ok(new EmptyResponse());
		}
	
	}
//	@RequestMapping(value = "/accountability/download", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)	
//	public ResponseEntity<?> download(@RequestBody Accountability acc){
//		
//		DocumentBuilder docBuilder = null;
//       
//    	docBuilder = new DocumentBuilder();
//    	byte bytes[] = docBuilder.createTemplate(acc);
//    	
//    	PrintableDocument document = new PrintableDocument();
//    	document.setContent(bytes);
//    	document.setContentType("application/octet-stream");
//    	
//    	return ResponseEntity.ok(document);
//	}
	
}
