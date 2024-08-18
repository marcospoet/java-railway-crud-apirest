package com.marcospoet.apirest.apirest.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marcospoet.apirest.apirest.Entities.Producto;


//le tengo que indicar el tipo de entidad y el tipo de dato de la clave primaria con la cual va a trabajar
public interface IProductoRepository extends JpaRepository<Producto,Long>{

}
