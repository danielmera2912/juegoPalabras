package com.example.juegopalabras.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data @Builder @NoArgsConstructor @AllArgsConstructor @Entity
public class Jugador {
    @Id @GeneratedValue
    private Long id;
    private String nombre;
    private String clave;
    private String avatar;
    private String rol = "user";
    @Column(nullable = false, unique = true)
    private String correo;
    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_modificacion")
    private LocalDateTime fechaModificacion;
    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name="id_equipo")
    private Equipo equipo;
    @EqualsAndHashCode.Exclude @ToString.Exclude
    @JsonIgnore
    @Builder.Default
    @OneToMany(mappedBy = "jugador", cascade = CascadeType.ALL)
    private Set<Partida> partidas = new HashSet<>();
}
