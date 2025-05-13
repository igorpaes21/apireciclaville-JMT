package br.com.senai.api.reciclaville.controller;

import br.com.senai.api.reciclaville.model.dtos.RequestDeclaracaoDTO;
import br.com.senai.api.reciclaville.model.dtos.RequestItemDeclaracaoDTO;
import br.com.senai.api.reciclaville.model.dtos.ResponseItemDeclaracaoDTO;
import br.com.senai.api.reciclaville.model.entity.ItemDeclaracao;
import br.com.senai.api.reciclaville.service.ItemDeclaracaoService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping
public class ItemDeclaracaoController {

    @Autowired
    ItemDeclaracaoService itemDeclaracaoService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<ResponseItemDeclaracaoDTO>> list() {
        List<ResponseItemDeclaracaoDTO> itensDeclaracao = this.itemDeclaracaoService.findAllItensDeclaracao().stream()
                .map(itemDeclaracao -> modelMapper.map(itemDeclaracao, ResponseItemDeclaracaoDTO.class)).collect(Collectors.toList());
        return itensDeclaracao.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(itensDeclaracao);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseItemDeclaracaoDTO> findById(@PathVariable Long id) {
        ItemDeclaracao itemDeclaracao = itemDeclaracaoService.findItemDeclaracaoById(id);
        ResponseItemDeclaracaoDTO itemDeclaracaoDTO = modelMapper.map(itemDeclaracao, ResponseItemDeclaracaoDTO.class);
        return ResponseEntity.ok(itemDeclaracaoDTO);
    }

    @PostMapping
    public ResponseEntity<ResponseItemDeclaracaoDTO> create(@RequestBody @Valid RequestDeclaracaoDTO itemDeclaracaoDTO) throws Exception {
        ItemDeclaracao itemDeclaracao = modelMapper.map(itemDeclaracaoDTO, ItemDeclaracao.class);
        ItemDeclaracao createdItemDeclaracao = itemDeclaracaoService.create(itemDeclaracao);
        ResponseItemDeclaracaoDTO createdItemDeclaracaoDTO = modelMapper.map(createdItemDeclaracao, ResponseItemDeclaracaoDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdItemDeclaracaoDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseItemDeclaracaoDTO> update(@PathVariable Long id, @RequestBody RequestItemDeclaracaoDTO itemDeclaracaoDTO) throws Exception {
        ItemDeclaracao itemDeclaracao = modelMapper.map(itemDeclaracaoDTO, ItemDeclaracao.class);
        ItemDeclaracao itemDeclaracaoUpdate = this.itemDeclaracaoService.update(id, itemDeclaracao);
        ResponseItemDeclaracaoDTO itemDeclaracaoUpdateDTO = modelMapper.map(itemDeclaracaoUpdate, ResponseItemDeclaracaoDTO.class);
        return ResponseEntity.ok(itemDeclaracaoUpdateDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        this.itemDeclaracaoService.delete(id);
        return ResponseEntity.ok("Item de declaração com Id: " + id + " deletado com sucesso");
    }
}
