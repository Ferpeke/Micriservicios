package com.sebastian.productos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sebastian.productos.models.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

}
