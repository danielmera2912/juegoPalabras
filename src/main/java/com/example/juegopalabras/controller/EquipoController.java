package com.example.juegopalabras.controller;

import com.example.juegopalabras.modelo.Equipo;
import com.example.juegopalabras.repos.EquipoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class EquipoController {
    private final EquipoRepository equipoRepository;
    @GetMapping("/equipo")
    public List<Equipo> obtenerTodos() { return equipoRepository.findAll();}

    @GetMapping("/equipo/{id}")
    public Equipo obtenerUno(@PathVariable Long id) { return equipoRepository.findById(id).orElse(null);}
    @PostMapping("/equipo")
    public Equipo newEquipo(@RequestBody Equipo newEquipo){
        return equipoRepository.save(newEquipo);
    }
    @PutMapping("/equipo/{id}")
    public Equipo updateEquipo(@RequestBody Equipo updateEquipo, @PathVariable Long id){
        updateEquipo.setId(id);
        return equipoRepository.save(updateEquipo);
    }
    @DeleteMapping("/equipo/{id}")
    public Equipo deleteEquipo(@PathVariable Long id) {
        Equipo result = equipoRepository.findById(id).get();
        equipoRepository.deleteById(id);
        return result;
    }
}
