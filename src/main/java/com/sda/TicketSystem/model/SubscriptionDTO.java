package com.sda.TicketSystem.model;

import java.time.LocalDateTime;

public class SubscriptionDTO {

    private String startDate;
    private String endDate;
    private String code;

    public SubscriptionDTO(){
    }

    public SubscriptionDTO(String startDate, String endDate, String code) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.code = code;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
