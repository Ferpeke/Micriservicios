package com.sebastian.clientes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sebastian.clientes.models.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
    
}
