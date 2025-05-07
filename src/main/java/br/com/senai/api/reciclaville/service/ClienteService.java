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

    //Método para listar todos os Clientes
    public List<Cliente> findAllClientes() {
        return clienteRepository.findAll();
    }

    //Método para listar um Cliente pelo Id
    public Cliente findClienteById(Long id) {
        return clienteRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Cliente com Id: " + id + " não encontrado"));
    }

    //Método para criar um Cliente
    public Cliente create(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    //Método para atualizar um Cliente
    public Cliente update(Long id, Cliente clienteUpdate) {
        Cliente existingCliente = findClienteById(id);
        existingCliente.setNome(clienteUpdate.getNome());
        existingCliente.setCnpj(clienteUpdate.getCnpj());
        existingCliente.setAtividadeEconomica(clienteUpdate.getAtividadeEconomica());
        existingCliente.setResponsavel(clienteUpdate.getResponsavel());
        return clienteRepository.save(existingCliente);
    }

    //Método para deletar um Cliente
    public void delete(Long id) {
        Cliente cliente = findClienteById(id);
        clienteRepository.delete(cliente);
    }
}
