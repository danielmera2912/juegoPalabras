package com.example.juegopalabras.repos;

import com.example.juegopalabras.modelo.Partida;
import com.example.juegopalabras.modelo.PartidaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PartidaRepository extends JpaRepository<Partida, PartidaId> {

    List<Partida> findAllByJugadorId(int jugadorId);

    List<Partida> findAllByJuegoId(int juegoId);

    Optional<Partida> findById(Long id);
}