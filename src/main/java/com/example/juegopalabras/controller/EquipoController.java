package com.example.juegopalabras.controller;

import com.example.juegopalabras.error.EquipoNotFoundException;
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
    public List<Equipo> obtenerTodos() {
        List<Equipo> result =  equipoRepository.findAll();
        if(result.isEmpty()){
            throw new EquipoNotFoundException();
        }
        return result;
    }

    @GetMapping("/equipo/{id}")
    public Equipo obtenerUno(@PathVariable Long id) {
        return equipoRepository.findById(id).orElseThrow(() -> new EquipoNotFoundException(id));
    }
    @PostMapping("/equipo")
    public Equipo newEquipo(@RequestBody Equipo newEquipo){
        return equipoRepository.save(newEquipo);
    }
    @PutMapping("/equipo/{id}")
    public Equipo updateEquipo(@RequestBody Equipo updateEquipo, @PathVariable Long id){
        if(equipoRepository.existsById(id)){
            updateEquipo.setId(id);
            return equipoRepository.save(updateEquipo);
        }
        else{
            throw new EquipoNotFoundException(id);
        }

    }
    @DeleteMapping("/equipo/{id}")
    public Equipo deleteEquipo(@PathVariable Long id) {
        if(equipoRepository.existsById(id)){
            Equipo result = equipoRepository.findById(id).get();
            equipoRepository.deleteById(id);
            return result;
        }else{
            throw new EquipoNotFoundException(id);
        }

    }
}
