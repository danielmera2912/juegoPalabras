package com.example.juegopalabras.service;

import com.example.juegopalabras.modelo.Juego;
import com.example.juegopalabras.repos.JuegoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class JuegoServiceImpl implements JuegoService {
    private final JuegoRepository juegoRepository;

    @Override
    public List<Juego> findAll() {
        return juegoRepository.findAll();
    }

    @Override
    public Optional<Juego> findById(Long id) {
        return juegoRepository.findById(id);
    }

    @Override
    public Juego save(Juego juego) {
        juego.setFechaCreacion(LocalDateTime.now());
        juego.setFechaModificacion(LocalDateTime.now());
        return juegoRepository.save(juego);
    }

    @Override
    public void deleteById(Long id) {
        juegoRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return juegoRepository.existsById(id);
    }

}
