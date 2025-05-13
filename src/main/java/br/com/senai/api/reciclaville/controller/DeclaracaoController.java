package br.com.senai.api.reciclaville.controller;

import br.com.senai.api.reciclaville.model.dtos.RequestDeclaracaoDTO;
import br.com.senai.api.reciclaville.model.dtos.ResponseDeclaracaoDTO;
import br.com.senai.api.reciclaville.model.entity.Declaracao;
import br.com.senai.api.reciclaville.model.entity.Declaracao;
import br.com.senai.api.reciclaville.service.DeclaracaoService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("declaracoes")
public class DeclaracaoController {

    @Autowired
    DeclaracaoService declaracaoService;


    @Autowired
    ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<ResponseDeclaracaoDTO>> list() {
        List<ResponseDeclaracaoDTO> declaracoes = this.declaracaoService.findAllDeclaracoes().stream()
                .map(declaracao -> modelMapper.map(declaracao, ResponseDeclaracaoDTO.class)).collect(Collectors.toList());
        return declaracoes.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(declaracoes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDeclaracaoDTO> findById(@PathVariable Long id) {
        Declaracao declaracao = declaracaoService.findDeclaracaoById(id);
        ResponseDeclaracaoDTO declaracaoDTO = modelMapper.map(declaracao, ResponseDeclaracaoDTO.class);
        return ResponseEntity.ok(declaracaoDTO);
    }



    @PostMapping
    public ResponseEntity<ResponseDeclaracaoDTO> create(@RequestBody @Valid RequestDeclaracaoDTO declaracaoDTO) throws Exception {
        Declaracao declaracao = modelMapper.map(declaracaoDTO, Declaracao.class);
        Declaracao createdDeclaracao  = declaracaoService.create(declaracao);
        ResponseDeclaracaoDTO createdDeclaracaoDTO = modelMapper.map(createdDeclaracao, ResponseDeclaracaoDTO.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdDeclaracaoDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDeclaracaoDTO> update(@PathVariable Long id, @RequestBody RequestDeclaracaoDTO declaracaoDTO) throws Exception {
        Declaracao declaracao = modelMapper.map(declaracaoDTO, Declaracao.class);
        Declaracao declaracaoUpdate = this.declaracaoService.update(id, declaracao);
        ResponseDeclaracaoDTO declaracaoUpdateDTO = modelMapper.map(declaracaoUpdate, ResponseDeclaracaoDTO.class);
        return ResponseEntity.ok(declaracaoUpdateDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        this.declaracaoService.delete(id);
        return ResponseEntity.ok("Cliente deletado com sucesso");
    }
}
