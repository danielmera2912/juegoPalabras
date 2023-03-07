package com.example.juegopalabras.controller;

import com.example.juegopalabras.modelo.Partida;
import com.example.juegopalabras.repos.PartidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/partida")
public class PartidaController {

    @Autowired
    private PartidaRepository partidaRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Partida> getPartida(@PathVariable Long id) {
        Optional<Partida> partida = partidaRepository.findById(id);
        if (partida.isPresent()) {
            return ResponseEntity.ok(partida.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Partida> crearPartida(@RequestBody Partida partida) {
        Partida nuevaPartida = partidaRepository.save(partida);
        return ResponseEntity.created(
                        ServletUriComponentsBuilder.fromCurrentRequest()
                                .path("/{id}")
                                .buildAndExpand(nuevaPartida.getId())
                                .toUri())
                .body(nuevaPartida);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Partida> actualizarPartida(@PathVariable Long id, @RequestBody Partida partida) {
        Optional<Partida> partidaExistente = partidaRepository.findById(id);
        if (partidaExistente.isPresent()) {
            Partida partidaActualizada = partidaExistente.get();
            partidaActualizada.setIntentos(partida.getIntentos());
            partidaActualizada.setPalabra(partida.getPalabra());
            partidaActualizada.setPuntos(partida.getPuntos());
            partidaActualizada.setFecha(partida.getFecha());
            partidaRepository.save(partidaActualizada);
            return ResponseEntity.ok(partidaActualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPartida(@PathVariable Long id) {
        Optional<Partida> partida = partidaRepository.findById(id);
        if (partida.isPresent()) {
            partidaRepository.delete(partida.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/jugador/{idJugador}")
    public List<Partida> getPartidasByJugador(@PathVariable Long idJugador) {
        return partidaRepository.findAllByJugadorId(Math.toIntExact(idJugador));
    }

    @GetMapping("/juego/{idJuego}")
    public List<Partida> getPartidasByJuego(@PathVariable Long idJuego) {
        return partidaRepository.findAllByJuegoId(Math.toIntExact(idJuego));
    }

}