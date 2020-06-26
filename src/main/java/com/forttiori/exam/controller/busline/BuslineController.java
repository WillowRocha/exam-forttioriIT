package com.forttiori.exam.controller.busline;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/busline")
public class BuslineController {

    @GetMapping
    ResponseEntity teste() {
        return ResponseEntity.ok("Linhas");
    }
}
