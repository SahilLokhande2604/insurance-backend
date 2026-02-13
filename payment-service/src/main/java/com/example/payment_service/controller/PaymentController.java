package com.example.payment_service.controller;


import com.example.payment_service.dto.*;
import com.example.payment_service.service.PaymentService;
import com.razorpay.RazorpayException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
 
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private PaymentService paymentService;

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
}

 
