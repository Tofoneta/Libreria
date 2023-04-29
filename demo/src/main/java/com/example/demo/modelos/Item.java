package com.example.demo.modelos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.nio.MappedByteBuffer;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_item;

    @NotNull
    private float precio;
    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Libro_ID", referencedColumnName = "idLibro")
    private Libro libro;


    @ManyToMany(mappedBy = "items")
    private List<Sucursal> sucursales;

}
