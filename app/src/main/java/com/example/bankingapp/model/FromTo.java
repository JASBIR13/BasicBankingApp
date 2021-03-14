package com.example.bankingapp.model;

public class FromTo {
    private String from;
    private String to;
    private String fromPhoneNo;
    private String fromEmail;
    private String fromBalance;
    private String toPhoneNo;
    private String toEmail;

    public String getFromPhoneNo() {
        return fromPhoneNo;
    }

    public void setFromPhoneNo(String fromPhoneNo) {
        this.fromPhoneNo = fromPhoneNo;
    }

    public String getFromEmail() {
        return fromEmail;
    }

    public void setFromEmail(String fromEmail) {
        this.fromEmail = fromEmail;
    }

    public String getFromBalance() {
        return fromBalance;
    }

    public void setFromBalance(String fromBalance) {
        this.fromBalance = fromBalance;
    }

    public String getToPhoneNo() {
        return toPhoneNo;
    }

    public void setToPhoneNo(String toPhoneNo) {
        this.toPhoneNo = toPhoneNo;
    }

    public String getToEmail() {
        return toEmail;
    }

    public void setToEmail(String toEmail) {
        this.toEmail = toEmail;
    }

    public String getToBalance() {
        return toBalance;
    }

    public void setToBalance(String toBalance) {
        this.toBalance = toBalance;
    }

    private String toBalance;

    public FromTo() {
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
