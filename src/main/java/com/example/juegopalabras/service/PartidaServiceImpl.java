package com.example.juegopalabras.service;

import com.example.juegopalabras.error.JugadorNotFoundException;
import com.example.juegopalabras.modelo.Partida;
import com.example.juegopalabras.repos.PartidaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class PartidaServiceImpl implements PartidaService{
    private final PartidaRepository partidaRepository;

    @Override
    public List<Partida> findAll() {
        return partidaRepository.findAll();
    }

    @Override
    public Optional<Partida> findById(Long id) {
        return partidaRepository.findById(id);
    }

    @Override
    public Partida save(Partida partida) {
        partida.setFecha(LocalDateTime.now());
        return partidaRepository.save(partida);
    }

    @Override
    public void deleteById(Long id) {
        partidaRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return partidaRepository.existsById(id);
    }
    @Override
    public List<Partida> findByJugadorId(Long jugadorId) {
        return partidaRepository.findByJugadorId(jugadorId);
    }
    @Override
    public int getTotalPuntosByJugadorId(Long jugadorId) {
        List<Partida> partidas = findByJugadorId(jugadorId);
        if(partidas == null || partidas.isEmpty()){
            throw new JugadorNotFoundException(jugadorId);
        }
        int totalPuntos = 0;
        for (Partida partida : partidas) {
            totalPuntos += partida.getPuntos();
        }
        return totalPuntos;
    }
}
