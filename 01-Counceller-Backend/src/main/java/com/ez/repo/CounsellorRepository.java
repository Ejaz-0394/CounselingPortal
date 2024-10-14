package com.ez.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ez.entity.Counsellor;

public interface CounsellorRepository extends JpaRepository<Counsellor, Integer> {
		
	Optional<Counsellor> findByCounsellorEmail(String email);
}
