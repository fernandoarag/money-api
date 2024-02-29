package com.fernandoarag.moneyapi.api.repository.people;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fernandoarag.moneyapi.api.models.PeopleModel;
import com.fernandoarag.moneyapi.api.repository.filter.PeopleFilter;

public interface PeopleRepositoryQuery {

    public Page<PeopleModel> filter(PeopleFilter peopleFilter, Pageable pageable);
}
