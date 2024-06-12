package com.sebastian.clientes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sebastian.clientes.models.ClienteProducto;

public interface ClientesProductosRepository extends JpaRepository<ClienteProducto, Long>{
    
}
