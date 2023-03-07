package com.example.juegopalabras.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data @NoArgsConstructor @AllArgsConstructor @Entity
public class Partida {

    @EmbeddedId
    private PartidaId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("jugadorId")
    private Jugador jugador;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("juegoId")
    private Juego juego;

    @Column(name = "intentos")
    private Integer intentos;

    @Column(name = "palabra")
    private String palabra;

    @Column(name = "puntos")
    private Integer puntos;

    @Column(name = "fecha")
    private Date fecha;

}