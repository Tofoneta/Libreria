package com.example.demo.controladoras;

import com.example.demo.Servicios.ItemServicio;
import com.example.demo.Servicios.LibroServicio;
import com.example.demo.modelos.Item;
import com.example.demo.modelos.Libro;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item")
@CrossOrigin(origins = "*")
public class ItemControladora {
    @Autowired
    private ItemServicio is;

    @PostMapping("/agregarLibro")
    public ResponseEntity agregarItem(@RequestBody final @NotNull Item i) {
        is.add(i);
        return null;
    }


    @GetMapping("/verLibros")
    public List<Item> verLibros() {
        return is.getAll();
    }


    @PostMapping("/{id}/delete")
    public ResponseEntity delete(@PathVariable Integer id) {
        return is.delete(id);
    }

    @PostMapping("/{id}/update")
    public ResponseEntity update(@PathVariable Integer id, @RequestBody Item i) {
        return is.update(id, i);
    }

    @GetMapping("/{id}")
    public Item get(@PathVariable Integer id) {
        return is.get(id);
    }

}
