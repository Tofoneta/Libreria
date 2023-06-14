package com.example.demo.Repositorio;


import com.example.demo.modelos.Sucursal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SucursalRepositorio extends JpaRepository<Sucursal,Integer> {
    @Query(value = "select item_id from disponibilidad where sucursal_id = :idSucursal",nativeQuery = true)


    List<Integer> findLibrosEnStock(Integer idSucursal);

}
