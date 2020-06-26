package com.forttiori.exam.model.itinerary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;

@Entity
@Table(name = "ITINERARIO")
public class Itinerary {

    public Itinerary() {
    }

    public Itinerary(Integer idlinha, String codigo, String nome, ArrayList<Coord> coords) {
        this.idlinha = idlinha;
        this.codigo = codigo;
        this.nome = nome;
        this.coords = coords;
    }

    @Id
    @Column(name = "ID")
    private Integer idlinha;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "CODIGO")
    private String codigo;

    private ArrayList<Coord> coords;

    public Integer getIdlinha() {
        return idlinha;
    }

    public void setIdlinha(Integer idlinha) {
        this.idlinha = idlinha;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<Coord> getCoords() {
        return coords;
    }

    public void setCoords(ArrayList<Coord> coords) {
        this.coords = coords;
    }
}
