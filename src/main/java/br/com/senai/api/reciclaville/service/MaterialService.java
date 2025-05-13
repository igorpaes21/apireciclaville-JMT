package br.com.senai.api.reciclaville.service;

import br.com.senai.api.reciclaville.model.entity.Material;
import br.com.senai.api.reciclaville.model.exceptions.ResourceNotFoundException;
import br.com.senai.api.reciclaville.repository.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialService {

    @Autowired
    MaterialRepository materialRepository;

    public List<Material> findAllMaterial() {
        return materialRepository.findAll();
    }

    public Material findMaterialById(Long id) {
        return materialRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Material com Id: " + id + " não encontrado"));
    }

    public Material create(Material material) {
        return materialRepository.save(material);
    }

    public Material update(Long id, Material materialUpdate) {
        Material existingMaterial = findMaterialById(id);
        existingMaterial.setNomeMaterial(materialUpdate.getNomeMaterial());
        existingMaterial.setPercentagemCompensacao(materialUpdate.getPercentagemCompensacao());
        return materialRepository.save(existingMaterial);
    }

    public void delete(Long id) {
        Material material = findMaterialById(id);
        materialRepository.delete(material);
    }
}
