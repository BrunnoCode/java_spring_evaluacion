package com.evaluacion2.demo.services;

import com.evaluacion2.demo.models.Cliente;
import com.evaluacion2.demo.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServices {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente getClienteById(Integer id){
        Optional<Cliente> optCliente = clienteRepository.findById(id);
        return optCliente.orElse(null);
    }

    public List<Cliente> getAllClientes(){
        return clienteRepository.findAll();
    }

    public Cliente saveCliente(Cliente cliente){
        return clienteRepository.save(cliente);
    }

    public Optional<Cliente> updateCliente(Integer id, Cliente clienteDetails){
        return clienteRepository.findById(id).map(cliente -> {
            cliente.setName(clienteDetails.getName());
            cliente.setContact(clienteDetails.getContact());
            return clienteRepository.save(cliente);
        });
    }
    public boolean deleteCliente(Integer id){
        if (clienteRepository.existsById(id)){
            clienteRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
