package com.example.prueba.api.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "telefono")
public class Telefono {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idtelefono;

    @Column(name = "numero")
    private String numero;

    @Column(name = "countrycode")
    private String countryCode;

    @Column(name = "citycode")
    private String cityCode;

    @Column(name = "id", nullable = false)
    private Integer id;


}
