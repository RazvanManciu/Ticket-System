package com.sda.TicketSystem.service;

import com.sda.TicketSystem.model.ParkingSpace;
import com.sda.TicketSystem.model.ParkingSpaceDTO;
import com.sda.TicketSystem.repository.ParkingSpaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ParkingSpaceService {

    private ParkingSpaceRepository parkingSpaceRepository;
    private SectorService sectorService;

    @Autowired
    public ParkingSpaceService(ParkingSpaceRepository parkingSpaceRepository, SectorService sectorService) {
        this.parkingSpaceRepository = parkingSpaceRepository;
        this.sectorService = sectorService;
    }

    public List<ParkingSpaceDTO> getAll() {
        List<ParkingSpaceDTO> parkingSpaceDTOS = new ArrayList<>();
        for (ParkingSpace parkingSpace : parkingSpaceRepository.findAll()) {
            ParkingSpaceDTO parkingSpaceDTO = new ParkingSpaceDTO();
            parkingSpaceDTO.setId(parkingSpace.getId());
            parkingSpaceDTO.setSectorName(sectorService.getById(parkingSpace.getSector().getId()).getName());
            parkingSpaceDTO.setNumber(parkingSpace.getNumber());
            parkingSpaceDTO.setIsFree(parkingSpace.getFree());
            parkingSpaceDTOS.add(parkingSpaceDTO);
        }
        return parkingSpaceDTOS;
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
