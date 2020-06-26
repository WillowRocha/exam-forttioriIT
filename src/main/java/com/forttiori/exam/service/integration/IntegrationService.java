package com.forttiori.exam.service.integration;

import com.forttiori.exam.model.busline.Busline;
import com.forttiori.exam.model.itinerary.Coord;
import com.forttiori.exam.model.itinerary.Itinerary;
import com.forttiori.exam.repo.busline.BuslineRepo;
import com.forttiori.exam.repo.itinerary.CoordRepo;
import com.forttiori.exam.repo.itinerary.ItineraryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class IntegrationService {

    private DataPoaService service;
    private BuslineRepo buslineRepo;
    private ItineraryRepo itineraryRepo;
    private CoordRepo coordRepo;

    @Autowired
    public IntegrationService(DataPoaService service, BuslineRepo buslineRepo, ItineraryRepo itineraryRepo, CoordRepo coordRepo) {
        this.service = service;
        this.buslineRepo = buslineRepo;
        this.itineraryRepo = itineraryRepo;
        this.coordRepo = coordRepo;
    }


    public void integrateBusLines() {
        ArrayList<Busline> lines = service.getBuslines();
        buslineRepo.saveAll(lines);
    }

    public void integrateItineraries() {
        Iterable<Busline> lines = buslineRepo.findAll();
        ArrayList<Itinerary> itineraries = new ArrayList<>();
        ArrayList<Coord> coords = new ArrayList<>();
        lines.forEach(line -> {
            Itinerary itinerary = service.getItinerary(line.getId());
            if (itinerary != null) {
                itineraries.add(itinerary);
                coords.addAll(itinerary.getCoords());
            }
        });
        itineraryRepo.saveAll(itineraries);
        coordRepo.saveAll(coords);
    }

    public void integrateItinerary(Integer lineId) throws IllegalArgumentException {
        Busline line = buslineRepo.findById(lineId).orElse(null);
        if (line == null) throw new IllegalArgumentException("Linha de ônibus não cadastrada.");

        Itinerary itinerary = service.getItinerary(line.getId());

        if (itinerary == null) throw new IllegalArgumentException("Itinerário não encontrado.");

        ArrayList<Coord> coords = new ArrayList<>(itinerary.getCoords());
        itineraryRepo.save(itinerary);
        coordRepo.saveAll(coords);
    }


}
