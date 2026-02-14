package com.insurance.repository;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.insurance.model.Claim;

@Repository
public interface ClaimRepository extends JpaRepository<Claim, Long>{
//	List<Claim> findByUserId(Long userId);
	List<Claim> findByCustomerId(Long customerId);
	
	
	

}
