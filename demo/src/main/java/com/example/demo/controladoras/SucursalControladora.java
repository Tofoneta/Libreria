package com.example.demo.controladoras;

import com.example.demo.Servicios.SucursalServicio;
import com.example.demo.modelos.Item;
import com.example.demo.modelos.Sucursal;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sucursal")
@CrossOrigin(origins = "*")
public class SucursalControladora {
    @Autowired
    private SucursalServicio ss;

    @PostMapping("/agregarSucursal")
    public ResponseEntity agregarLibro(@RequestBody final @NotNull Sucursal s) {
        ss.add(s);
        return null;
    }


    @GetMapping("/verSucursales")
    public List<Sucursal> verLibros() {
        return ss.getAll();
    }


    @PostMapping("/{id}/delete")
    public ResponseEntity delete(@PathVariable Integer id) {
        return ss.delete(id);
    }

    @PostMapping("/{id}/update")
    public ResponseEntity update(@PathVariable Integer id, @RequestBody Sucursal sa){
        return ss.update(id,sa);
    }



    @GetMapping("/{id}")
    public Sucursal get(@PathVariable Integer id) {
        return ss.get(id);
    }


    @GetMapping("/libros/{id}")
    public List<Item> getLibros(@PathVariable Integer id) {
        return ss.getBooksForPlaces(id);
    }
}