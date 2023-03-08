package com.example.juegopalabras.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor @Entity
public class Equipo {
    @Id @GeneratedValue
    private Long id;
    private String nombre;
    private String logo;
    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_modificacion")
    private LocalDateTime fechaModificacion;
    @OneToMany(mappedBy = "equipo")
    @JsonIgnore
    private List<Jugador> jugadores;

}
