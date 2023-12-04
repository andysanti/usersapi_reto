package com.example.prueba.api.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "token")
    private String token;

    @Column(name = "active")
    private boolean active;

    @Column(name = "created")
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime created;

    @Column(name = "modified")
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime modified;

    @Column(name = "last_login")
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime last_login;

    @OneToMany(orphanRemoval=true)
    @JoinColumn(name="id")
    private Set<Telefono> telefonos;




}
