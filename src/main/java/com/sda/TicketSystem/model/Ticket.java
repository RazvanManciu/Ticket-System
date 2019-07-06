package com.sda.TicketSystem.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ticket")
public class Ticket {

    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "enter_date")
    private Date enterDate;

    @Column(name = "exit_date")
    private Date exitDate;

    @Column(name = "code")
    private String code;

    @Column(name = "pay_date")
    private Date payDate;

    @Column(name = "payed_amount")
    private int payedAmount;

    public Ticket(){

    }

    public Ticket(Date enterDate, Date exitDate, String code, Date payDate, int payedAmount) {
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

    public Date getEnterDate() {
        return enterDate;
    }

    public void setEnterDate(Date enterDate) {
        this.enterDate = enterDate;
    }

    public Date getExitDate() {
        return exitDate;
    }

    public void setExitDate(Date exitDate) {
        this.exitDate = exitDate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public int getPayedAmount() {
        return payedAmount;
    }

    public void setPayedAmount(int payedAmount) {
        this.payedAmount = payedAmount;
    }
}
