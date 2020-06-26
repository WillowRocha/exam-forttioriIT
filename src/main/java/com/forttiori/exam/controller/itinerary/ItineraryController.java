package com.forttiori.exam.controller.itinerary;

import com.forttiori.exam.http.ItineraryRequest;
import com.forttiori.exam.model.itinerary.Itinerary;
import com.forttiori.exam.service.itinerary.ItineraryService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

    @ApiOperation("Retorna o itinerário de uma linha de ônibus")
    @GetMapping(value = "/{id}", produces = "application/json")
    Itinerary findById(@PathVariable @NotNull Integer id) {
        return service.getItineraryById(id);
    }

    @ApiOperation("Retorna todos itinerários de linhas de ônibus")
    @GetMapping(produces = "application/json")
    Iterable<Itinerary> findAll() {
        return service.getItineraries();
    }

    @ApiOperation("Cadastra ou atualiza o itinerário para uma linha de ônibus pré-existente")
    @ApiResponses(value = {
            @ApiResponse(code = 422, message = "Linha de ônibus não cadastrada")
    })
    @PostMapping(produces = "application/json", consumes = "application/json")
    Itinerary upsertItinerary(@Valid @RequestBody ItineraryRequest request) {
        return service.upsertItinerary(request);
    }

    @ApiOperation("Deleta o itinerário de uma linha de ônibus")
    @DeleteMapping("/{id}")
    void deleteItinerary(@PathVariable @NotNull Integer id) {
        service.deleteItinerary(id);
    }
}
