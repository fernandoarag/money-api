package com.fernandoarag.moneyapi.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fernandoarag.moneyapi.api.models.ReleasesModel;
import com.fernandoarag.moneyapi.api.repository.releases.ReleasesRepositoryQuery;

public interface ReleasesRepository extends JpaRepository<ReleasesModel, Long>, ReleasesRepositoryQuery {

}
