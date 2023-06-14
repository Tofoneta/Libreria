package com.example.demo.Servicios;

import com.example.demo.Repositorio.ItemRepositorio;
import com.example.demo.Repositorio.SucursalRepositorio;
import com.example.demo.modelos.Item;
import com.example.demo.modelos.Sucursal;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;

@Service

public class SucursalServicio {
    public SucursalServicio(SucursalRepositorio sr, ItemRepositorio ir, ItemServicio is) {
        this.sr = sr;
        this.ir = ir;
        this.is = is;
    }

    @Autowired
    private SucursalRepositorio sr;
    private ItemRepositorio ir ;
    private ItemServicio is;
    private final ModelMapper mm = new ModelMapper();


    public ResponseEntity add(Sucursal s) {
        List<Item> auxiliar = new ArrayList<Item>();
        for (int i = 0; i < s.getItemsAux().size(); i++) {
            int AuxI = i + 1;
            if (ir.existsById(AuxI)) {
                System.out.println(ir.getReferenceById(AuxI).getPrecio());
                auxiliar.add(ir.getReferenceById(AuxI));


            }
        }

        s.setItems(auxiliar);
        try {

            sr.save(s);
            return ResponseEntity.status(CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }

        /*
         boolean existenTodos = true;
        System.out.println(s.getItemsAux().size());
        for (int i = 0; i < s.getItemsAux().size(); i++) {
            int AuxI = i+1;
            if (!ir.existsById(i)) {
                existenTodos = false;
                System.out.println(existenTodos);

            }
        }
        * */
        /*
        try{

            sr.save(s);
            return ResponseEntity.status(CREATED).build();
        }
        catch (Exception e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    */
        /*
        List<Item> listaAux = new ArrayList<Item>();
        List<Item> listaAux2 = new ArrayList<Item>();

        listaAux = is.getAll();

        for(int i = 0;i<listaAux.size();i++){
            System.out.println(listaAux.get(i).getId_item());
        }*/

        /*
        for(int i = 0;i<s.getItemsAux().size();i++){
            int itemId = s.getItemsAux().get(i);
            Item aux = is.get(itemId);
            if(aux != null){
            {
                listaAuxiliar.set(indice,aux);
                indice++;

            }

        }
        if(!listaAuxiliar.isEmpty()){
            try{
                s.setItems(listaAuxiliar);
                sr.save(s);
                return ResponseEntity.status(CREATED).build();
            }
            catch (Exception e){
                return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }



    }}*/

    }


    public Sucursal get(Integer id) {
        try {
            return sr.findById(id).orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }


    //crear auto temporal, guardarle los datos y después agregarlo al Repositorio

    public ResponseEntity update(Integer id, Sucursal sa) {
        try {
            sa.setIdSucursal(id);

            Sucursal sucursal = sr.findById(id).orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST));

            sucursal.setIdSucursal(sa.getIdSucursal());
            sucursal.setDireccion(sa.getDireccion());
            sucursal.setIdSucursal(sa.getIdSucursal());
            sucursal.setItems(sa.getItems());
            sr.save(sucursal);
            return ResponseEntity.status(OK).build();
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }
    public ResponseEntity delete(Integer id) {
        try {
            sr.deleteById(id);
            return ResponseEntity.status(OK).build();
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    public List<Sucursal> getAll() {
        try {
            return sr.findAll().stream().collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    public List<Item> getBooksForPlaces(Integer id){
        try{
            List<Integer> librosDisponibles = sr.findLibrosEnStock(id);
            List<Item> auxiliar = new ArrayList<Item>();


            for(Integer idItem: librosDisponibles){
                System.out.println(ir.getReferenceById(idItem).getLibro().getAutor());
                System.out.println(auxiliar.size());
                auxiliar.add(ir.getReferenceById(idItem));
                //itemsSucursal.add(mm.map(item,Item.class));

            }
            return auxiliar;
        }catch (Exception e){
            throw new RuntimeException((e.getMessage()));

        }
    }

    /*    public List<Item> getBooksForPlaces(Integer id){
        try{
            List<Item> librosDisponibles = sr.findLibrosEnStock(id);
            List<SucursalDTO> lista = new ArrayList<>();
            for (Integer lid : librosDisponibles){
                Sucursal sucursal = sr.findById(lid).orElseThrow(() -> new HttpClientErrorException(BAD_REQUEST));

                lista.add(mm.map(sucursal, SucursalDTO.class));
            }
            return lista;
        }catch (Exception e){
            throw new RuntimeException((e.getMessage()));

        }
    }*/

}
