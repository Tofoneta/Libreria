package com.example.demo.Servicios;


import com.example.demo.Repositorio.ItemRepositorio;
import com.example.demo.Repositorio.LibroRepositorio;
import com.example.demo.Repositorio.SucursalRepositorio;
import com.example.demo.modelos.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;

@Service
public class ItemServicio {
    @Autowired
    private ItemRepositorio ir;
    private SucursalRepositorio sr;
    private LibroRepositorio lr;
    private final ModelMapper mm = new ModelMapper();

    @Autowired
    public ItemServicio(ItemRepositorio ir, SucursalRepositorio sr) {
        this.ir = ir;
        this.sr = sr;

    }

    public ResponseEntity add(Item i) {
        try {
            ir.save(i);

            return ResponseEntity.status(CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }


    //Get all, obtener todos los datos

    public List<Item> getAll() {
        try {
            return ir.findAll().stream().collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    //Deletear

    public ResponseEntity delete(Integer id) {
        try {
            ir.deleteById(id);
            return ResponseEntity.status(OK).build();
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    //Actualizar registro

    public ResponseEntity update(Integer id, Item i) {
        try {
            Item item = ir.findById(id).orElseThrow(() -> new HttpClientErrorException(BAD_REQUEST));
            item.setId_item(id);
            item.setPrecio(i.getPrecio());
            item.setLibro(i.getLibro());
            ir.save(item);
            return ResponseEntity.status(OK).build();
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();

        }
    }




    public Item get(Integer id) {
        try {

            return ir.findById(id).orElseThrow(() -> new HttpClientErrorException(BAD_REQUEST));
        }
        catch (Exception e){
            throw new RuntimeException((e.getMessage()));
        }}

    public Item getID(Integer idItem){
        try {
            return ir.findID(idItem);

        }
        catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

}
