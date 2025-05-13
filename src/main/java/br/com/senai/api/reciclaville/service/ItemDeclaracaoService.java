package br.com.senai.api.reciclaville.service;

import br.com.senai.api.reciclaville.model.entity.Cliente;
import br.com.senai.api.reciclaville.model.entity.ItemDeclaracao;
import br.com.senai.api.reciclaville.model.exceptions.ResourceNotFoundException;
import br.com.senai.api.reciclaville.repository.ClienteRepository;
import br.com.senai.api.reciclaville.repository.ItemDeclaracaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemDeclaracaoService {

    @Autowired
    ItemDeclaracaoRepository itemDeclaracaoRepository;



    public List<ItemDeclaracao> findAllItensDeclaracao() {
        return itemDeclaracaoRepository.findAll();
    }

    public ItemDeclaracao findItemDeclaracaoById(Long id) {
        return itemDeclaracaoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Item com Id: " + id + " não encontrado"));
    }

    public ItemDeclaracao create(ItemDeclaracao itemDeclaracao) {
        return itemDeclaracaoRepository.save(itemDeclaracao);
    }

    public ItemDeclaracao update(Long id, ItemDeclaracao itemDeclaracaoUpdate) {
        ItemDeclaracao existingItemDeclaracao = findItemDeclaracaoById(id);
        existingItemDeclaracao.setDeclaracao(itemDeclaracaoUpdate.getDeclaracao());
        existingItemDeclaracao.setMaterial(itemDeclaracaoUpdate.getMaterial());
        existingItemDeclaracao.setToneladasDeclaradas(itemDeclaracaoUpdate.getToneladasDeclaradas());
        existingItemDeclaracao.setToneladasCompensacao(itemDeclaracaoUpdate.getToneladasCompensacao());
        existingItemDeclaracao.setPercentualCompensacao(itemDeclaracaoUpdate.getPercentualCompensacao());
        return itemDeclaracaoRepository.save(existingItemDeclaracao);
    }

    public void delete(Long id) {
        ItemDeclaracao itemDeclaracao = findItemDeclaracaoById(id);
        itemDeclaracaoRepository.delete(itemDeclaracao);
    }


}
