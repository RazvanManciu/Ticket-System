package com.sda.TicketSystem.service;

import com.sda.TicketSystem.model.ParkingSpace;
import com.sda.TicketSystem.model.ParkingSpaceDTO;
import com.sda.TicketSystem.repository.ParkingSpaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public int getNumberOfSpaces() {
        List<ParkingSpace> parkingSpaceList = parkingSpaceRepository.findAll();
        if (parkingSpaceList != null)
            return parkingSpaceList.size();
        return 0;
    }

    public int getNumberOfFreeSpaces() {
        List<ParkingSpace> parkingSpaceList = parkingSpaceRepository.findAll();
        int numberOfFreeSpaces = 0;
        if (parkingSpaceList != null)
            for (ParkingSpace parkingSpace : parkingSpaceList) {
                if (parkingSpace.getFree())
                    numberOfFreeSpaces++;
            }
        return numberOfFreeSpaces;
    }

/*    public ParkingSpace create(ParkingSpace parkingSpace) {
        return parkingSpaceRepository.save(parkingSpace);
    }

    public ParkingSpace getbyId(Long id) {
        Optional<ParkingSpace> parkingSpace = parkingSpaceRepository.findById(id);
        return parkingSpace.orElse(null);
    }*/

    public void deleteParkingSpace(Long id) {
        parkingSpaceRepository.deleteById(id);
    }
}
