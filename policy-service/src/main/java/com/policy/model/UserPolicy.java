package com.policy.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_policies")
public class UserPolicy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username; 

    @ManyToOne
    @JoinColumn(name = "policy_id")
    private Policy policy;

    private LocalDateTime purchaseDate;
    private LocalDateTime expiryDate;
    private String status;

    public UserPolicy() {}

    public UserPolicy(String username,
                      Policy policy,
                      LocalDateTime purchaseDate,
                      LocalDateTime expiryDate,
                      String status) {
        this.username = username;
        this.policy = policy;
        this.purchaseDate = purchaseDate;
        this.expiryDate = expiryDate;
        this.status = status;
    }

    // Getters & Setters

    public Long getId() { return id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public Policy getPolicy() { return policy; }
    public void setPolicy(Policy policy) { this.policy = policy; }

    public LocalDateTime getPurchaseDate() { return purchaseDate; }
    public void setPurchaseDate(LocalDateTime purchaseDate) { this.purchaseDate = purchaseDate; }

    public LocalDateTime getExpiryDate() { return expiryDate; }
    public void setExpiryDate(LocalDateTime expiryDate) { this.expiryDate = expiryDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}

