package com.example.payment_service.service;

import com.example.payment_service.controller.PaymentResponse;
import com.example.payment_service.dto.*;
import com.example.payment_service.entity.Payment;
import com.example.payment_service.repository.PaymentRepository;
import com.razorpay.*;
import org.apache.commons.codec.digest.HmacUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.payment_service.kafka.PaymentEventProducer;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//@Service
//public class PaymentService {
//
//    private RazorpayClient razorpayClient;
//    private PaymentRepository paymentRepository;
//    private RestTemplate restTemplate;
//
//    @Value("${razorpay.secret}")
//    private String secret;
//
//    public PaymentService(RazorpayClient razorpayClient,
//            PaymentRepository paymentRepository) {
//this.razorpayClient = razorpayClient;
//this.paymentRepository = paymentRepository;
//this.restTemplate = new RestTemplate(); // ✅ directly initialize
//}
//
//    // ================= CREATE PAYMENT =================
//
//    public CreatePaymentResponse createPayment(CreatePaymentRequest request)
//            throws RazorpayException {
//
//        JSONObject options = new JSONObject();
//        options.put("amount", (int) (request.getAmount() * 100));
//        options.put("currency", "INR");
//
//        Order razorpayOrder = razorpayClient.orders.create(options);
//
//        Payment payment = new Payment();
//        payment.setOrderId(request.getOrderId());
//        payment.setUserId(request.getUserId());
//        payment.setAmount(request.getAmount());
//        payment.setRazorpayOrderId(razorpayOrder.get("id").toString());
//        payment.setStatus("PENDING");
//        payment.setCreatedAt(LocalDateTime.now());
//        payment.setUpdatedAt(LocalDateTime.now());
//
//        paymentRepository.save(payment);
//
//        return new CreatePaymentResponse(
//                razorpayOrder.get("id").toString(),
//                request.getAmount(),
//                "INR"
//        );
//    }
//
//    // ================= VERIFY PAYMENT =================
//
//    public String verifyPayment(VerifyPaymentRequest request) {
//
//        try {
//
//            System.out.println("Razorpay Order ID: " + request.getRazorpayOrderId());
//            System.out.println("Razorpay Payment ID: " + request.getRazorpayPaymentId());
//            System.out.println("Razorpay Signature: " + request.getSignature());
//
//            String payload = request.getRazorpayOrderId() + "|" +
//                             request.getRazorpayPaymentId();
//
//            String generatedSignature = new HmacUtils("HmacSHA256", secret)
//                    .hmacHex(payload);
//
//            System.out.println("Generated Signature: " + generatedSignature);
//
//            if (!generatedSignature.equals(request.getSignature())) {
//                throw new RuntimeException("Payment verification failed - Signature mismatch");
//            }
//
//            // Fetch payment from DB
//            Payment payment = paymentRepository
//                    .findByRazorpayOrderId(request.getRazorpayOrderId())
//                    .orElseThrow(() -> new RuntimeException("Payment not found"));
//
//            payment.setRazorpayPaymentId(request.getRazorpayPaymentId());
//            payment.setStatus("SUCCESS");
//            payment.setUpdatedAt(LocalDateTime.now());
//
//            paymentRepository.save(payment);
//
//            // ================= CALL POLICY SERVICE =================
//
//            try {
//
//                Map<String, Object> policyRequest = new HashMap<>();
//                policyRequest.put("userId", payment.getUserId());
//                policyRequest.put("amount", payment.getAmount());
//                policyRequest.put("paymentStatus", payment.getStatus());
//                policyRequest.put("paymentId", payment.getPaymentId());
//
//                System.out.println("Sending to Policy Service: " + policyRequest);
//
//                restTemplate.postForObject(
//                        "http://localhost:8083/api/policies/generate",
//                        policyRequest,
//                        String.class
//                );
//
//            } catch (Exception ex) {
//                System.out.println("Policy Service Error: " + ex.getMessage());
//                // DO NOT throw exception
//            }
//
//            return "Payment verified successfully";
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new RuntimeException("Verification Error: " + e.getMessage());
//        }
//    }
//}

import com.example.payment_service.dto.PolicyPurchaseConfirmRequest;

@Service
public class PaymentService {


    private RazorpayClient razorpayClient;
    private PaymentRepository paymentRepository;
    private RestTemplate restTemplate;
    private final PaymentEventProducer paymentEventProducer;

    @Value("${razorpay.secret}")
    private String secret;

    public PaymentService(RazorpayClient razorpayClient,
            PaymentRepository paymentRepository,
            PaymentEventProducer paymentEventProducer) {
        this.razorpayClient = razorpayClient;
        this.paymentRepository = paymentRepository;
        this.paymentEventProducer = paymentEventProducer;
        this.restTemplate = new RestTemplate();
    }


	// ================= CREATE PAYMENT =================

    public CreatePaymentResponse createPayment(CreatePaymentRequest request)
            throws RazorpayException {
    	
    	System.out.println("Username "+request.getUsername());
    	System.out.println("PolicyId "+request.getPolicyId());
    	System.out.println("Amount "+request.getAmount());

        JSONObject options = new JSONObject();
        options.put("amount", (int) (request.getAmount() * 100));
        options.put("currency", "INR");

        Order razorpayOrder = razorpayClient.orders.create(options);

        Payment payment = new Payment();
        payment.setUsername(request.getUsername());
        payment.setPolicyId(request.getPolicyId());
        payment.setAmount(request.getAmount());
        payment.setRazorpayOrderId(razorpayOrder.get("id"));
        payment.setStatus("PENDING");
        payment.setCreatedAt(LocalDateTime.now());
        payment.setUpdatedAt(LocalDateTime.now());

        paymentRepository.save(payment);

        return new CreatePaymentResponse(
                razorpayOrder.get("id"),
                request.getAmount(),
                "INR"
        );
    }

    // ================= VERIFY PAYMENT =================
    
    public String verifyPayment(VerifyPaymentRequest request) {

        try {
        	
        	 System.out.println("Razorpay Order ID: " + request.getRazorpayOrderId());
           System.out.println("Razorpay Payment ID: " + request.getRazorpayPaymentId());
           System.out.println("Razorpay Signature: " + request.getSignature());
            String payload = request.getRazorpayOrderId() + "|" +
                             request.getRazorpayPaymentId();

            String generatedSignature = new HmacUtils("HmacSHA256", secret)
                    .hmacHex(payload);

            if (!generatedSignature.equals(request.getSignature())) {
                throw new RuntimeException("Signature mismatch");
            }

            Payment payment = paymentRepository
                    .findByRazorpayOrderId(request.getRazorpayOrderId())
                    .orElseThrow(() -> new RuntimeException("Payment not found"));

            payment.setRazorpayPaymentId(request.getRazorpayPaymentId());
            payment.setStatus("SUCCESS");
            payment.setUpdatedAt(LocalDateTime.now());

            // 🔥 THIS IS THE KEY LINE
            payment = paymentRepository.saveAndFlush(payment);

            System.out.println("Payment ID after save: " + payment.getPaymentId());
            
            System.out.println("Sending to Policy Service → " +
                    "username=" + payment.getUsername() +
                    ", policyId=" + payment.getPolicyId() +
                    ", paymentId=" + payment.getPaymentId());


            PolicyPurchaseConfirmRequest confirmRequest =
                    new PolicyPurchaseConfirmRequest(
                            payment.getUsername(),
                            payment.getPolicyId(),
                            payment.getPaymentId()
                    );

            restTemplate.postForObject(
                    "http://localhost:8089/api/policies/purchase/confirm",
                    confirmRequest,
                    Void.class
            );
            
         // 🔥 Send success event to Kafka
            paymentEventProducer.sendPaymentSuccessEvent(
                    payment.getUsername(),
                    payment.getRazorpayPaymentId(),
                    payment.getAmount()
            );


            return "Payment verified and policy activated";

        } catch (Exception e) {
            e.printStackTrace();
            paymentEventProducer.sendPaymentFailureEvent(null, "Payment failed: " + e.getMessage(), secret);
            throw new RuntimeException("Verification failed: " + e.getMessage());
        }
    }
    
 // ================= GET PAYMENTS BY USERNAME =================
    public List<PaymentResponse> getPaymentsByUsername(String username) {
        List<Payment> payments = paymentRepository.findAllByUsername(username);

        return payments.stream().map(p -> {
            PaymentResponse response = new PaymentResponse(); // default constructor
            response.setPaymentId(p.getPaymentId());
            response.setAmount(p.getAmount());
            response.setStatus(p.getStatus());
            response.setCreatedAt(p.getCreatedAt());
            response.setOrderId(p.getOrderId());
            response.setOrderId(p.getRazorpayOrderId());
            response.setUsername(p.getUsername());
            response.setPolicyId(p.getPolicyId());
            return response;
        }).collect(Collectors.toList());
    }

 // ================= GET ALL PAYMENTS =================
    public List<PaymentResponse> getAllPayments() {
        List<Payment> payments = paymentRepository.findAll();

        return payments.stream().map(p -> {
            PaymentResponse response = new PaymentResponse();
            response.setPaymentId(p.getPaymentId());
            response.setAmount(p.getAmount());
            response.setStatus(p.getStatus());
            response.setCreatedAt(p.getCreatedAt());
            response.setOrderId(p.getRazorpayOrderId());
            response.setUsername(p.getUsername());
            response.setPolicyId(p.getPolicyId());
            return response;
        }).collect(Collectors.toList());
    }

    
    



}
