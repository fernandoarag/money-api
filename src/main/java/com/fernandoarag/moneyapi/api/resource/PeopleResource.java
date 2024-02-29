package com.fernandoarag.moneyapi.api.resource;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fernandoarag.moneyapi.api.event.ResourceCreatedEvent;
import com.fernandoarag.moneyapi.api.models.PeopleModel;
import com.fernandoarag.moneyapi.api.repository.PeopleRepository;
import com.fernandoarag.moneyapi.api.repository.filter.PeopleFilter;
import com.fernandoarag.moneyapi.api.service.PeopleService;

@RestController
@RequestMapping("/")
public class PeopleResource {

    /*
     * 2xx -> Sucesso
     * 4xx -> Erro do cliente
     * 5xx -> Erro no serviço/servidor
     */

    private static final String PEOPLE_URI = "people";
    private static final String PERSON_URI = "person";

    @Autowired
    private PeopleRepository peopleRepository;

    @Autowired
    private PeopleService peopleService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping(PEOPLE_URI)
    @PreAuthorize("hasAuthority('ROLE_SEARCH_PERSON') and #oauth2.hasScope('read')")
    public Page<PeopleModel> searchPeople(PeopleFilter peopleFilter, Pageable pageable) {

        return peopleRepository.filter(peopleFilter, pageable);
    }

    @GetMapping(PERSON_URI + "/{id}")
    @PreAuthorize("hasAuthority('ROLE_SEARCH_PERSON') and #oauth2.hasScope('read')")
    public ResponseEntity<?> searchById(@PathVariable Long id) {

        PeopleModel person = peopleRepository.findOne(id);
        return person != null ? ResponseEntity.ok(person) : ResponseEntity.notFound().build();
    }

    @PostMapping(PERSON_URI)
    @PreAuthorize("hasAuthority('ROLE_CREATE_PERSON') and #oauth2.hasScope('write')")
    public ResponseEntity<PeopleModel> createPerson(@Valid @RequestBody PeopleModel peopleModel,
            HttpServletResponse response) {

        PeopleModel person = peopleRepository.save(peopleModel);
        publisher.publishEvent(new ResourceCreatedEvent(this, response, person.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(person);
    }

    @DeleteMapping(PERSON_URI + "/{id}")
    @PreAuthorize("hasAuthority('ROLE_DELETE_PERSON') and #oauth2.hasScope('write')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePerson(@PathVariable Long id) {

        peopleRepository.delete(id);
    }

    @PutMapping(PERSON_URI + "/{id}")
    @PreAuthorize("hasAuthority('ROLE_UPDATE_PERSON') and #oauth2.hasScope('write')")
    public ResponseEntity<PeopleModel> updatePerson(@PathVariable Long id,
            @Valid @RequestBody PeopleModel peopleModel) {

        PeopleModel person = peopleService.updatePerson(id, peopleModel);
        return ResponseEntity.ok(person);
    }

    @PutMapping(PERSON_URI + "/{id}/active")
    @PreAuthorize("hasAuthority('ROLE_UPDATE_PERSON') and #oauth2.hasScope('write')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarPropriedadeAtivo(@PathVariable Long id, @RequestBody Boolean active) {

        peopleService.updatePropertyActive(id, active);
    }
}
