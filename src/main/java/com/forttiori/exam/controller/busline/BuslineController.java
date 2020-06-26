package com.forttiori.exam.controller.busline;

import com.forttiori.exam.http.BuslineRequest;
import com.forttiori.exam.model.busline.Busline;
import com.forttiori.exam.service.busline.BuslineService;
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

    @GetMapping("/{id}")
    Busline findById(@PathVariable Integer id) {
        return service.getBuslineById(id);
    }

    @GetMapping
    Iterable<Busline> findAll(@RequestParam(required = false) String search) {
        return service.getBuslines(search);
    }

    @PostMapping
    Busline upsertBusline(@Valid @RequestBody BuslineRequest request) {
        return service.upsertBusline(request);
    }

    @DeleteMapping("/{id}")
    void deleteBusline(@PathVariable @NotNull Integer id) {
        service.deleteBusline(id);
    }
}
