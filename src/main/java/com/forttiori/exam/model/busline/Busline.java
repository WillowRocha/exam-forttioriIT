package com.forttiori.exam.model.busline;


import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@EqualsAndHashCode
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
    @ApiModelProperty("Id")
    @Column(name = "ID")
    private Integer id;

    @ApiModelProperty("Código")
    @Column(name = "CODIGO")
    private String code;

    @ApiModelProperty("Nome")
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
