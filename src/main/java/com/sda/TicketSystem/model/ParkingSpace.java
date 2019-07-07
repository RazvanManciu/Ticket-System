package com.sda.TicketSystem.model;

import javax.persistence.*;

@Entity
@Table(name = "parking_space")
public class ParkingSpace {

    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "is_free")
    private Boolean isFree;

    @ManyToOne
    @JoinColumn(name = "sector_id")
    private Sector sector;

    @Column(name = "number")
    private int number;

    public ParkingSpace(){
    }

    public ParkingSpace(Boolean isFree, Sector sector, int number) {
        this.isFree = isFree;
        this.sector = sector;
        this.number = number;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getFree() {
        return isFree;
    }

    public void setFree(Boolean free) {
        isFree = free;
    }

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
