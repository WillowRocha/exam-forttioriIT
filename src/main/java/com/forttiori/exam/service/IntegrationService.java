package com.forttiori.exam.service;

import com.forttiori.exam.model.busline.json.BuslineResponse;
import com.forttiori.exam.model.itinerary.Coord;
import com.forttiori.exam.model.itinerary.Itinerary;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class IntegrationService {

    private RestTemplate restTemplate;

    @Autowired
    public IntegrationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ArrayList<BuslineResponse> getBuslines() {
        String url = "http://www.poatransporte.com.br/php/facades/process.php?a=nc&t=o";
        return restTemplate.exchange(
                url, HttpMethod.GET, new HttpEntity<>(getHeaders()),
                new ParameterizedTypeReference<ArrayList<BuslineResponse>>() {
                }).getBody();
    }

    public Itinerary getItinerary(Integer id) {
        String url = "http://www.poatransporte.com.br/php/facades/process.php?a=il&p={id}";
        HashMap<String, Integer> uriParams = new HashMap<>();
        uriParams.put("id", id);
        String obj = restTemplate.exchange(
                url, HttpMethod.GET, new HttpEntity<>(getHeaders()),
                String.class, uriParams).getBody();
        return convertToItinerary(new JSONObject(obj));
    }

    private HttpHeaders getHeaders() { //TODO: Ver se é necessário
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        return headers;
    }

    private Itinerary convertToItinerary(JSONObject obj) {
        Itinerary itinerary = new Itinerary();
        itinerary.setIdlinha(obj.getInt("idlinha"));
        itinerary.setCodigo(obj.getString("codigo"));
        itinerary.setNome(obj.getString("nome"));
        itinerary.setCoords(convertCoords(obj));
        return itinerary;
    }

    private ArrayList<Coord> convertCoords(JSONObject obj) {
        ArrayList<Coord> coords = new ArrayList<>();
        int current = 0;
        JSONObject next = getNullableCoord(obj, current);
        while (next != null) {
            coords.add(new Coord(next.getDouble("lat"), next.getDouble("lng")));
            current++;
            next = getNullableCoord(obj, current);
        }
        return coords;
    }

    private JSONObject getNullableCoord(JSONObject obj, Integer key) {
        try {
            return obj.getJSONObject(key.toString());
        } catch (JSONException ignore) {
            return null;
        }
    }

}
