package com.sda.TicketSystem.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
/*        //Create a DateTimeFormatter with your required format:
        DateTimeFormat dateTimeFormat =
                new DateTimeFormatter(DateTimeFormatter.BASIC_ISO_DATE);

        //Next parse the date from the @RequestParam, specifying the TO type as a TemporalQuery:
        LocalDateTime date = dateTimeFormat.parse(startDate, LocalDateTime::from);*/

//        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.BASIC_ISO_DATE;
//        LocalDateTime startDateLDT = LocalDateTime.parse("2016-03-04T10:15:30", DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        LocalDate localDate = LocalDate.now();
        LocalDate startDateLD = LocalDate.parse(startDate, DateTimeFormatter.ISO_LOCAL_DATE);
        System.out.println(startDateLD.getDayOfMonth());
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        System.out.println(endDate);
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
