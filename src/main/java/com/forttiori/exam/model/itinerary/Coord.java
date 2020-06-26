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

    public Coord(Double lat, Double lng) {
        this.lat = lat;
        this.lng = lng;
    }

    @Id
    @JsonIgnore
    @Column(name = "ID")
    private Integer idLinha;
    @Id
    @Column(name = "LATITUDE")
    private Double lat;
    @Id
    @Column(name = "LONGITUDE")
    private Double lng;


    public Integer getIdLinha() {
        return idLinha;
    }

    public void setIdLinha(Integer idLinha) {
        this.idLinha = idLinha;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }
}
