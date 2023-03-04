package com.example.juegopalabras.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor @Entity
public class Equipo {
    @Id @GeneratedValue
    private Long id;
    private String nombre;
    private float puntos;
    private String logo;

}
