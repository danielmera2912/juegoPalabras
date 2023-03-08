package com.example.juegopalabras.controller;

import com.example.juegopalabras.error.EquipoNotFoundException;
import com.example.juegopalabras.modelo.Equipo;
import com.example.juegopalabras.modelo.Jugador;
import com.example.juegopalabras.service.EquipoServiceImpl;
import com.example.juegopalabras.service.JugadorService;
import com.example.juegopalabras.service.PartidaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class EquipoController {
    private final EquipoServiceImpl equipoService;
    private final JugadorService jugadorService;
    private final PartidaService partidaService;
    @GetMapping("/equipo")
    public List<Equipo> obtenerTodos() {
        List<Equipo> result =  equipoService.findAll();
        if(result.isEmpty()){
            throw new EquipoNotFoundException();
        }
        return result;
    }
    @GetMapping("/equipo/{id}/puntos")
    public int obtenerPuntosTotales(@PathVariable Long id) {
        List<Jugador> jugadores = equipoService.findById(id).orElseThrow(() -> new EquipoNotFoundException(id)).getJugadores();
        int totalPuntos = 0;

        for (Jugador jugador : jugadores) {
            totalPuntos += partidaService.getTotalPuntosByJugadorId(jugador.getId());
        }

        return totalPuntos;
    }

    @GetMapping("/equipo/{id}")
    public Equipo obtenerUno(@PathVariable Long id) {
        return equipoService.findById(id).orElseThrow(() -> new EquipoNotFoundException(id));
    }

    @PostMapping("/equipo")
    public Equipo newEquipo(@RequestBody Equipo newEquipo){
        return equipoService.save(newEquipo);
    }

    @PutMapping("/equipo/{id}")
    public Equipo updateEquipo(@RequestBody Equipo updateEquipo, @PathVariable Long id){
        if (equipoService.existsById(id)) {
            updateEquipo.setId(id);
            updateEquipo.setFechaModificacion(LocalDateTime.now());
            return equipoService.save(updateEquipo);
        } else {
            throw new EquipoNotFoundException(id);
        }
    }

    @DeleteMapping("/equipo/{id}")
    public Equipo deleteEquipo(@PathVariable Long id) {
        if(equipoService.existsById(id)){
            Equipo result = equipoService.findById(id).get();
            equipoService.deleteById(id);
            return result;
        }else{
            throw new EquipoNotFoundException(id);
        }
    }
}
