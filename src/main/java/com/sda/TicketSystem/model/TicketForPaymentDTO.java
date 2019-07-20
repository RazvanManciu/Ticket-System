package com.sda.TicketSystem.model;

public class TicketForPaymentDTO {

    private String tickeCodeForPayment;
    private String amountToPay;

    public TicketForPaymentDTO() {
    }

    public String getTickeCodeForPayment() {
        return tickeCodeForPayment;
    }

    public void setTickeCodeForPayment(String tickeCodeForPayment) {
        this.tickeCodeForPayment = tickeCodeForPayment;
    }

    public String getAmountToPay() {
        return amountToPay;
    }

    public void setAmountToPay(String amountToPay) {
        this.amountToPay = amountToPay;
    }
}
