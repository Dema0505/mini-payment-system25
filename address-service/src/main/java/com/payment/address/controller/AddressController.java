package com.payment.address.controller;

import com.payment.address.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/address")
@CrossOrigin(origins = "*")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping("/account/{phone}")
    public ResponseEntity<String> getAccountByPhone(@PathVariable String phone) {
        try {
            String accountNumber = addressService.getAccountByPhone(phone);
            return ResponseEntity.ok(accountNumber);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/participant/{phone}")
    public ResponseEntity<String> getParticipantByPhone(@PathVariable String phone) {
        try {
            String participantName = addressService.getParticipantByPhone(phone);
            return ResponseEntity.ok(participantName);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
