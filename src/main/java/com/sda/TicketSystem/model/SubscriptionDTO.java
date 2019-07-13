package com.sda.TicketSystem.model;

import java.time.LocalDateTime;

public class SubscriptionDTO {

    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String code;

    public SubscriptionDTO(){
    }

    public SubscriptionDTO(LocalDateTime startDate, LocalDateTime endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
