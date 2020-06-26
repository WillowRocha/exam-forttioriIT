package com.forttiori.exam.service.integration;

import com.forttiori.exam.model.busline.Busline;
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
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class DataPoaService {

    private RestTemplate restTemplate;

    @Autowired
    public DataPoaService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ArrayList<Busline> getBuslines() {
        String url = "http://www.poatransporte.com.br/php/facades/process.php?a=nc&t=o";
        ArrayList<BuslineResponse> response = restTemplate.exchange(
                url, HttpMethod.GET, new HttpEntity<>(getHeaders()),
                new ParameterizedTypeReference<ArrayList<BuslineResponse>>() {
                }).getBody();
        return convertToModal(response);
    }

    public Itinerary getItinerary(Integer id) {
        String url = "http://www.poatransporte.com.br/php/facades/process.php?a=il&p={id}";
        HashMap<String, Integer> uriParams = new HashMap<>();
        uriParams.put("id", id);
        String responseBody;
        try {
            responseBody = restTemplate.exchange(
                    url, HttpMethod.GET, new HttpEntity<>(getHeaders()),
                    String.class, uriParams).getBody();
            return convertToItinerary(new JSONObject(responseBody));
        } catch (HttpClientErrorException e) {
            return null;
        }
    }

    private HttpHeaders getHeaders() { //TODO: Ver se é necessário
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        return headers;
    }

    private ArrayList<Busline> convertToModal(ArrayList<BuslineResponse> list) {
        ArrayList<Busline> lines = new ArrayList<>();
        list.forEach(it -> lines.add(new Busline(it.getId(), it.getCode(), it.getName())));
        return lines;
    }

    private Itinerary convertToItinerary(JSONObject obj) {
        Itinerary itinerary = new Itinerary();
        itinerary.setLineId(obj.getInt("idlinha"));
        itinerary.setLineCode(obj.getString("codigo"));
        itinerary.setLineName(obj.getString("nome"));
        itinerary.setCoords(convertCoords(obj));
        return itinerary;
    }

    private ArrayList<Coord> convertCoords(JSONObject obj) {
        ArrayList<Coord> coords = new ArrayList<>();
        int current = 0;
        JSONObject next = getNullableCoord(obj, current);
        while (next != null) {
            coords.add(new Coord(obj.getInt("idlinha"), next.getDouble("lat"), next.getDouble("lng")));
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
