package com.policy.service;

import com.policy.model.Policy;
import com.policy.model.UserPolicy;
import com.policy.repository.PolicyRepository;
import com.policy.repository.UserPolicyRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserPolicyService {

    private final UserPolicyRepository userPolicyRepository;
    private final PolicyRepository policyRepository;

    public UserPolicyService(UserPolicyRepository userPolicyRepository,
                             PolicyRepository policyRepository) {
        this.userPolicyRepository = userPolicyRepository;
        this.policyRepository = policyRepository;
    }

    public UserPolicy addPolicyToUser(String username,
                                      Long policyId,
                                      Long paymentId) {

        Policy policy = policyRepository.findById(policyId)
                .orElseThrow(() -> new RuntimeException("Policy not found"));

        UserPolicy userPolicy = new UserPolicy(
                username,
                policy,
                LocalDateTime.now(),
                LocalDateTime.now().plusYears(1),
                "ACTIVE"
        );

        return userPolicyRepository.save(userPolicy);
    }

    public List<UserPolicy> getUserPolicies(String username) {
        return userPolicyRepository.findByUsername(username);
    }

	
}

