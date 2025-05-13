package br.com.senai.api.reciclaville.controller;

import br.com.senai.api.reciclaville.model.dtos.RequestClienteDTO;
import br.com.senai.api.reciclaville.model.dtos.RequestMaterialDTO;
import br.com.senai.api.reciclaville.model.dtos.ResponseMaterialDTO;
import br.com.senai.api.reciclaville.model.dtos.ResponseMaterialDTO;
import br.com.senai.api.reciclaville.model.entity.Material;
import br.com.senai.api.reciclaville.model.entity.Material;
import br.com.senai.api.reciclaville.service.MaterialService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/materiais")
public class MaterialController {

    @Autowired
    MaterialService materialService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<ResponseMaterialDTO>> list() {
        List<ResponseMaterialDTO> materiais = this.materialService.findAllMaterial().stream()
                .map(material -> modelMapper.map(material, ResponseMaterialDTO.class)).collect(Collectors.toList());
        return materiais.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(materiais);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseMaterialDTO> findById(@PathVariable Long id) {
        Material material = materialService.findMaterialById(id);
        ResponseMaterialDTO materialDTO = modelMapper.map(material, ResponseMaterialDTO.class);
        return ResponseEntity.ok(materialDTO);
    }

    @PostMapping
    public ResponseEntity<ResponseMaterialDTO> create(@Valid @RequestBody RequestMaterialDTO materialDTO) throws Exception {
        Material material = modelMapper.map(materialDTO, Material.class);
        Material createdMaterial = materialService.create(material);
        ResponseMaterialDTO createdMaterialDTO = modelMapper.map(createdMaterial, ResponseMaterialDTO.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdMaterialDTO);
    }

    public ResponseEntity<ResponseMaterialDTO> update(@PathVariable Long id, @RequestBody RequestMaterialDTO materialDTO) throws Exception {
        Material material = modelMapper.map(materialDTO, Material.class);
        Material materialUpdate = this.materialService.update(id, material);
        ResponseMaterialDTO materialUpdateDTO = modelMapper.map(materialUpdate, ResponseMaterialDTO.class);
        return ResponseEntity.ok(materialUpdateDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        this.materialService.delete(id);
        return ResponseEntity.ok("Material deletado com sucesso");

    }
}
