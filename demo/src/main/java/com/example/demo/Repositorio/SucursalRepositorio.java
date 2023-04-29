package com.example.demo.Repositorio;


import com.example.demo.modelos.Sucursal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SucursalRepositorio extends JpaRepository<Sucursal,Integer> {
}
