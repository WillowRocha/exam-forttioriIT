package com.forttiori.exam.service.busline;

import com.forttiori.exam.http.BuslineRequest;
import com.forttiori.exam.http.ItineraryFilterRequest;
import com.forttiori.exam.model.busline.Busline;
import com.forttiori.exam.repo.busline.BuslineRepo;
import com.forttiori.exam.repo.itinerary.CoordRepo;
import com.forttiori.exam.service.itinerary.ItineraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class BuslineService {

    private BuslineRepo repo;
    private ItineraryService itineraryService;
    private CoordRepo coordRepo;
    private final static Double EARTH_RADIUS = 6371d;

    @Autowired
    public BuslineService(BuslineRepo repo, ItineraryService itineraryService, CoordRepo coordRepo) {
        this.repo = repo;
        this.itineraryService = itineraryService;
        this.coordRepo = coordRepo;
    }

    public Busline getBuslineById(Integer id) {
        return repo.findById(id).orElse(null);
    }

    public Iterable<Busline> getItinerariesByRadius(ItineraryFilterRequest req) {
        return null;
    }

    public Iterable<Busline> getBuslines(String search) {
        if (search != null && search.length() > 0) {
            return repo.findAllByCodeContainingOrNameContainingAllIgnoreCase(search, search);
        }
        return repo.findAll();
    }

    public Busline upsertBusline(BuslineRequest req) {
        return repo.save(new Busline(req.getId(), req.getCode(), req.getName()));
    }

    @Transactional
    public void deleteBusline(Integer id) {
        itineraryService.deleteItinerary(id);
        repo.deleteById(id);
    }
}
