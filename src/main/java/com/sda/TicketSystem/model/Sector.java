package com.sda.TicketSystem.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "sector")
public class Sector {

    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "sector")
    @Column(name = "parking_spaces")
    private List<ParkingSpace> parkingSpaces;

    public Sector(){
    }

    public Sector(String name, List<ParkingSpace> parkingSpaces) {
        this.name = name;
        this.parkingSpaces = parkingSpaces;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ParkingSpace> getParkingSpaces() {
        return parkingSpaces;
    }

    public void setParkingSpaces(List<ParkingSpace> parkingSpaces) {
        this.parkingSpaces = parkingSpaces;
    }
}
