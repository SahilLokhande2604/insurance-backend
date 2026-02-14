package com.policy.repository;

import com.policy.model.UserPolicy;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserPolicyRepository extends JpaRepository<UserPolicy, Long> {

    List<UserPolicy> findByUsername(String username);
}
