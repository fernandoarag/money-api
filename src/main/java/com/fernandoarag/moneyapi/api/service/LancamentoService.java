package com.fernandoarag.moneyapi.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fernandoarag.moneyapi.api.model.Lancamento;
import com.fernandoarag.moneyapi.api.model.Pessoa;
import com.fernandoarag.moneyapi.api.repository.LancamentoRepository;
import com.fernandoarag.moneyapi.api.repository.PessoaRepository;
import com.fernandoarag.moneyapi.api.service.exception.PessoaInexistenteOuInativaException;

@Service
public class LancamentoService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private LancamentoRepository lancamentoRepository;

    public Lancamento salvar(Lancamento lancamento) {
        Pessoa pessoa = pessoaRepository.findOne(lancamento.getPessoa().getCodigo());
        if (pessoa == null || pessoa.isInativo()) {
            throw new PessoaInexistenteOuInativaException();
        }

        return lancamentoRepository.save(lancamento);
    }
}
