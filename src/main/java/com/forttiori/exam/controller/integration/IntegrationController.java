package com.forttiori.exam.controller.integration;

import com.forttiori.exam.service.integration.IntegrationService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/integration")
public class IntegrationController {

    @Autowired
    private IntegrationService service;

    @ApiOperation("Integra as linhas de ônibus da API Datapoa com o banco de dados")
    @PostMapping("/buslines")
    void integrateBuslines() {
        service.integrateBusLines();
    }

    @ApiOperation("Integra os itinerários da API Datapoa com o banco de dados, para as linhas de ônibus cadastradas")
    @PostMapping("/itineraries")
    void integrateItineraries() {
        service.integrateItineraries();
    }

    @ApiOperation("Integra o itinerário de uma linha de ônibus com o banco de dados, para uma linha cadastrada")
    @ApiResponses(value = {
            @ApiResponse(code = 422, message = "Linha de ônibus não cadastrada")
    })
    @PostMapping("/itineraries/{lineId}")
    void integrateItinerary(@PathVariable Integer lineId) {
        service.integrateItinerary(lineId);
    }
}
