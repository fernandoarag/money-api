package com.fernandoarag.moneyapi.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fernandoarag.moneyapi.api.models.ReleasesModel;
import com.fernandoarag.moneyapi.api.models.CategoriesModel;
import com.fernandoarag.moneyapi.api.models.PeopleModel;
import com.fernandoarag.moneyapi.api.repository.ReleasesRepository;
import com.fernandoarag.moneyapi.api.repository.CategoriesRepository;
import com.fernandoarag.moneyapi.api.repository.PeopleRepository;
import com.fernandoarag.moneyapi.api.service.exception.NonexistentOrInactiveCategoryException;
import com.fernandoarag.moneyapi.api.service.exception.NonexistentOrInactivePersonException;

@Service
public class ReleasesService {

    @Autowired
    private PeopleRepository peopleRepository;

    @Autowired
    private CategoriesRepository categoriesRepository;

    @Autowired
    private ReleasesRepository releasesRepository;

    public ReleasesModel createRelease(ReleasesModel releasesModel) {
        PeopleModel person = peopleRepository.findOne(releasesModel.getPerson().getId());
        isActiveAndExistThePerson(person);

        CategoriesModel category = categoriesRepository.findOne(releasesModel.getCategory().getId());
        isActiveAndExistTheCategory(category);

        return releasesRepository.save(releasesModel);
    }

    private void isActiveAndExistThePerson(PeopleModel person) {
        if (person == null || person.isInactive()) {
            throw new NonexistentOrInactivePersonException();
        }
    }

    private void isActiveAndExistTheCategory(CategoriesModel category) {
        if (category == null) {
            throw new NonexistentOrInactiveCategoryException();
        }
    }
}
