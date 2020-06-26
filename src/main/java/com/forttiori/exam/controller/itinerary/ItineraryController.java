package com.forttiori.exam.controller.itinerary;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/itinerary")
public class ItineraryController {

    @GetMapping
    ResponseEntity teste() {

        return ResponseEntity.ok("Itinerário");
    }
}
