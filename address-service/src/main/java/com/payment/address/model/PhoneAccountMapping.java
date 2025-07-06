package com.payment.address.model;

public class PhoneAccountMapping {
    private String phone;
    private String accountNumber;
    private String participantName;
    private String bankCode;

    public PhoneAccountMapping() {}

    public PhoneAccountMapping(String phone, String accountNumber, String participantName, String bankCode) {
        this.phone = phone;
        this.accountNumber = accountNumber;
        this.participantName = participantName;
        this.bankCode = bankCode;
    }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getAccountNumber() { return accountNumber; }
    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }

    public String getParticipantName() { return participantName; }
    public void setParticipantName(String participantName) { this.participantName = participantName; }

    public String getBankCode() { return bankCode; }
    public void setBankCode(String bankCode) { this.bankCode = bankCode; }
}
