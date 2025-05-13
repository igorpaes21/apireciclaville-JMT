package br.com.senai.api.reciclaville.service;

import br.com.senai.api.reciclaville.model.entity.Cliente;
import br.com.senai.api.reciclaville.model.entity.Declaracao;
import br.com.senai.api.reciclaville.model.entity.ItemDeclaracao;
import br.com.senai.api.reciclaville.model.entity.Material;
import br.com.senai.api.reciclaville.repository.ClienteRepository;
import br.com.senai.api.reciclaville.repository.DeclaracaoRepository;
import br.com.senai.api.reciclaville.repository.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeclaracaoService {

    @Autowired
    DeclaracaoRepository declaracaoRepository;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    MaterialRepository materialRepository;

    public List<Declaracao> findAllDeclaracoes() {
        return declaracaoRepository.findAll();
    }

    public Declaracao findDeclaracaoById(Long id) {
        return declaracaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Declaração não encontrada"));
    }

    public Declaracao create(Declaracao declaracaoDTO){
        Cliente cliente = clienteRepository.findById(declaracaoDTO.getCliente().getId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        Declaracao declaracao = new Declaracao();
        declaracao.setCliente(cliente);
        declaracao.setDataDeclaracao(LocalDate.now());
        declaracao.setDataInicialPeriodo(declaracaoDTO.getDataInicialPeriodo());
        declaracao.setDataFinalPeriodo(declaracaoDTO.getDataFinalPeriodo());

        List<ItemDeclaracao> itens = declaracaoDTO.getItens().stream().map(itemDTO -> {
            Material material = materialRepository.findById(itemDTO.getMaterial().getId())
                    .orElseThrow(() -> new RuntimeException("Material não encontrado"));

            ItemDeclaracao itemDeclaracao = new ItemDeclaracao();
            itemDeclaracao.setMaterial(material);
            itemDeclaracao.setToneladasDeclaradas(itemDTO.getToneladasDeclaradas());
            itemDeclaracao.setPercentualCompensacao(material.getPercentagemCompensacao());
            itemDeclaracao.setToneladasCompensacao(itemDeclaracao.getToneladasDeclaradas() * material.getPercentagemCompensacao() / 100);
            itemDeclaracao.setDeclaracao(declaracao);
            return itemDeclaracao;
        }).collect(Collectors.toList());

        declaracao.setItens(itens);
        declaracao.setTotalMateriais(itens.stream().mapToDouble(ItemDeclaracao::getToneladasDeclaradas).sum());
        declaracao.setTotalCompensado(itens.stream().mapToDouble(ItemDeclaracao::getToneladasCompensacao).sum());

        return declaracaoRepository.save(declaracao);
    }

    public Declaracao update(Long id, Declaracao declaracaoDTO) {
        Declaracao existingDeclaracao = findDeclaracaoById(id);
        existingDeclaracao.setDataInicialPeriodo(declaracaoDTO.getDataInicialPeriodo());
        existingDeclaracao.setDataFinalPeriodo(declaracaoDTO.getDataFinalPeriodo());

        List<ItemDeclaracao> itens = declaracaoDTO.getItens().stream().map(itemDTO -> {
            Material material = materialRepository.findById(itemDTO.getMaterial().getId())
                    .orElseThrow(() -> new RuntimeException("Material não encontrado"));

            ItemDeclaracao itemDeclaracao = new ItemDeclaracao();
            itemDeclaracao.setMaterial(material);
            itemDeclaracao.setToneladasDeclaradas(itemDTO.getToneladasDeclaradas());
            itemDeclaracao.setPercentualCompensacao(material.getPercentagemCompensacao());
            itemDeclaracao.setToneladasCompensacao(itemDeclaracao.getToneladasDeclaradas() * material.getPercentagemCompensacao() / 100);
            itemDeclaracao.setDeclaracao(existingDeclaracao);
            return itemDeclaracao;
        }).collect(Collectors.toList());

        existingDeclaracao.setItens(itens);
        existingDeclaracao.setTotalMateriais(itens.stream().mapToDouble(ItemDeclaracao::getToneladasDeclaradas).sum());
        existingDeclaracao.setTotalCompensado(itens.stream().mapToDouble(ItemDeclaracao::getToneladasCompensacao).sum());

        return declaracaoRepository.save(existingDeclaracao);
    }

    public void delete(Long id) {
        Declaracao declaracao = findDeclaracaoById(id);
        declaracaoRepository.deleteById(id);
    }
}
