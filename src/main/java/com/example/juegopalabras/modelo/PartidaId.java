package com.example.juegopalabras.modelo;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class PartidaId implements Serializable {

    @Column(name = "id_jugador")
    private Integer jugadorId;

    @Column(name = "id_juego")
    private Integer juegoId;


}
