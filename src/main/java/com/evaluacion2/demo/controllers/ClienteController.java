package com.evaluacion2.demo.controllers;

import com.evaluacion2.demo.models.Cliente;
import com.evaluacion2.demo.models.Producto;
import com.evaluacion2.demo.services.ClienteServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
@CrossOrigin(origins = "*")
public class ClienteController {

    private final ClienteServices clienteServices;

    @Autowired
    public ClienteController(ClienteServices clienteServices){
        this.clienteServices = clienteServices;
    }

    // METODO GET ALL
    @GetMapping
    public List<Cliente> getAllClientes(){
        return clienteServices.getAllClientes();
    }

    // METODO FIND BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable("id") Integer id) {

        Cliente cliente = clienteServices.getClienteById(id);
        return ResponseEntity.ok(cliente);
    }

    // METODO POST CREATE NEW CLIENTE
    @PostMapping()
    public ResponseEntity<Cliente> createCliente(@RequestBody Cliente cliente){
        Cliente newCliente = clienteServices.saveCliente(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCliente);
    }

    // METODO PUT UPDATE CLIENTE
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable("id") Integer id, @RequestBody Cliente clienteDetails){
        Optional<Cliente> updatedCliente = clienteServices.updateCliente(id,
                clienteDetails);
        return updatedCliente.map(ResponseEntity::ok
        ).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // METODO DELETE, DELETE CLIENTE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable("id") Integer id) {
        if (clienteServices.deleteCliente(id)){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
