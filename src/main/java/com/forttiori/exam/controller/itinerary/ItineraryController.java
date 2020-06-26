package com.forttiori.exam.controller.itinerary;

import com.forttiori.exam.http.ItineraryRequest;
import com.forttiori.exam.model.itinerary.Itinerary;
import com.forttiori.exam.service.itinerary.ItineraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/itineraries")
public class ItineraryController {

    private ItineraryService service;

    @Autowired
    public ItineraryController(ItineraryService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    Itinerary findById(@PathVariable @NotNull Integer id) {
        return service.getItineraryById(id);
    }

    @GetMapping
    Iterable<Itinerary> findAll() {
        return service.getItineraries();
    }

    @PostMapping
    Itinerary upsertItinerary(@Valid @RequestBody ItineraryRequest request) {
        return service.upsertItinerary(request);
    }

    @DeleteMapping("/{id}")
    void deleteItinerary(@PathVariable @NotNull Integer id) {
        service.deleteItinerary(id);
    }
}
