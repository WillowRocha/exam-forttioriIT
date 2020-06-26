package com.forttiori.exam.controller.busline;

import com.forttiori.exam.http.BuslineRequest;
import com.forttiori.exam.http.ItineraryFilterRequest;
import com.forttiori.exam.model.busline.Busline;
import com.forttiori.exam.service.busline.BuslineService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/buslines")
public class BuslineController {

    private BuslineService service;

    @Autowired
    public BuslineController(BuslineService service) {
        this.service = service;
    }

    @ApiOperation("Retorna uma linha de ônibus")
    @GetMapping(value = "/{id}", produces = "application/json")
    Busline findById(@PathVariable Integer id) {
        return service.getBuslineById(id);
    }


    @ApiOperation("Retorna as linhas de ônibus contidas em um raio em Km")
    @GetMapping(value = "/filter", produces = "application/json")
    Iterable<Busline> findBuslinesByRadius(@Valid ItineraryFilterRequest request) {
        return service.getItinerariesByRadius(request);
    }

    @ApiOperation("Retorna linhas de ônibus com base no filtro, ou todas caso não definido")
    @GetMapping(produces = "application/json")
    Iterable<Busline> findAll(@RequestParam(required = false) String search) {
        return service.getBuslines(search);
    }

    @ApiOperation("Cadastra ou atualiza uma linha de ônibus")
    @PostMapping(produces = "application/json", consumes = "application/json")
    Busline upsertBusline(@Valid @RequestBody BuslineRequest request) {
        return service.upsertBusline(request);
    }

    @ApiOperation("Deleta uma linha de ônibus")
    @DeleteMapping("/{id}")
    void deleteBusline(@PathVariable @NotNull Integer id) {
        service.deleteBusline(id);
    }
}
