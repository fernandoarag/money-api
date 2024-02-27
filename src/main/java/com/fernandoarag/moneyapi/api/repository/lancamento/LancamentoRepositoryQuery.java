package com.fernandoarag.moneyapi.api.repository.lancamento;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fernandoarag.moneyapi.api.model.Lancamento;
import com.fernandoarag.moneyapi.api.repository.filter.LancamentoFilter;

public interface LancamentoRepositoryQuery {

    public Page<Lancamento> filtrar(LancamentoFilter lancamentoFilter, Pageable pageable);
}
