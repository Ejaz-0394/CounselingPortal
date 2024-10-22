package com.ez.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ez.dao.DashboardResponse;
import com.ez.entity.Counsellor;
import com.ez.repo.CounsellorRepository;
import com.ez.repo.EnquiryRepository;

@Service
public class CounsellorService {

	
	@Autowired
	private CounsellorRepository counsellorRepository;
	
	@Autowired
    private EnquiryRepository enquiryRepository;
	
	// checkigng credentials while login
	public boolean authenticate(String email, String password) {
	
		Counsellor counsellor = counsellorRepository.findByCounsellorEmail(email);
	
			if(counsellor.getCounsellorPwd().equals(password)) {
				return true;
			}
			return false;
	}
	
	// saving counsellor into db
	public boolean saveCounselor(Counsellor counselor) {
		Counsellor save = counsellorRepository.save(counselor);
		if(save.getCId() != null) {
			return true;
		}
		return false;
    }
	
	public DashboardResponse getDashboardData(String userEmail) {
		
		// Fetch the Counsellor entity based on userEmail
        Counsellor counsellor = counsellorRepository.findByCounsellorEmail(userEmail);
        if (counsellor == null) {
            throw new RuntimeException("Counsellor not found for email: " + userEmail);
        }

        // Fetch counts based on enquiry status and counsellor
        int totalEnquiries = enquiryRepository.countByCounsellor(counsellor);
        int openEnquiries = enquiryRepository.countByEnqStatusAndCounsellor("OPEN", counsellor);
        int lostEnquiries = enquiryRepository.countByEnqStatusAndCounsellor("LOST", counsellor);
        int enrolledEnquiries = enquiryRepository.countByEnqStatusAndCounsellor("ENROLLED", counsellor);

        // Create and return the dashboard response
        return new DashboardResponse(totalEnquiries, openEnquiries, lostEnquiries, enrolledEnquiries);
    }
	
	
	public Counsellor findByEmail(String email) {
		Counsellor byCounsellorEmail = counsellorRepository.findByCounsellorEmail(email);
		System.out.println("at service mthod of find by email: "+byCounsellorEmail);
		return byCounsellorEmail;
	}
	
	
	
	
}
