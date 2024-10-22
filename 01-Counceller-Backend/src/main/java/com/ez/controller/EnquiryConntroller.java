package com.ez.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ez.entity.Counsellor;
import com.ez.entity.Enquiry;
import com.ez.service.CounsellorService;
import com.ez.service.EnquiryService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200",allowCredentials = "true") 
public class EnquiryConntroller {
	
	@Autowired
	private EnquiryService enquiryService;
	
	@Autowired
	private CounsellorService counsellorService;
	
	@PostMapping("/add")
    public Enquiry addEnquiry(@RequestBody Enquiry enquiry, HttpServletRequest request) {
        // Save the enquiry received from Angular
		System.out.println("At/add"+ enquiry);
		HttpSession session = request.getSession(false);
		String userEmail = (String) session.getAttribute("userEmail");
		System.out.println("At/addif fetched useremail"+ userEmail);
		Counsellor counsellor = counsellorService.findByEmail(userEmail);
		enquiry.setCounsellor(counsellor);
         return enquiryService.saveEnquiry(enquiry);
         
         
    }

	@GetMapping("/getall")
	public List<Enquiry> fetchAllInquiry(HttpServletRequest request){
		HttpSession session = request.getSession(false);
		String userEmail =(String) session.getAttribute("userEmail");
		System.out.println("in getall url"+userEmail);
		List<Enquiry> enquiries = enquiryService.fetchAllInquiry(userEmail);
		System.out.println(enquiries);
//		List<Enquiry> enquiries = enquiryService.fetchAllInquiry(email);
		return enquiries;


	}

	@PutMapping("/enquiries/{id}")
	public ResponseEntity<Map<String, String>> updateEnquiry(@PathVariable Long id, @RequestBody Enquiry enquiry) {
		boolean isUpdated = enquiryService.updateEnquiry(id, enquiry); // Implement this in your service layer

		Map<String, String> response = new HashMap<>();

		if (isUpdated) {
			response.put("message", "Enquiry updated successfully");
			return ResponseEntity.ok(response);
		} else {
			response.put("error", "Unable to update enquiry");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
		}
	}

}
