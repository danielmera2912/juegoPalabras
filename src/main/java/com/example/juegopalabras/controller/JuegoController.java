package com.example.juegopalabras.controller;

import com.example.juegopalabras.error.JuegoNotFoundException;
import com.example.juegopalabras.modelo.Juego;
import com.example.juegopalabras.service.JuegoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class JuegoController {
    private final JuegoService juegoService;
    @GetMapping("/juego")
    public List<Juego> obtenerTodos() {
        List<Juego> result =  juegoService.findAll();
        if(result.isEmpty()){
            throw new JuegoNotFoundException();
        }
        return result;
    }

    @GetMapping("/juego/{id}")
    public Juego obtenerUno(@PathVariable Long id) {
        return juegoService.findById(id).orElseThrow(() -> new JuegoNotFoundException(id));
    }

    @PostMapping("/juego")
    public Juego newJuego(@RequestBody Juego newJuego){
        return juegoService.save(newJuego);
    }

    @PutMapping("/juego/{id}")
    public Juego updateJuego(@RequestBody Juego updateJuego, @PathVariable Long id){
        if (juegoService.existsById(id)) {
            updateJuego.setId(id);
            updateJuego.setFechaModificacion(LocalDateTime.now());
            return juegoService.save(updateJuego);
        } else {
            throw new JuegoNotFoundException(id);
        }
    }

    @DeleteMapping("/juego/{id}")
    public Juego deleteJuego(@PathVariable Long id) {
        if(juegoService.existsById(id)){
            Juego result = juegoService.findById(id).get();
            juegoService.deleteById(id);
            return result;
        }else{
            throw new JuegoNotFoundException(id);
        }
    }
}
