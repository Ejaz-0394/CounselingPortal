package com.ez.service;

import com.ez.entity.Counsellor;
import com.ez.repo.CounsellorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ez.entity.Enquiry;
import com.ez.repo.EnquiryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EnquiryService {

	@Autowired
	private EnquiryRepository enquiryRepository;

	@Autowired
	private CounsellorRepository counsellorRepository;
	
	public Enquiry saveEnquiry(Enquiry enquiry) {
        // Save the enquiry to the database
        return enquiryRepository.save(enquiry);
    }

	public List<Enquiry> fetchAllInquiry(String userEmail){
		Counsellor counsellor = counsellorRepository.findByCounsellorEmail(userEmail);

		List<Enquiry> allByCId = enquiryRepository.findByCounsellor(counsellor);

		return allByCId;

	}

	public boolean updateEnquiry(Long id, Enquiry enquiry) {
		// Fetch the existing enquiry
		Optional<Enquiry> existingEnquiryOpt = enquiryRepository.findByEId(id);
		if (existingEnquiryOpt.isPresent()) {
			Enquiry existingEnquiry = existingEnquiryOpt.get();

			// Update fields as necessary
			existingEnquiry.setStudentName(enquiry.getStudentName());
			existingEnquiry.setStudentPhno(enquiry.getStudentPhno());
			existingEnquiry.setCourseName(enquiry.getCourseName());
			existingEnquiry.setClassMode(enquiry.getClassMode());
			existingEnquiry.setEnqStatus(enquiry.getEnqStatus());

			enquiryRepository.save(existingEnquiry); // Save the updated enquiry
			return true;
		}
		return false;
	}
}
