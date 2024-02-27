package com.fernandoarag.moneyapi.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fernandoarag.moneyapi.api.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}
