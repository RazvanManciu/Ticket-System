package com.sda.TicketSystem.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ticket")
public class Ticket {

    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "enter_date")
    private LocalDateTime enterDate;

    @Column(name = "exit_date")
    private LocalDateTime exitDate;

    @Column(name = "code")
    private String code;

    @Column(name = "pay_date")
    private LocalDateTime payDate;

    @Column(name = "payed_amount")
    private int payedAmount;

    public Ticket(){

    }

    public Ticket(LocalDateTime enterDate, LocalDateTime exitDate, String code, LocalDateTime payDate, int payedAmount) {
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

    public LocalDateTime getEnterDate() {
        return enterDate;
    }

    public void setEnterDate(LocalDateTime enterDate) {
        this.enterDate = enterDate;
    }

    public LocalDateTime getExitDate() {
        return exitDate;
    }

    public void setExitDate(LocalDateTime exitDate) {
        this.exitDate = exitDate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getPayDate() {
        return payDate;
    }

    public void setPayDate(LocalDateTime payDate) {
        this.payDate = payDate;
    }

    public int getPayedAmount() {
        return payedAmount;
    }

    public void setPayedAmount(int payedAmount) {
        this.payedAmount = payedAmount;
    }
}
