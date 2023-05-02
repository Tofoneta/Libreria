package com.example.demo.modelos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cascade;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
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
    private Integer idSucursal;
    @NotNull
    private String direccion;
    @NotNull
    private String Localidad;


    @ManyToMany()
    @JoinTable(name = "disponibilidad", joinColumns = @JoinColumn(name = "Sucursal_ID"), inverseJoinColumns = @JoinColumn(name = "Item_ID"))
    private List<Item> items;


}

