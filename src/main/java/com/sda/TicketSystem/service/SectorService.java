package com.sda.TicketSystem.service;

import com.sda.TicketSystem.model.Sector;
import com.sda.TicketSystem.repository.SectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SectorService {

    private SectorRepository sectorRepository;

    @Autowired
    public SectorService(SectorRepository sectorRepository) {
        this.sectorRepository = sectorRepository;
    }

    public Sector create(Sector sector){
        return sectorRepository.save(sector);
    }

    public Sector getById(Long id){
        Optional<Sector> sector = sectorRepository.findById(id);
        return sector.orElse(null);
    }

    public void deleteSector(Long id){
        sectorRepository.deleteById(id);
    }
}
