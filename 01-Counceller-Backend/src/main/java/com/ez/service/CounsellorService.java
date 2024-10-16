package com.ez.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ez.entity.Counsellor;
import com.ez.repo.CounsellorRepository;

@Service
public class CounsellorService {

	
	@Autowired
	private CounsellorRepository counsellorRepository;
	
	
	// checkigng credentials while login
	public boolean authenticate(String email, String password) {
	
		Optional<Counsellor> counsellor = counsellorRepository.findByCounsellorEmail(email);
		System.out.println(counsellor.isPresent());
			if(counsellor.isPresent() && counsellor.get().getCounsellorPwd().equals(password)) {
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
	
	
}
