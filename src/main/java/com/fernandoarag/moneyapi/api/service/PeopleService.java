package com.fernandoarag.moneyapi.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.fernandoarag.moneyapi.api.models.PeopleModel;
import com.fernandoarag.moneyapi.api.repository.PeopleRepository;

@Service
public class PeopleService {

    @Autowired
    private PeopleRepository peopleRepository;

    public PeopleModel updatePerson(Long id, PeopleModel peopleModel) {

        PeopleModel person = searchPersonById(id);
        BeanUtils.copyProperties(peopleModel, person, "id");
        return peopleRepository.save(person);
    }

    public void updatePropertyActive(Long id, Boolean active) {

        PeopleModel person = searchPersonById(id);
        person.setActive(active);
        peopleRepository.save(person);
    }

    private PeopleModel searchPersonById(Long id) {
        PeopleModel person = peopleRepository.findOne(id);
        if (person == null) {
            throw new EmptyResultDataAccessException(1);
        }
        return person;
    }
}
