package com.example.juegopalabras.controller;

import com.example.juegopalabras.error.JugadorNotFoundException;
import com.example.juegopalabras.modelo.Jugador;
import com.example.juegopalabras.repos.JugadorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequiredArgsConstructor
public class JugadorController {

    private final JugadorRepository jugadorRepository;
    @GetMapping("/jugador")
    public List<Jugador> obtenerTodos() {
        List<Jugador> result =  jugadorRepository.findAll();
        if(result.isEmpty()){
            throw new JugadorNotFoundException();
        }
        return result;
    }

    @GetMapping("/jugador/{id}")
    public Jugador obtenerUno(@PathVariable Long id) {
        return jugadorRepository.findById(id).orElseThrow(() -> new JugadorNotFoundException(id));
    }
    @PostMapping("/jugador")
    public Jugador newJugador(@RequestBody Jugador newJugador){
        return jugadorRepository.save(newJugador);
    }
    @PutMapping("/jugador/{id}")
    public Jugador updateJugador(@RequestBody Jugador updateJugador, @PathVariable Long id){
        if(jugadorRepository.existsById(id)){
            updateJugador.setId(id);
            return jugadorRepository.save(updateJugador);
        }
        else{
            throw new JugadorNotFoundException(id);
        }
    }
    @DeleteMapping("/jugador/{id}")
    public Jugador deleteJugador(@PathVariable Long id) {
        if(jugadorRepository.existsById(id)){
            Jugador result = jugadorRepository.findById(id).get();
            jugadorRepository.deleteById(id);
            return result;
        }else{
            throw new JugadorNotFoundException(id);
        }
    }
}
