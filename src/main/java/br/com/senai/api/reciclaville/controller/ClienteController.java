package br.com.senai.api.reciclaville.controller;

import br.com.senai.api.reciclaville.model.dtos.RequestClienteDTO;
import br.com.senai.api.reciclaville.model.dtos.ResponseClienteDTO;
import br.com.senai.api.reciclaville.model.entity.Cliente;
import br.com.senai.api.reciclaville.service.ClienteService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
     ClienteService clienteService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<ResponseClienteDTO>> list() {
        List<ResponseClienteDTO> clientes = this.clienteService.findAllClientes().stream()
                .map(cliente -> modelMapper.map(cliente, ResponseClienteDTO.class)).collect(Collectors.toList());
        return clientes.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseClienteDTO> findById(@PathVariable Long id) {
        Cliente cliente = clienteService.findClienteById(id);
        ResponseClienteDTO clienteDTO = modelMapper.map(cliente, ResponseClienteDTO.class);
        return ResponseEntity.ok(clienteDTO);
    }

    @PostMapping
    public ResponseEntity<ResponseClienteDTO> create(@RequestBody @Valid RequestClienteDTO clienteDTO) throws Exception {
        Cliente cliente = modelMapper.map(clienteDTO, Cliente.class);
        Cliente createdCliente = clienteService.create(cliente);
        ResponseClienteDTO createdClienteDTO = modelMapper.map(createdCliente, ResponseClienteDTO.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdClienteDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseClienteDTO> update(@PathVariable Long id, @RequestBody RequestClienteDTO clienteDTO) throws Exception {
        Cliente cliente = modelMapper.map(clienteDTO, Cliente.class);
        Cliente clienteUpdate = this.clienteService.update(id, cliente);
        ResponseClienteDTO clienteUpdateDTO = modelMapper.map(clienteUpdate, ResponseClienteDTO.class);
        return ResponseEntity.ok(clienteUpdateDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        this.clienteService.delete(id);
        return ResponseEntity.ok("Cliente deletado com sucesso");

    }
}