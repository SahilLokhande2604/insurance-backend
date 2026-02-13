//package com.policy.service;
//
//import com.policy.dto.*;
//import com.policy.model.Policy;
//import com.policy.exception.ResourceNotFoundException;
//import com.policy.repository.PolicyRepository;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//public class PolicyService {
//
//    private final PolicyRepository policyRepository;
//
//    public PolicyService(PolicyRepository policyRepository) {
//        this.policyRepository = policyRepository;
//    }
//
//    // Create Policy (Before Payment)
//    public PolicyResponse createPolicy(PolicyRequest request) {
//
//        Policy policy = Policy.builder()
//                .policyNumber("POL" + System.currentTimeMillis())
//                .policyType(request.getPolicyType())
//                .coverageAmount(request.getCoverageAmount())
//                .premiumAmount(request.getPremiumAmount())
//                .userId(request.getUserId())
//                .status("PENDING_PAYMENT")
//                .createdAt(LocalDateTime.now())
//                .updatedAt(LocalDateTime.now())
//                .build();
//
//        policyRepository.save(policy);
//
//        return mapToResponse(policy);
//    }
//
//    // Activate Policy (Called by Payment Service)
//    public String activatePolicy(PolicyActivationRequest request) {
//
//        if (!"SUCCESS".equals(request.getPaymentStatus())) {
//            throw new RuntimeException("Payment not successful");
//        }
//
//        Policy policy = policyRepository
//                .findTopByUserIdAndStatusOrderByCreatedAtDesc(
//                        request.getUserId(),
//                        "PENDING_PAYMENT")
//                .orElseThrow(() -> new ResourceNotFoundException("Pending policy not found"));
//
//        policy.setStatus("ACTIVE");
//        policy.setPaymentId(request.getPaymentId());
//        policy.setUpdatedAt(LocalDateTime.now());
//
//        policyRepository.save(policy);
//
//        return "Policy activated successfully";
//    }
//
//    public List<PolicyResponse> getPoliciesByUser(Long userId) {
//        return policyRepository.findByUserId(userId)
//                .stream()
//                .map(this::mapToResponse)
//                .collect(Collectors.toList());
//    }
//
//    public PolicyResponse getPolicyById(Long id) {
//        Policy policy = policyRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Policy not found"));
//
//        return mapToResponse(policy);
//    }
//
//    private PolicyResponse mapToResponse(Policy policy) {
//        return PolicyResponse.builder()
//                .id(policy.getId())
//                .policyNumber(policy.getPolicyNumber())
//                .policyType(policy.getPolicyType())
//                .coverageAmount(policy.getCoverageAmount())
//                .premiumAmount(policy.getPremiumAmount())
//                .status(policy.getStatus())
//                .userId(policy.getUserId())
//                .build();
//    }
//}

package com.policy.service;

import com.policy.dto.*;
import com.policy.model.Policy;
import com.policy.exception.ResourceNotFoundException;
import com.policy.repository.PolicyRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PolicyService {

    private final PolicyRepository policyRepository;

    public PolicyService(PolicyRepository policyRepository) {
        this.policyRepository = policyRepository;
    }

    // Create Policy (Before Payment)
    public PolicyResponse createPolicy(PolicyRequest request) {

        Policy policy = new Policy();

        policy.setPolicyNumber("POL" + System.currentTimeMillis());
        policy.setPolicyType(request.getPolicyType());
        policy.setCoverageAmount(request.getCoverageAmount());
        policy.setPremiumAmount(request.getPremiumAmount());
        policy.setUserId(request.getUserId());
        policy.setStatus("PENDING_PAYMENT");
        policy.setCreatedAt(LocalDateTime.now());
        policy.setUpdatedAt(LocalDateTime.now());

        policyRepository.save(policy);

        return mapToResponse(policy);
    }

    // Activate Policy (Called by Payment Service)
    public String activatePolicy(PolicyActivationRequest request) {

        if (!"SUCCESS".equals(request.getPaymentStatus())) {
            throw new RuntimeException("Payment not successful");
        }

        Policy policy = policyRepository
                .findTopByUserIdAndStatusOrderByCreatedAtDesc(
                        request.getUserId(),
                        "PENDING_PAYMENT")
                .orElseThrow(() -> new ResourceNotFoundException("Pending policy not found"));

        policy.setStatus("ACTIVE");
        policy.setPaymentId(request.getPaymentId());
        policy.setUpdatedAt(LocalDateTime.now());

        policyRepository.save(policy);

        return "Policy activated successfully";
    }

    public List<PolicyResponse> getPoliciesByUser(Long userId) {
        return policyRepository.findByUserId(userId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public PolicyResponse getPolicyById(Long id) {

        Policy policy = policyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Policy not found"));

        return mapToResponse(policy);
    }

    private PolicyResponse mapToResponse(Policy policy) {

        PolicyResponse response = new PolicyResponse();

        response.setId(policy.getId());
        response.setPolicyNumber(policy.getPolicyNumber());
        response.setPolicyType(policy.getPolicyType());
        response.setCoverageAmount(policy.getCoverageAmount());
        response.setPremiumAmount(policy.getPremiumAmount());
        response.setStatus(policy.getStatus());
        response.setUserId(policy.getUserId());

        return response;
    }
}

