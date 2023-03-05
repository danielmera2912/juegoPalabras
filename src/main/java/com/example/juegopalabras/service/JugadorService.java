package com.example.juegopalabras.service;
import com.example.juegopalabras.modelo.Jugador;

import java.util.List;
import java.util.Optional;

public interface JugadorService {
    List<Jugador> findAll();
    Optional<Jugador> findById(Long id);
    Jugador save(Jugador jugador);
    void deleteById(Long id);
    boolean existsById(Long id);
}
