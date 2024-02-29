package com.fernandoarag.moneyapi.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fernandoarag.moneyapi.api.models.PeopleModel;
import com.fernandoarag.moneyapi.api.repository.people.PeopleRepositoryQuery;

public interface PeopleRepository extends JpaRepository<PeopleModel, Long>, PeopleRepositoryQuery {

}
