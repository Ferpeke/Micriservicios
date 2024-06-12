package com.sebastian.clientes.controllers;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sebastian.clientes.models.Cliente;
import com.sebastian.clientes.services.ClientesService;

@RestController
@RequestMapping("/clientes")
public class ClientesController {
    @Autowired
    ClientesService clientesService;

    @GetMapping
    public ResponseEntity<List<Cliente>> listar() {
        List<Cliente> clientes = this.clientesService.getAll();
        if(clientes.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(clientes);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable(name = "id") Long id) {
        Optional<Cliente> c = clientesService.findById(id);
        if (c.isPresent()) {
            return ResponseEntity.ok(c.get());
        } else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> post(@RequestBody Cliente c){
        Cliente save = clientesService.save(c);
        return ResponseEntity.status(HttpStatus.CREATED).body(save);
    }
}   
