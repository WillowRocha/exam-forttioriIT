package com.forttiori.exam.http;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class ItineraryFilterRequest {

    @NotNull
    private Double latitude;

    @NotNull
    private Double longitude;

    @Min(0L)
    @NotNull
    private Double radius;

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getRadius() {
        return radius;
    }

    public void setRadius(Double radius) {
        this.radius = radius;
    }
}
