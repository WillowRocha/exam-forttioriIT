package com.forttiori.exam.service.integration.datapoa;

import com.forttiori.exam.model.itinerary.Coord;
import com.forttiori.exam.model.itinerary.Itinerary;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DataPoaServiceTest {

    @Test
    void convertToItinerary() {
        ArrayList<Coord> coords = new ArrayList<>();
        coords.add(new Coord(4567, -30.14983557422600000, -51.14698813620700000));
        coords.add(new Coord(4567, -30.15018357422600000, -51.14617613620700000));
        coords.add(new Coord(4567, -30.15035157422600000, -51.14577013620700000));
        assertEquals(new Itinerary(4567, "123-1", "Teste", coords), DataPoaService.convertToItinerary(mockResponse()));
    }

    @Test
    void convertCoords() {
        ArrayList<Coord> coords = new ArrayList<>();
        coords.add(new Coord(4567, -30.14983557422600000, -51.14698813620700000));
        coords.add(new Coord(4567, -30.15018357422600000, -51.14617613620700000));
        coords.add(new Coord(4567, -30.15035157422600000, -51.14577013620700000));
        assertEquals(coords, DataPoaService.convertCoords(mockResponse()));
    }

    private static String mockResponse() {
        return "{\"idlinha\": \"4567\",\"nome\": \"Teste\",\"codigo\": \"123-1\"," +
                "\"0\": {\"lat\": \"-30.14983557422600000\",\"lng\": \"-51.14698813620700000\"}," +
                "\"1\": {\"lat\": \"-30.15018357422600000\",\"lng\": \"-51.14617613620700000\"}," +
                "\"2\": {\"lat\": \"-30.15035157422600000\",\"lng\": \"-51.14577013620700000\"}}";
    }
}