package com.example.juegopalabras.modelo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data @NoArgsConstructor @AllArgsConstructor @Builder @Entity
public class Partida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "jugador_id")
    private Jugador jugador;
    @ManyToOne
    @JoinColumn(name = "juego_id")
    private Juego juego;
    private Integer intentos;
    private String palabra;
    private Integer puntos;
    private LocalDateTime fecha;
}