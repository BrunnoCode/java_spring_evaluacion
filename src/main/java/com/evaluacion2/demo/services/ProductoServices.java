package com.evaluacion2.demo.services;


import com.evaluacion2.demo.models.Producto;
import com.evaluacion2.demo.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class ProductoServices {


    private final ProductoRepository productoRepository;

    @Autowired
    public ProductoServices(ProductoRepository productoRepository){
        this.productoRepository = productoRepository;
    }

    public Producto getProductById(Integer id){
        Optional<Producto> optProducto = productoRepository.findById(id);
        return optProducto.orElse(null);
    }

    public List<Producto> findAllProducts(){
        List<Producto> productList = productoRepository.findAll();
        return productList;
    }

    public Producto saveProducto(Producto producto){
        return productoRepository.save(producto);
    }

    public Optional<Producto> updateProducto(Integer id,
                                             Producto productoDetails){
        return productoRepository.findById(id).map(producto -> {
            producto.setName(productoDetails.getName());
            producto.setPrice(productoDetails.getPrice());
            return productoRepository.save(producto);
        });
    }

    public boolean deleteProducto(Integer id){
        if (productoRepository.existsById(id)){
            productoRepository.deleteById(id);
            return true;
        }
        return false;
    }



}

