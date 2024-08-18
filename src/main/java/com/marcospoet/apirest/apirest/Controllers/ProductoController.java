package com.marcospoet.apirest.apirest.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marcospoet.apirest.apirest.Entities.Producto;
import com.marcospoet.apirest.apirest.Repositories.IProductoRepository;

//aca es donde ponemos todo lo que vamos a consumir con el front, con un cliente
//es decir, aca vamos a poner los metodos que van a consumir los servicios

@RestController
@RequestMapping("/productos") //con esto le decimos que todas las rutas que esten en este controlador van a tener que empezar con /productos
public class ProductoController {

    @Autowired //con esto le decimos a spring que inyecte la dependencia
    private IProductoRepository productoRepository;

    //aca vamos a poner los metodos que van a consumir los servicios, todos los metodos HTTP
    //GET, POST, PUT, DELETE
    @GetMapping
    public List<Producto> getAllProductos(){
        return productoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Producto obtenerProductoPorId(@PathVariable Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el producto con el ID: " + id));
    }

    @PostMapping
    public Producto crearProducto(@RequestBody Producto producto){
        return productoRepository.save(producto);
    }

    /**
     * Actualiza un producto existente en la base de datos.
     * 
     * @param id       El ID del producto a actualizar.
     * @param producto El objeto Producto con los nuevos datos.
     * @return El producto actualizado.
     */
    @PutMapping("/{id}")
    public Producto actualizarProducto(@PathVariable Long id, @RequestBody Producto detallesProducto) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el producto con el ID: " + id));

        producto.setNombre(detallesProducto.getNombre());
        producto.setPrecio(detallesProducto.getPrecio());

        return productoRepository.save(producto);
    }

    @DeleteMapping("/{id}")
    public String borrarProducto(@PathVariable Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el producto con el ID: " + id));

        productoRepository.delete(producto);
        return "El producto con el ID: " + id + " fue eliminado correctamente";
    }
}
