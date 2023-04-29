package com.example.demo.Servicios;

import com.example.demo.Repositorio.LibroRepositorio;
import com.example.demo.modelos.Item;
import com.example.demo.modelos.Libro;
import com.example.demo.modelos.Sucursal;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.yaml.snakeyaml.events.Event;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.*;

@Service
public class LibroServicio {
    @Autowired
    private LibroRepositorio lr;
    private final ModelMapper mm = new ModelMapper();



    public List<Libro> getLibros(){
        return lr.findAll();
    }


    public ResponseEntity add(Libro l) {
        try {
            lr.save(l);
            return ResponseEntity.status(CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity delete(Integer id) {
        try {
            lr.deleteById(id);
            return ResponseEntity.status(OK).build();
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity update(Integer id, Libro l) {
        try {
            Libro libro = lr.findById(id).orElseThrow(() -> new HttpClientErrorException(BAD_REQUEST));
            libro.setIdLibro(id);
            libro.setNumeroEdicion(l.getNumeroEdicion());
            libro.setArea(l.getArea());
            libro.setISBN(l.getISBN());
            libro.setTitulo(l.getTitulo());
            libro.setAutor(l.getAutor());
            libro.setEditorial(l.getEditorial());
            libro.setCantidadPaginas(l.getCantidadPaginas());

            return ResponseEntity.status(OK).build();
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();

        }
    }

    public Libro get(Integer id) {
        try {
            return lr.findById(id).orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }


}
