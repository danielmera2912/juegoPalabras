package com.example.juegopalabras.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data @Builder @NoArgsConstructor @AllArgsConstructor @Entity
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
    @EqualsAndHashCode.Exclude @ToString.Exclude
    @JsonIgnore
    @Builder.Default
    @OneToMany(mappedBy = "juego", cascade = CascadeType.ALL)
    private Set<Partida> partidas = new HashSet<>();
    enum Dificultad {
        FACIL, NORMAL, DIFICIL
    }
}
