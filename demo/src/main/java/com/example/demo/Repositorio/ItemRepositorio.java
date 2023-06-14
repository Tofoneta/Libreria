package com.example.demo.Repositorio;

import com.example.demo.modelos.Item;
import com.example.demo.modelos.SucursalDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepositorio extends JpaRepository<Item,Integer> {
    @Query(value = "select id_item, precio from item where id_item = :IdItem",nativeQuery = true)

    Item findID(Integer IdItem);

}
