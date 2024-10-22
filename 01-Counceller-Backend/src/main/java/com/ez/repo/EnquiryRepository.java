package com.ez.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ez.entity.Counsellor;
import com.ez.entity.Enquiry;

import java.util.List;
import java.util.Optional;

public interface EnquiryRepository extends JpaRepository<Enquiry, Integer> {

	// Count total enquiries for a user
	int countByCounsellor(Counsellor counsellor);

    // Count enquiries by status and user email
	int countByEnqStatusAndCounsellor(String enqStatus, Counsellor counsellor);

	List<Enquiry> findByCounsellor(Counsellor c);

	Optional<Enquiry>  findByEId(Long id);
}
