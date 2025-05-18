package br.com.senai.api.reciclaville.service;

import br.com.senai.api.reciclaville.model.dtos.UpdateMaterialDTO;
import br.com.senai.api.reciclaville.model.entity.Cliente;
import br.com.senai.api.reciclaville.model.entity.Material;
import br.com.senai.api.reciclaville.model.exceptions.ResourceNotFoundException;
import br.com.senai.api.reciclaville.repository.ClienteRepository;
import br.com.senai.api.reciclaville.repository.MaterialRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

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
        if (material.getPercentagemCompensacao() == null || material.getPercentagemCompensacao().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("A percentagem de compensação deve ser maior que zero.");
        }

        Optional<Material> existingMaterial = materialRepository.findByNomeMaterial(material.getNomeMaterial());
        if (existingMaterial.isPresent()) {
            throw new IllegalArgumentException("Material já existe com o nome: " + material.getNomeMaterial());
        }

        Material newMaterial = new Material();
        newMaterial.setNomeMaterial(material.getNomeMaterial());
        newMaterial.setPercentagemCompensacao(material.getPercentagemCompensacao());



        return materialRepository.save(newMaterial);

    }

    public Material update(Long id, UpdateMaterialDTO materialUpdatedto) {
        Material existingMaterial = materialRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Material com Id: " + id + " não encontrado"));

        existingMaterial.setNomeMaterial(materialUpdatedto.getNomeMaterial());
        existingMaterial.setPercentagemCompensacao(materialUpdatedto.getPercentagemCompensacao());

        return materialRepository.save(existingMaterial);
    }

    public void delete(Long id) {
        Material material = findMaterialById(id);
        materialRepository.delete(material);
    }
}
