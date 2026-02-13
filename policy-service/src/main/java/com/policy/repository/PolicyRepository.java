package com.policy.repository;


import com.policy.model.Policy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PolicyRepository extends JpaRepository<Policy, Long> {

    List<Policy> findByUserId(Long userId);

    Optional<Policy> findTopByUserIdAndStatusOrderByCreatedAtDesc(
            Long userId,
            String status
    );
}
