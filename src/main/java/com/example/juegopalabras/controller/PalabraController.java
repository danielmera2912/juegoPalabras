package com.example.juegopalabras.controller;

import com.example.juegopalabras.error.PalabraNotFoundException;
import com.example.juegopalabras.service.PalabraService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping(produces = "application/json")
@RestController
@RequiredArgsConstructor
public class PalabraController {
    @Autowired
    private PalabraService palabraService;
    @GetMapping("/palabra")
    public String obtenerPalabra() {
        String palabra = palabraService.obtenerPalabra();
        if(palabra == null){
            throw new PalabraNotFoundException();
        }
        return palabra;


    }
    @GetMapping("/palabras")
    public List<String> obtenerTodasPalabras() {
        List<String> palabras = palabraService.obtenerTodasPalabras();
        if(palabras == null || palabras.isEmpty()){
            throw new PalabraNotFoundException();
        }
        return palabras;


    }
    @GetMapping("/palabras/{numero}")
    public List<String> obtenerPalabras(@PathVariable Long numero) {
        List<String> palabras =  palabraService.obtenerPalabras(numero);
        if(palabras == null || palabras.isEmpty()){
            throw new PalabraNotFoundException();
        }
        return palabras;

    }
    @GetMapping("/palabra/filtro={filtro}")
    public String obtenerPalabraFiltrada(@PathVariable String filtro) {
        String palabra=  palabraService.obtenerPalabraFiltrada(filtro);
        if(palabra==null){
            throw new PalabraNotFoundException();
        }
        return palabra;


    }
    @GetMapping("/palabras/filtro={filtro}")
    public List<String> obtenerTodasPalabraFiltrada(@PathVariable String filtro) {
        List<String> palabras = palabraService.obtenerTodasPalabraFiltrada(filtro);
        if(palabras==null || palabras.isEmpty()){
            throw new PalabraNotFoundException();
        }
        return palabras;


    }
}
