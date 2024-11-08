package com.evaluacion2.demo.controllers;

import com.evaluacion2.demo.models.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import com.evaluacion2.demo.services.ProductoServices;

@RestController
@RequestMapping("/productos")
@CrossOrigin(origins = "*")
public class ProductoController {
    private final ProductoServices productoServices;

    @Autowired
    public ProductoController(ProductoServices productoServices) {
        this.productoServices = productoServices;
    }

    // METODO LIST ALL PRODUCTS GET
    @GetMapping
    public ResponseEntity<List<Producto>> getAllProductos() {
        List<Producto> productos = productoServices.findAllProducts();
        return ResponseEntity.ok(productos);
    }

    // METODO FIND BY ID GET
    @GetMapping("/{id}")
    public ResponseEntity<Producto> getProductoById(@PathVariable("id") Integer id) {
        Producto producto = productoServices.getProductById(id);
        return ResponseEntity.ok(producto);
    }

    // METODO POST CREATE NEW PRODUCT
    @PostMapping
    public ResponseEntity<Producto> createProducto(@RequestBody Producto producto) {
        Producto newProducto = productoServices.saveProducto(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newProducto);
    }

    // METODO PUT UPDATE PRODUCT
    @PutMapping("/{id}")
    public ResponseEntity<Producto> updateProducto(@PathVariable("id") Integer id, @RequestBody Producto productoDetails) {
        Optional<Producto> updatedProduct =
                productoServices.updateProducto(id, productoDetails);
        return updatedProduct.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // METODO DELETE, DELETE PRODUCT
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProducto(@PathVariable("id") Integer id){
        if (productoServices.deleteProducto(id)){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


}

