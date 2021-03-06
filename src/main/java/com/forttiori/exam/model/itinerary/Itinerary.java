package com.forttiori.exam.model.itinerary;

import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@EqualsAndHashCode
@Table(name = "ITINERARIO")
public class Itinerary {

    public Itinerary() {
    }

    public Itinerary(Integer lineId, String lineCode, String lineName, ArrayList<Coord> coords) {
        this.lineId = lineId;
        this.lineCode = lineCode;
        this.lineName = lineName;
        this.coords = coords;
    }

    @Id
    @ApiModelProperty("Id Linha")
    @Column(name = "ID_LINHA")
    private Integer lineId;

    @ApiModelProperty("Código Linha")
    @Column(name = "CODIGO_LINHA")
    private String lineCode;

    @ApiModelProperty("Nome Linha")
    @Column(name = "NOME_LINHA")
    private String lineName;

    @Transient
    private ArrayList<Coord> coords;

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

    public ArrayList<Coord> getCoords() {
        return coords;
    }

    public void setCoords(ArrayList<Coord> coords) {
        this.coords = coords;
    }
}
