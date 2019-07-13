package com.sda.TicketSystem.service;

import com.sda.TicketSystem.model.ParkingSpace;
import com.sda.TicketSystem.repository.ParkingSpaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ParkingSpaceService {

    private ParkingSpaceRepository parkingSpaceRepository;

    @Autowired
    public ParkingSpaceService(ParkingSpaceRepository parkingSpaceRepository) {
        this.parkingSpaceRepository = parkingSpaceRepository;
    }

    public ParkingSpace create(ParkingSpace parkingSpace){
        return parkingSpaceRepository.save(parkingSpace);
    }

    public ParkingSpace getbyId(Long id){
        Optional<ParkingSpace> parkingSpace = parkingSpaceRepository.findById(id);
        return parkingSpace.orElse(null);
    }

    public void deleteParkingSpace(Long id){
        parkingSpaceRepository.deleteById(id);
    }
}
