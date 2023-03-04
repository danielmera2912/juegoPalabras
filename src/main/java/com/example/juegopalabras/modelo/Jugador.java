package com.example.juegopalabras.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data @NoArgsConstructor @AllArgsConstructor @Entity
public class Jugador {
    @Id @GeneratedValue
    private Long id;
    private String nombre;
    private String clave;
    private String avatar;
    private float puntos;
    private String rol;
    @ManyToOne
    @JoinColumn(name="id_equipo")
    private Equipo equipo;
}
