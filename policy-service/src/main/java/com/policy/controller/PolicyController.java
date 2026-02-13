package com.policy.controller;

import com.policy.dto.*;
import com.policy.service.PolicyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/policies")
public class PolicyController {

    private final PolicyService policyService;

    public PolicyController(PolicyService policyService) {
        this.policyService = policyService;
    }

    @PostMapping
    public ResponseEntity<PolicyResponse> createPolicy(
            @RequestBody PolicyRequest request) {

        return ResponseEntity.ok(policyService.createPolicy(request));
    }

    @PostMapping("/activate")
    public ResponseEntity<String> activatePolicy(
            @RequestBody PolicyActivationRequest request) {

        return ResponseEntity.ok(policyService.activatePolicy(request));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PolicyResponse>> getPoliciesByUser(
            @PathVariable Long userId) {

        return ResponseEntity.ok(policyService.getPoliciesByUser(userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PolicyResponse> getPolicyById(
            @PathVariable Long id) {

        return ResponseEntity.ok(policyService.getPolicyById(id));
    }
}
