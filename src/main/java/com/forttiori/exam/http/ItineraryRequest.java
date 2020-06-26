package com.forttiori.exam.http;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;

public class ItineraryRequest {

    @NotNull
    private Integer lineId;

    @NotBlank
    private String lineCode;

    @NotBlank
    private String lineName;

    @NotEmpty
    private ArrayList<CoordRequest> coords;

    public Integer getLineId() {
        return lineId;
    }

    public void setLineId(Integer lineId) {
        this.lineId = lineId;
    }

    public String getLineCode() {
        return lineCode;
    }

    public void setLineCode(String lineCode) {
        this.lineCode = lineCode;
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public ArrayList<CoordRequest> getCoords() {
        return coords;
    }

    public void setCoords(ArrayList<CoordRequest> coords) {
        this.coords = coords;
    }
}
