package com.forttiori.exam.controller.integration;

import com.forttiori.exam.service.integration.IntegrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/integration")
public class IntegrationTestController {

    @Autowired
    private IntegrationService service;

    @PostMapping("/buslines")
    ResponseEntity integrateBuslines() {
        service.integrateBusLines();
        return ResponseEntity.ok("");
    }

    @PostMapping("/itineraries")
    ResponseEntity integrateItineraries() {
        service.integrateItineraries();
        return ResponseEntity.ok("");
    }

    @PostMapping("/itineraries/{lineId}")
    ResponseEntity integrateItinerary(@PathVariable("lineId") Integer lineId) {
        service.integrateItinerary(lineId);
        return ResponseEntity.ok("");
    }
}
