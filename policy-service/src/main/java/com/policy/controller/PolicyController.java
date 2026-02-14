package com.policy.controller;

import com.policy.dto.PurchaseConfirmRequest;
import com.policy.model.Policy;
import com.policy.model.UserPolicy;
import com.policy.service.PolicyService;
import com.policy.service.UserPolicyService;

import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/policies")
public class PolicyController {

    private final PolicyService policyService;
    private final UserPolicyService userPolicyService;

    public PolicyController(PolicyService policyService,
                            UserPolicyService userPolicyService) {
        this.policyService = policyService;
        this.userPolicyService = userPolicyService;
    }

    // ================= ADMIN / PUBLIC =================

    @PostMapping
    public Policy createPolicy(@RequestBody Policy policy) {
        return policyService.createPolicy(policy);
    }

    @GetMapping
    public List<Policy> getAllPolicies() {
        return policyService.getAllPolicies();
    }

    @GetMapping("/{id}")
    public Policy getPolicy(@PathVariable Long id) {
        return policyService.getPolicyById(id);
    }

    @PutMapping("/{id}")
    public Policy updatePolicy(@PathVariable Long id,
                               @RequestBody Policy policy) {
        return policyService.updatePolicy(id, policy);
    }

    @DeleteMapping("/{id}")
    public void deletePolicy(@PathVariable Long id) {
        policyService.deletePolicy(id);
    }

    // ================= USER =================

    @GetMapping("/my")
    public List<UserPolicy> getMyPolicies(
            @RequestHeader("X-USERNAME") String username) {
        return userPolicyService.getUserPolicies(username);
    }
//    @GetMapping("/my")
//    public List<UserPolicy> getMyPolicies(Authentication authentication) {
//        String username = authentication.getName(); // 🔥 from JWT
//        return userPolicyService.getUserPolicies(username);
//    }


    /**
     * PAYMENT SERVICE will call this after successful payment
     */
    @PostMapping("/purchase/confirm")
    public UserPolicy confirmPolicyPurchase(
            @RequestBody PurchaseConfirmRequest request) {

        return userPolicyService.addPolicyToUser(
                request.getUsername(),
                request.getPolicyId(),
                request.getPaymentId()
        );
    }
}
