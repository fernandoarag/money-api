package com.fernandoarag.moneyapi.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fernandoarag.moneyapi.api.models.UsersModel;

public interface UsersRepository extends JpaRepository<UsersModel, Long> {

    public Optional<UsersModel> findByEmail(String email);

}
