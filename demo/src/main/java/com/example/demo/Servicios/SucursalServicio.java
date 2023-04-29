package com.example.demo.Servicios;

import com.example.demo.Repositorio.SucursalRepositorio;
import com.example.demo.modelos.Libro;
import com.example.demo.modelos.Sucursal;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.OK;

@Service

public class SucursalServicio {
    @Autowired
    private SucursalRepositorio sr;
    private final ModelMapper mm = new ModelMapper();

    public void add(Sucursal s){
        sr.save(s);
    }



    public Sucursal get(Integer id) {
        try {
            return sr.findById(id).orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    public ResponseEntity update(Integer id, Sucursal sa) {
        try {
            Sucursal sucursal = sr.findById(id).orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST));
            sucursal.setDireccion(sa.getDireccion());
            sucursal.setIdSucursal(sa.getIdSucursal());

            sr.save(sucursal);
            return ResponseEntity.status(OK).build();
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }
    public ResponseEntity delete(Integer id) {
        try {
            sr.deleteById(id);
            return ResponseEntity.status(OK).build();
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    public List<Sucursal> getAll() {
        try {
            return sr.findAll().stream().collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
