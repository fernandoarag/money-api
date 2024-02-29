package com.fernandoarag.moneyapi.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fernandoarag.moneyapi.api.models.CategoriesModel;

public interface CategoriesRepository extends JpaRepository<CategoriesModel, Long> {

}
