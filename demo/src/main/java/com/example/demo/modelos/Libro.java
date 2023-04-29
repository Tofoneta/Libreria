package com.example.demo.modelos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idLibro;
    @NotNull
    private String ISBN;
    @NotNull
    private String titulo;
    @NotNull
    private String autor;
    @NotNull
    private String area;
    @NotNull
    private String numeroEdicion;
    @NotNull
    private String cantidadPaginas;
    @NotNull
    private String Editorial;

    @OneToOne(mappedBy = "libro")
    @JsonIgnore
    private Item item;

}

