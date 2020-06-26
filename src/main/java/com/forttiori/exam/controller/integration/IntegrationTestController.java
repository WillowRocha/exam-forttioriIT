package com.forttiori.exam.controller.integration;

import com.forttiori.exam.model.busline.json.BuslineResponse;
import com.forttiori.exam.model.itinerary.Itinerary;
import com.forttiori.exam.service.IntegrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/integration")
public class IntegrationTestController {

    @Autowired
    private IntegrationService service;

    @GetMapping("/buslines")
    ArrayList<BuslineResponse> getBuslines() {
        return service.getBuslines();
    }

    @GetMapping("/itinerary/{id}")
    Itinerary getItinerary(@PathVariable("id") Integer id) {
        return service.getItinerary(id);
    }

}
