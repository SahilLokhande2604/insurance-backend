package com.example.payment_service.controller;


import com.example.payment_service.dto.*;
import com.example.payment_service.service.PaymentService;
import com.razorpay.RazorpayException;
//import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/create")
    public CreatePaymentResponse createPayment(
            @RequestBody CreatePaymentRequest request
    ) throws RazorpayException {

        return paymentService.createPayment(request);
    }

    @PostMapping("/verify")
    public String verifyPayment(
            @RequestBody VerifyPaymentRequest request
    ) {
        return paymentService.verifyPayment(request);
    }
    
    
    @GetMapping("/my-payments")
    public List<PaymentResponse> getMyPayments(@RequestHeader("X-USERNAME") String username) {
        return paymentService.getPaymentsByUsername(username);
    }
    
 // =========================
    // GET ALL PAYMENTS (ADMIN)
    // =========================
    @GetMapping
    public List<PaymentResponse> getAllPayments() {
        return paymentService.getAllPayments();
    }
}

 
