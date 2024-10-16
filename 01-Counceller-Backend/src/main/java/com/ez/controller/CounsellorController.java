package com.ez.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ez.dao.LoginRequest;
import com.ez.entity.Counsellor;
import com.ez.service.CounsellorService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200") 
public class CounsellorController {
	
	@Autowired
	private CounsellorService counsellorService;

	@PostMapping("/login")
	public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequest loginRequest){
		
		boolean isAuthenticated=counsellorService.authenticate(loginRequest.getEmail(), loginRequest.getPassword());
		Map<String, String> response = new HashMap<String, String>();	
		
		
			if(isAuthenticated) {
				response.put("message", "LoginSuccessfull");
		        return ResponseEntity.ok(response); // Return JSON object
			}	else {
				response.put("error", "Invalid email or password");
		        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response); // Return JSON object with error
			}
	}
	
	@PostMapping("/register")
	public ResponseEntity<Map<String, String>> registerCounsellor(@RequestBody Counsellor counsellor){
		boolean isSaved = counsellorService.saveCounselor(counsellor);
		System.out.println(counsellor);
		Map<String,String> response=new HashMap<String, String>();
		
		if(isSaved) {
			response.put("message", "Registered successfully");
			return ResponseEntity.ok(response);
		}else {
			response.put("error", "Unable to register");
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
		}
		
	}
	
}
 