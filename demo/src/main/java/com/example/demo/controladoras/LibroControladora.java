package com.example.demo.controladoras;

import com.example.demo.Repositorio.LibroRepositorio;
import com.example.demo.Servicios.LibroServicio;
import com.example.demo.modelos.Libro;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/libro")
@CrossOrigin(origins = "*")
public class LibroControladora {
    @Autowired
    private LibroServicio ls;

    @PostMapping("/agregarLibro")
    public ResponseEntity agregarLibro(@RequestBody final @NotNull Libro l) {
        ls.add(l);
        return null;
    }


    @GetMapping("/verLibros")
    public List<Libro> verLibros() {
        return ls.getLibros();
    }


    @PostMapping("/{id}/delete")
    public ResponseEntity delete(@PathVariable Integer id) {
        return ls.delete(id);
    }

    @PostMapping("/{id}/update")
    public ResponseEntity update(@PathVariable Integer id, @RequestBody Libro l) {
        return ls.update(id, l);
    }

    @GetMapping("/{id}")
    public Libro get(@PathVariable Integer id) {
        return ls.get(id);
    }




}


