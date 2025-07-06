package com.payment.commission.service;

import com.payment.commission.model.CommissionRule;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommissionService {

    private final List<CommissionRule> commissionRules = new ArrayList<>();

    @PostConstruct
    public void initializeRules() {

        commissionRules.add(new CommissionRule("RULE001", "BANK001", "BANK001", 
            BigDecimal.ZERO, new BigDecimal("10000"), 
            new BigDecimal("1.00"), new BigDecimal("0.5"), "Same bank transfer"));


        commissionRules.add(new CommissionRule("RULE002", "BANK001", "BANK002", 
            BigDecimal.ZERO, new BigDecimal("10000"), 
            new BigDecimal("2.50"), new BigDecimal("1.0"), "Inter-bank transfer"));

        commissionRules.add(new CommissionRule("RULE003", "BANK002", "BANK001", 
            BigDecimal.ZERO, new BigDecimal("10000"), 
            new BigDecimal("2.50"), new BigDecimal("1.0"), "Inter-bank transfer"));

        commissionRules.add(new CommissionRule("RULE004", "*", "*", 
            new BigDecimal("10000"), new BigDecimal("100000"), 
            new BigDecimal("5.00"), new BigDecimal("0.3"), "High amount transfer"));

        commissionRules.add(new CommissionRule("RULE005", "*", "*", 
            BigDecimal.ZERO, new BigDecimal("999999999"), 
            new BigDecimal("2.00"), new BigDecimal("0.8"), "Default transfer"));
    }

    public BigDecimal calculateCommission(String fromAccount, String toAccount, BigDecimal amount) {

        String fromBankCode = extractBankCode(fromAccount);
        String toBankCode = extractBankCode(toAccount);

        CommissionRule applicableRule = findApplicableRule(fromBankCode, toBankCode, amount);

        if (applicableRule == null) {
            return amount.multiply(new BigDecimal("0.01")).setScale(2, RoundingMode.HALF_UP);
        }

        BigDecimal percentageCommission = amount.multiply(applicableRule.getPercentageFee().divide(new BigDecimal("100")));
        BigDecimal totalCommission = applicableRule.getFixedFee().add(percentageCommission);

        return totalCommission.setScale(2, RoundingMode.HALF_UP);
    }

    private String extractBankCode(String accountNumber) {
        if (accountNumber.startsWith("ACC001")) return "BANK001";
        if (accountNumber.startsWith("ACC002")) return "BANK002";
        if (accountNumber.startsWith("ACC003")) return "BANK001";
        return "UNKNOWN";
    }

    private CommissionRule findApplicableRule(String fromBankCode, String toBankCode, BigDecimal amount) {
        for (CommissionRule rule : commissionRules) {
            if (isRuleApplicable(rule, fromBankCode, toBankCode, amount)) {
                return rule;
            }
        }
        return null;
    }

    private boolean isRuleApplicable(CommissionRule rule, String fromBankCode, String toBankCode, BigDecimal amount) {
        boolean fromBankMatch = rule.getFromBankCode().equals("*") || rule.getFromBankCode().equals(fromBankCode);
        boolean toBankMatch = rule.getToBankCode().equals("*") || rule.getToBankCode().equals(toBankCode);

        boolean amountInRange = amount.compareTo(rule.getMinAmount()) >= 0 && 
                               amount.compareTo(rule.getMaxAmount()) <= 0;

        return fromBankMatch && toBankMatch && amountInRange;
    }

    public List<CommissionRule> getAllRules() {
        return new ArrayList<>(commissionRules);
    }
}
