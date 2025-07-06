package com.payment.commission.model;

import java.math.BigDecimal;

public class CommissionRule {
    private String ruleId;
    private String fromBankCode;
    private String toBankCode;
    private BigDecimal minAmount;
    private BigDecimal maxAmount;
    private BigDecimal fixedFee;
    private BigDecimal percentageFee;
    private String description;

    public CommissionRule() {}

    public CommissionRule(String ruleId, String fromBankCode, String toBankCode, 
                         BigDecimal minAmount, BigDecimal maxAmount, 
                         BigDecimal fixedFee, BigDecimal percentageFee, String description) {
        this.ruleId = ruleId;
        this.fromBankCode = fromBankCode;
        this.toBankCode = toBankCode;
        this.minAmount = minAmount;
        this.maxAmount = maxAmount;
        this.fixedFee = fixedFee;
        this.percentageFee = percentageFee;
        this.description = description;
    }

    public String getRuleId() { return ruleId; }
    public void setRuleId(String ruleId) { this.ruleId = ruleId; }

    public String getFromBankCode() { return fromBankCode; }
    public void setFromBankCode(String fromBankCode) { this.fromBankCode = fromBankCode; }

    public String getToBankCode() { return toBankCode; }
    public void setToBankCode(String toBankCode) { this.toBankCode = toBankCode; }

    public BigDecimal getMinAmount() { return minAmount; }
    public void setMinAmount(BigDecimal minAmount) { this.minAmount = minAmount; }

    public BigDecimal getMaxAmount() { return maxAmount; }
    public void setMaxAmount(BigDecimal maxAmount) { this.maxAmount = maxAmount; }

    public BigDecimal getFixedFee() { return fixedFee; }
    public void setFixedFee(BigDecimal fixedFee) { this.fixedFee = fixedFee; }

    public BigDecimal getPercentageFee() { return percentageFee; }
    public void setPercentageFee(BigDecimal percentageFee) { this.percentageFee = percentageFee; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
