package com.ez.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ez.dao.DashboardResponse;
import com.ez.dao.LoginRequest;
import com.ez.entity.Counsellor;
import com.ez.service.CounsellorService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200",allowCredentials = "true") 
public class CounsellorController {
	
	@Autowired
	private CounsellorService counsellorService;

	@PostMapping("/login")
	public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequest loginRequest, HttpServletRequest request){
		
		
		boolean isAuthenticated=counsellorService.authenticate(loginRequest.getEmail(), loginRequest.getPassword());
		
		Map<String, String> response = new HashMap<String, String>();	
		
		
			if(isAuthenticated) {
				response.put("message", "LoginSuccessfull");

		HttpSession session = request.getSession(true);
		
				session.setAttribute("userEmail", loginRequest.getEmail());
				
		        return ResponseEntity.ok(response); // Return JSON object
			}	else {
				response.put("error", "Invalid email or password");
		        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response); // Return JSON object with error
			}
	}
	
	@PostMapping("/register")
	public ResponseEntity<Map<String, String>> registerCounsellor(@RequestBody Counsellor counsellor){
		System.out.println("In /register"+counsellor);
		boolean isSaved = counsellorService.saveCounselor(counsellor);
		
		Map<String,String> response=new HashMap<String, String>();
		
		if(isSaved) {
			response.put("message", "Registered successfully");
			return ResponseEntity.ok(response);
		}else {
			response.put("error", "Unable to register");
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
		}
		
	}
	
	@GetMapping("/dashboard")
	public ResponseEntity<?> getDashboard(HttpServletRequest request) {
		HttpSession session = request.getSession(false);

	    String userEmail = (String) session.getAttribute("userEmail");


	    	
	    if (userEmail == null) {
	        // If no session found, return unauthorized
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not logged in");
	    }
	    
	    // Proceed with your logic to return dashboard data
	    DashboardResponse dashboard = counsellorService.getDashboardData(userEmail);

	    return ResponseEntity.ok(dashboard);
	}

	
}
 