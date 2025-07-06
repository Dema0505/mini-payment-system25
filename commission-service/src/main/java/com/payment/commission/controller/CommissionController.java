package com.payment.commission.controller;

import com.payment.commission.service.CommissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/commission")
@CrossOrigin(origins = "*")
public class CommissionController {

    @Autowired
    private CommissionService commissionService;

    @GetMapping("/calculate")
    public ResponseEntity<BigDecimal> calculateCommission(
            @RequestParam String fromAccount,
            @RequestParam String toAccount,
            @RequestParam BigDecimal amount) {
        try {
            BigDecimal commission = commissionService.calculateCommission(fromAccount, toAccount, amount);
            return ResponseEntity.ok(commission);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(BigDecimal.ZERO);
        }
    }

    @GetMapping("/rules")
    public ResponseEntity<?> getAllRules() {
        return ResponseEntity.ok(commissionService.getAllRules());
    }
}
