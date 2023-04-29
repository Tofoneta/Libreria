package com.example.demo.modelos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Sucursal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idSucursal;
    @NotNull
    private String direccion;
    @NotNull
    private String Localidad;


    @ManyToMany
    @JoinTable(name = "disponibilidad_Sucursal", joinColumns = { @JoinColumn(name = "idSucursal")},inverseJoinColumns = {@JoinColumn(name = "idItems")})
    private List<Item> items;

}

/*
    @ManyToMany(mappedBy = "sucursales")
    @JsonIgnore
    private List<Item> items;


}*/
