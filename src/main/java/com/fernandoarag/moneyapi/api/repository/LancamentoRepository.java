package com.fernandoarag.moneyapi.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fernandoarag.moneyapi.api.model.Lancamento;
import com.fernandoarag.moneyapi.api.repository.lancamento.LancamentoRepositoryQuery;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long>, LancamentoRepositoryQuery {

}
