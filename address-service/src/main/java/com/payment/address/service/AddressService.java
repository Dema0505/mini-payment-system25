package com.payment.address.service;

import com.payment.address.model.PhoneAccountMapping;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
public class AddressService {

    private final Map<String, PhoneAccountMapping> phoneToAccountMap = new HashMap<>();

    @PostConstruct
    public void initializeData() {
    
        phoneToAccountMap.put("+1234567890", new PhoneAccountMapping("+1234567890", "ACC001", "John Doe", "BANK001"));
        phoneToAccountMap.put("+0987654321", new PhoneAccountMapping("+0987654321", "ACC002", "Jane Smith", "BANK002"));
        phoneToAccountMap.put("+1122334455", new PhoneAccountMapping("+1122334455", "ACC003", "Bob Johnson", "BANK001"));
        phoneToAccountMap.put("1234567890", new PhoneAccountMapping("1234567890", "ACC001", "John Doe", "BANK001"));
        phoneToAccountMap.put("0987654321", new PhoneAccountMapping("0987654321", "ACC002", "Jane Smith", "BANK002"));
    }

    public String getAccountByPhone(String phone) {
        PhoneAccountMapping mapping = phoneToAccountMap.get(phone);
        if (mapping == null) {
            throw new RuntimeException("Phone number not found: " + phone);
        }
        return mapping.getAccountNumber();
    }

    public String getParticipantByPhone(String phone) {
        PhoneAccountMapping mapping = phoneToAccountMap.get(phone);
        if (mapping == null) {
            throw new RuntimeException("Phone number not found: " + phone);
        }
        return mapping.getParticipantName();
    }

    public PhoneAccountMapping getFullMappingByPhone(String phone) {
        PhoneAccountMapping mapping = phoneToAccountMap.get(phone);
        if (mapping == null) {
            throw new RuntimeException("Phone number not found: " + phone);
        }
        return mapping;
    }
}
