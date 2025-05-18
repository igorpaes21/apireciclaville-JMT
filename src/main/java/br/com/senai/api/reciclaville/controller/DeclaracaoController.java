package br.com.senai.api.reciclaville.controller;

import br.com.senai.api.reciclaville.model.dtos.RequestDeclaracaoDTO;
import br.com.senai.api.reciclaville.model.dtos.ResponseDeclaracaoDTO;
import br.com.senai.api.reciclaville.model.entity.*;
import br.com.senai.api.reciclaville.model.entity.Declaracao;
import br.com.senai.api.reciclaville.model.exceptions.ResourceNotFoundException;
import br.com.senai.api.reciclaville.repository.ClienteRepository;
import br.com.senai.api.reciclaville.repository.DeclaracaoRepository;
import br.com.senai.api.reciclaville.repository.MaterialRepository;
import br.com.senai.api.reciclaville.service.DeclaracaoService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/declaracoes")
public class DeclaracaoController {

    @Autowired
    DeclaracaoService declaracaoService;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    MaterialRepository materialRepository;

    @Autowired
    DeclaracaoRepository declaracaoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<ResponseDeclaracaoDTO>> listAll() {
        List<ResponseDeclaracaoDTO> declaracoes = declaracaoService.findAll().stream()
                .map(declaracao -> modelMapper.map(declaracao, ResponseDeclaracaoDTO.class))
                .collect(Collectors.toList());
        return declaracoes.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(declaracoes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDeclaracaoDTO> findDeclaracaoById(@PathVariable Long id) {
        Declaracao declaracao = declaracaoService.findById(id);
        ResponseDeclaracaoDTO response = modelMapper.map(declaracao, ResponseDeclaracaoDTO.class);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ResponseDeclaracaoDTO> create(@RequestBody RequestDeclaracaoDTO declaracaoDTO) {
        Declaracao createdDeclaracao = declaracaoService.create(declaracaoDTO);
        ResponseDeclaracaoDTO response = modelMapper.map(createdDeclaracao, ResponseDeclaracaoDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDeclaracaoDTO> update(@PathVariable Long id, @RequestBody @Valid RequestDeclaracaoDTO requestDeclaracaoDTO) {
        Declaracao declaracao = modelMapper.map(requestDeclaracaoDTO, Declaracao.class);
        Declaracao updatedDeclaracao = declaracaoService.update(id, declaracao);
        ResponseDeclaracaoDTO response = modelMapper.map(updatedDeclaracao, ResponseDeclaracaoDTO.class);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        declaracaoService.delete(id);
        return ResponseEntity.ok("Declaração com ID " + id + " deletada com sucesso.");
    }
}

