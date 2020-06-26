package com.forttiori.exam.model.itinerary;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.forttiori.exam.model.id.CoordId;

import javax.persistence.*;

@Entity
@IdClass(CoordId.class)
@Table(name = "COORDENADAS")
public class Coord {

    public Coord() {
    }

    public Coord(Integer lineId, Double latitude, Double longitude) {
        this.lineId = lineId;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Id
    @JsonIgnore
    @Column(name = "ID_LINHA")
    private Integer lineId;
    @Id
    @Column(name = "LATITUDE")
    private Double latitude;
    @Id
    @Column(name = "LONGITUDE")
    private Double longitude;


    public Integer getLineId() {
        return lineId;
    }

    public void setLineId(Integer lineId) {
        this.lineId = lineId;
    }

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
}
