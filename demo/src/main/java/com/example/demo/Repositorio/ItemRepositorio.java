package com.example.demo.Repositorio;

import com.example.demo.modelos.Item;
import com.example.demo.modelos.SucursalDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepositorio extends JpaRepository<Item,Integer> {
    @Query(value = "select id_sucursal from disponibilidad_sucursal where id_items = :IdItem",nativeQuery = true)

    List<Integer> findLibrosEnStock(Integer IdItem);

}
