package com.sebastian.productos.controllers;

import java.util.Optional;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sebastian.productos.models.Producto;
import com.sebastian.productos.service.ProductosService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/productos")
public class ProductosController {
    @Autowired
    ProductosService productosService;

    @GetMapping
    public ResponseEntity<List<Producto>> listar() {
        List<Producto> productos = this.productosService.getAll();
        if(productos.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(productos);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable(name="id") Long id) {
        Optional<Producto> p = productosService.findById(id);
        if (p.isPresent()) {
            return ResponseEntity.ok(p.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping()
    public ResponseEntity<?> post(@RequestBody Producto p){
        Producto save = productosService.save(p);
        return ResponseEntity.status(HttpStatus.CREATED).body(save);
    }
    
}
