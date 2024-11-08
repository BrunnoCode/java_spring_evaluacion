package com.evaluacion2.demo.models;


import lombok.Data;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "cliente") // cambia el nombre de la tabla en el DB
@Data // obtiene getters, setters, constructors, equals y hascodes
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idClient;
    private String name;
    private String contact;
}
