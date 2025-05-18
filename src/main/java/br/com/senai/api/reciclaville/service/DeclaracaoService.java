package br.com.senai.api.reciclaville.service;

import br.com.senai.api.reciclaville.model.dtos.RequestDeclaracaoDTO;
import br.com.senai.api.reciclaville.model.entity.Cliente;
import br.com.senai.api.reciclaville.model.entity.Declaracao;
import br.com.senai.api.reciclaville.model.entity.ItemDeclaracao;
import br.com.senai.api.reciclaville.model.entity.Material;
import br.com.senai.api.reciclaville.repository.ClienteRepository;
import br.com.senai.api.reciclaville.repository.DeclaracaoRepository;
import br.com.senai.api.reciclaville.repository.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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

    public List<Declaracao> findAll() {
        return declaracaoRepository.findAll();
    }

    public Declaracao findById(Long id) {
        return declaracaoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Declaração não encontrada."));
    }

    public Declaracao create(RequestDeclaracaoDTO request) {
        Cliente cliente = clienteRepository.findById(request.getClienteId())
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado."));

        if (request.getDataInicialPeriodo().isAfter(request.getDataFinalPeriodo())) {
            throw new IllegalArgumentException("A data inicial do período deve ser menor que a data final.");
        }

        Declaracao declaracao = new Declaracao();
        declaracao.setCliente(cliente);
        declaracao.setDataDeclaracao(LocalDate.now());
        declaracao.setDataInicialPeriodo(request.getDataInicialPeriodo());
        declaracao.setDataFinalPeriodo(request.getDataFinalPeriodo());

        List<ItemDeclaracao> itens = request.getItens().stream().map(itemDTO -> {
            Material material = materialRepository.findById(itemDTO.getMaterialId())
                    .orElseThrow(() -> new IllegalArgumentException("Material não encontrado."));

            if (itemDTO.getToneladasDeclaradas().compareTo(BigDecimal.ZERO) <= 0) {
                throw new IllegalArgumentException("As toneladas declaradas devem ser maiores que zero.");
            }

            ItemDeclaracao item = new ItemDeclaracao();
            item.setMaterial(material);
            item.setToneladasDeclaradas(itemDTO.getToneladasDeclaradas());
            item.setPercentualCompensacao(material.getPercentagemCompensacao());
            item.setToneladasCompensacao(itemDTO.getToneladasDeclaradas().multiply(material.getPercentagemCompensacao()).divide(BigDecimal.valueOf(100))
            );
            item.setDeclaracao(declaracao);
            return item;
        }).collect(Collectors.toList());

        declaracao.setItens(itens);

        BigDecimal totalMateriais = itens.stream()
                .map(ItemDeclaracao::getToneladasDeclaradas)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalCompensado = itens.stream()
                .map(ItemDeclaracao::getToneladasCompensacao)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        declaracao.setTotalMateriais(totalMateriais);
        declaracao.setTotalCompensado(totalCompensado);

        return declaracaoRepository.save(declaracao);
    }

    public Declaracao calcularTotais(Declaracao declaracao) {
        List<ItemDeclaracao> itens = declaracao.getItens();
        BigDecimal totalMateriais = BigDecimal.ZERO;
        BigDecimal totalCompensado = BigDecimal.ZERO;

        if (itens != null && !itens.isEmpty()) {
            for (ItemDeclaracao item : itens) {
                totalMateriais = totalMateriais.add(item.getToneladasDeclaradas()); // Supondo que getQuantidade() retorna a quantidade de material
                totalCompensado = totalCompensado.add(item.getToneladasCompensacao()); // Supondo que getValorCompensado() retorna o valor compensado
            }
        }

        declaracao.setTotalMateriais(totalMateriais);
        declaracao.setTotalCompensado(totalCompensado);

        return declaracao;
    }

    public Declaracao update(Long id, Declaracao declaracao) {
        Declaracao existingDeclaracao = findById(id);
        existingDeclaracao.setCliente(declaracao.getCliente());
        existingDeclaracao.setDataDeclaracao(declaracao.getDataDeclaracao());
        existingDeclaracao.setDataInicialPeriodo(declaracao.getDataInicialPeriodo());
        existingDeclaracao.setDataFinalPeriodo(declaracao.getDataFinalPeriodo());
        existingDeclaracao.setItens(declaracao.getItens());
        return declaracaoRepository.save(existingDeclaracao);
    }

    public void delete(Long id) {
        Declaracao declaracao = findById(id);
        declaracaoRepository.delete(declaracao);
    }
}
