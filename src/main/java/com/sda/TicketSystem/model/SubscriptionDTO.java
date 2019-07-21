package com.sda.TicketSystem.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class SubscriptionDTO {

    private Long id;
    private String startDate;
    private String endDate;
    private String code;

    public SubscriptionDTO() {
    }

    public SubscriptionDTO(String startDate, String endDate, String code) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.code = code;
    }

    public LocalDate getStartDate() {
        LocalDate startDateLD = LocalDate.parse(startDate, DateTimeFormatter.ISO_LOCAL_DATE);
        return startDateLD;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        LocalDate endDateLD = LocalDate.parse(endDate, DateTimeFormatter.ISO_LOCAL_DATE);
        return endDateLD;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean validateDates() {
        return Objects.nonNull(startDate) && !startDate.isEmpty() &&
                Objects.nonNull(endDate) && !endDate.isEmpty();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
