//package com.policy.controller;
//
//import java.util.List;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.web.bind.annotation.*;
//
//import org.springframework.web.client.RestTemplate;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestHeader;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.policy.model.UserPolicy;
//import com.policy.service.UserPolicyService;
//
//@RestController
//@RequestMapping("/api/user-policies")
//public class UserPolicyController {
//
//	
//	private RestTemplate restTemplate;
//	private UserPolicyService userPolicyService;
//
//	public UserPolicyController(UserPolicyService userPolicyService,
//            RestTemplate restTemplate) {
//this.userPolicyService = userPolicyService;
//this.restTemplate = restTemplate;
//}
//
//@GetMapping("/my-policies")
//public List<UserPolicy> getMyPolicies(
//@RequestHeader("Authorization") String authHeader) {
//
//HttpHeaders headers = new HttpHeaders();
//headers.set("Authorization", authHeader);
//
//HttpEntity<String> entity = new HttpEntity<>(headers);
//
//String username = restTemplate.exchange(
//"http://localhost:8089/api/auth/validate",
//HttpMethod.GET,
//entity,
//String.class
//).getBody();
//
//return userPolicyService.getUserPolicies(username);
//}
//    
//    @GetMapping("/user/{username}")
//    public List<UserPolicy> getUserPolicies(@PathVariable String username) {
//        return userPolicyService.getUserPolicies(username);
//    }
//    
//    @PostMapping("/purchase")
//    public UserPolicy purchasePolicy(
//            @RequestParam String username,
//            @RequestParam Long policyId) {
//
//        return userPolicyService.purchasePolicy(username, policyId);
//    }
//    
//
//}
