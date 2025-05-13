package br.com.senai.api.reciclaville.service;

import br.com.senai.api.reciclaville.model.entity.Cliente;
import br.com.senai.api.reciclaville.model.exceptions.ResourceNotFoundException;
import br.com.senai.api.reciclaville.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    public List<Cliente> findAllClientes() {
        return clienteRepository.findAll();
    }

    public Cliente findClienteById(Long id) {
        return clienteRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Cliente com Id: " + id + " não encontrado"));
    }

    public Cliente create(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente update(Long id, Cliente clienteUpdate) {
        Cliente existingCliente = findClienteById(id);
        existingCliente.setNome(clienteUpdate.getNome());
        existingCliente.setCnpj(clienteUpdate.getCnpj());
        existingCliente.setAtividadeEconomica(clienteUpdate.getAtividadeEconomica());
        existingCliente.setResponsavel(clienteUpdate.getResponsavel());
        return clienteRepository.save(existingCliente);
    }

    public void delete(Long id) {
        Cliente cliente = findClienteById(id);
        clienteRepository.delete(cliente);
    }
}
