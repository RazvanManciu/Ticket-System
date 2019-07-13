package com.sda.TicketSystem.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "ticket")
public class Ticket {

    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "enter_date")
    private LocalDate enterDate;

    @Column(name = "exit_date")
    private LocalDate exitDate;

    @Column(name = "code")
    private String code;

    @Column(name = "pay_date")
    private LocalDate payDate;

    @Column(name = "payed_amount")
    private int payedAmount;

    public Ticket(){

    }

    public Ticket(LocalDate enterDate, LocalDate exitDate, String code, LocalDate payDate, int payedAmount) {
        this.enterDate = enterDate;
        this.exitDate = exitDate;
        this.code = code;
        this.payDate = payDate;
        this.payedAmount = payedAmount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getEnterDate() {
        return enterDate;
    }

    public void setEnterDate(LocalDate enterDate) {
        this.enterDate = enterDate;
    }

    public LocalDate getExitDate() {
        return exitDate;
    }

    public void setExitDate(LocalDate exitDate) {
        this.exitDate = exitDate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDate getPayDate() {
        return payDate;
    }

    public void setPayDate(LocalDate payDate) {
        this.payDate = payDate;
    }

    public int getPayedAmount() {
        return payedAmount;
    }

    public void setPayedAmount(int payedAmount) {
        this.payedAmount = payedAmount;
    }
}
