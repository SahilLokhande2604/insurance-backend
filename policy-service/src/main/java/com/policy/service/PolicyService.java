package com.policy.service;

import com.policy.model.Policy;
import com.policy.repository.PolicyRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PolicyService {

    private final PolicyRepository policyRepository;

    public PolicyService(PolicyRepository policyRepository) {
        this.policyRepository = policyRepository;
    }

    public Policy createPolicy(Policy policy) {
        policy.setCreatedAt(LocalDateTime.now());
        policy.setUpdatedAt(LocalDateTime.now());
        return policyRepository.save(policy);
    }

    public List<Policy> getAllPolicies() {
        return policyRepository.findAll();
    }

    public Policy getPolicyById(Long id) {
        return policyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Policy not found"));
    }
    
    public Policy updatePolicy(Long id, Policy updatedPolicy) {
        Policy policy = policyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Policy not found"));

        policy.setPolicyName(updatedPolicy.getPolicyName());
        policy.setPolicyType(updatedPolicy.getPolicyType());
        policy.setCoverageAmount(updatedPolicy.getCoverageAmount());
        policy.setPremiumAmount(updatedPolicy.getPremiumAmount());
        policy.setDescription(updatedPolicy.getDescription());
        policy.setUpdatedAt(LocalDateTime.now());

        return policyRepository.save(policy);
    }

    public void deletePolicy(Long id) {
        Policy policy = policyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Policy not found"));
        policyRepository.delete(policy);
    }

}
