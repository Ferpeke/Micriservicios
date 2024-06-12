package com.sebastian.clientes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sebastian.clientes.models.ClienteProducto;
import com.sebastian.clientes.repositories.ClientesProductosRepository;

@Service
public class ClientesProductosService {

    @Autowired
    ClientesProductosRepository cpRepo;

    public ClienteProducto save(ClienteProducto t) {
        return this.cpRepo.save(t);
    }

}
