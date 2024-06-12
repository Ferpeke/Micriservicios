package com.sebastian.productos.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sebastian.productos.models.Producto;
import com.sebastian.productos.repositories.ProductoRepository;

@Component
public class ProductosService {
    @Autowired
    ProductoRepository productoRepo;

    public List<Producto> getAll() {
        List<Producto> listado = new ArrayList<>();
        listado = this.productoRepo.findAll();
        return listado;
    }

    public Optional<Producto> findById(Long id) {
        Optional<Producto> find = this.productoRepo.findById(id);
        if (find.isPresent()) {
            return find;
        } else {
            return Optional.empty();
        }
    }

    public Producto save(Producto p) {
        return this.productoRepo.save(p);
    }

    public void deleteById(Producto p){
        this.productoRepo.delete(p);
    }
}
