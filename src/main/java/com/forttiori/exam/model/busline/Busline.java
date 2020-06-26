package com.forttiori.exam.model.busline;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "LINHAS")
public class Busline {

    public Busline() {
    }

    public Busline(Integer id, String code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
    }

    @Id
    @Column(name = "ID")
    private Integer id;

    @Column(name = "CODIGO")
    private String code;

    @Column(name = "NOME")
    private String name;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
