package com.sebastian.clientes.services;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sebastian.clientes.models.Cliente;
import com.sebastian.clientes.repositories.ClienteRepository;

@Component
public class ClientesService {
    @Autowired
    ClienteRepository clienteRepo;

    public List<Cliente> getAll() {
        List<Cliente> listado = new ArrayList<>();
        listado  = this.clienteRepo.findAll();
        return listado;
    }
    
    public Optional<Cliente> findById(Long id ) {
        Optional<Cliente> find = this.clienteRepo.findById(id);
        if(find.isPresent()) {
            return find;
        } else {
            return Optional.empty();
        }
    }

    public Cliente save(Cliente t) {
        return this.clienteRepo.save(t);
    }

    public void deleteById(Cliente t) {
        this.clienteRepo.delete(t);
    }
}
