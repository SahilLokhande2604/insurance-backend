package com.insurance.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.insurance.model.Claim;

@Repository
public interface ClaimRepository extends JpaRepository<Claim, Long>{


}
