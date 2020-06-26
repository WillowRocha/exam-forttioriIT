package com.forttiori.exam.service.itinerary;

import com.forttiori.exam.http.ItineraryRequest;
import com.forttiori.exam.model.itinerary.Coord;
import com.forttiori.exam.model.itinerary.Itinerary;
import com.forttiori.exam.repo.busline.BuslineRepo;
import com.forttiori.exam.repo.itinerary.CoordRepo;
import com.forttiori.exam.repo.itinerary.ItineraryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Service
public class ItineraryService {

    private ItineraryRepo repo;
    private CoordRepo coordRepo;
    private BuslineRepo buslineRepo;

    @Autowired
    public ItineraryService(ItineraryRepo repo, CoordRepo coordRepo, BuslineRepo buslineRepo) {
        this.repo = repo;
        this.coordRepo = coordRepo;
        this.buslineRepo = buslineRepo;
    }

    public Iterable<Itinerary> getItineraries() {
        Iterable<Itinerary> itineraries = repo.findAll();
        itineraries.forEach(it -> {
            it.setCoords(coordRepo.findAllByLineId(it.getLineId()));
        });
        return itineraries;
    }

    public Itinerary getItineraryById(Integer id) {
        Itinerary itinerary = repo.findById(id).orElse(null);
        if (itinerary != null) {
            itinerary.setCoords(coordRepo.findAllByLineId(itinerary.getLineId()));
            return itinerary;
        }
        return null;
    }

    @Transactional
    public Itinerary upsertItinerary(ItineraryRequest req) throws IllegalArgumentException {
        buslineRepo.findById(req.getLineId()).orElseThrow(() -> new IllegalArgumentException("Linha de ônibus não cadastrada."));
        ArrayList<Coord> coords = new ArrayList<>();
        req.getCoords().forEach(c -> coords.add(new Coord(req.getLineId(), c.getLatitude(), c.getLongitude())));
        Itinerary itinerary = new Itinerary(req.getLineId(), req.getLineCode(), req.getLineName(), coords);
        coordRepo.saveAll(coords);
        return repo.save(itinerary);
    }

    @Transactional
    public void deleteItinerary(Integer lineId) {
        repo.deleteByLineId(lineId);
        coordRepo.deleteByLineId(lineId);
    }
}
