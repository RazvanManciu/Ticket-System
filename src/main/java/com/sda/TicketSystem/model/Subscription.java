package com.sda.TicketSystem.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "subscription")
public class Subscription {

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

    public Subscription(){

    }

    public Subscription(Date enterDate, Date exitDate, String code) {
        this.enterDate = enterDate;
        this.exitDate = exitDate;
        this.code = code;
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
}
