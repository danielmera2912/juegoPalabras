package com.example.juegopalabras.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
@Data @NoArgsConstructor @AllArgsConstructor @Entity
public class Juego {
    @Id
    @GeneratedValue
    private Long id;
    private String nombre;
    private Long intentos;
    @Enumerated(EnumType.STRING)
    private Dificultad dificultad;
    private String instrucciones;
    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_modificacion")
    private LocalDateTime fechaModificacion;
    enum Dificultad {
        FACIL, NORMAL, DIFICIL
    }
}
