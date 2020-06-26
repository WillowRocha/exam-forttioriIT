package com.forttiori.exam.service.integration.datapoa;

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
        ArrayList<BuslineResponse> responseBody;
        try {
            responseBody = restTemplate.exchange(
                    url, HttpMethod.GET, new HttpEntity<>(new HttpHeaders()),
                    new ParameterizedTypeReference<ArrayList<BuslineResponse>>() {
                    }).getBody();
            return convertToModal(responseBody);
        } catch (HttpClientErrorException e) {
            return new ArrayList<>();
        }
    }

    public Itinerary getItinerary(Integer id) {
        String url = "http://www.poatransporte.com.br/php/facades/process.php?a=il&p={id}";
        HashMap<String, Integer> uriParams = new HashMap<>();
        uriParams.put("id", id);
        String responseBody;
        try {
            responseBody = restTemplate.exchange(
                    url, HttpMethod.GET, new HttpEntity<>(new HttpHeaders()),
                    String.class, uriParams).getBody();
            return convertToItinerary(responseBody);
        } catch (HttpClientErrorException e) {
            return null;
        }
    }

    private ArrayList<Busline> convertToModal(ArrayList<BuslineResponse> list) {
        ArrayList<Busline> lines = new ArrayList<>();
        list.forEach(it -> lines.add(new Busline(it.getId(), it.getCode(), it.getName())));
        return lines;
    }

    static Itinerary convertToItinerary(String content) {
        JSONObject obj = new JSONObject(content);
        Itinerary itinerary = new Itinerary();
        itinerary.setLineId(obj.getInt("idlinha"));
        itinerary.setLineCode(obj.getString("codigo"));
        itinerary.setLineName(obj.getString("nome"));
        itinerary.setCoords(convertCoords(content));
        return itinerary;
    }

    static ArrayList<Coord> convertCoords(String content) {
        JSONObject obj = new JSONObject(content);
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

    private static JSONObject getNullableCoord(JSONObject obj, Integer key) {
        try {
            return obj.getJSONObject(key.toString());
        } catch (JSONException ignore) {
            return null;
        }
    }

}
