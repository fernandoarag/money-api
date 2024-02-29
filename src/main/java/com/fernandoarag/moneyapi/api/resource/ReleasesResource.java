package com.fernandoarag.moneyapi.api.resource;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fernandoarag.moneyapi.api.event.ResourceCreatedEvent;
import com.fernandoarag.moneyapi.api.exceptionhandler.MoneyapiExceptionHandler.ReponseError;
import com.fernandoarag.moneyapi.api.models.ReleasesModel;
import com.fernandoarag.moneyapi.api.repository.ReleasesRepository;
import com.fernandoarag.moneyapi.api.repository.filter.ReleasesFilter;
import com.fernandoarag.moneyapi.api.service.ReleasesService;
import com.fernandoarag.moneyapi.api.service.exception.NonexistentOrInactivePersonException;

@RestController
@RequestMapping("/")
public class ReleasesResource {
    /*
     * 2xx -> Sucesso
     * 4xx -> Erro do cliente
     * 5xx -> Erro no serviço/servidor
     */

    private static final String RELEASES_URI = "releases";
    private static final String RELEASE_URI = "release";

    @Autowired
    private ReleasesRepository releasesRepository;

    @Autowired
    private ReleasesService releasesService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private MessageSource messageSource;

    @GetMapping(RELEASES_URI)
    @PreAuthorize("hasAuthority('ROLE_SEARCH_RELEASE') and #oauth2.hasScope('read')")
    public Page<ReleasesModel> searchReleases(ReleasesFilter releasesFilter, Pageable pageable) {

        return releasesRepository.filter(releasesFilter, pageable);
    }

    @GetMapping(RELEASE_URI + "/{id}")
    @PreAuthorize("hasAuthority('ROLE_SEARCH_RELEASE') and #oauth2.hasScope('read')")
    public ResponseEntity<ReleasesModel> findReleasyById(@PathVariable Long id) {

        ReleasesModel release = releasesRepository.findOne(id);
        return release != null ? ResponseEntity.ok(release) : ResponseEntity.notFound().build();
    }

    @PostMapping(RELEASE_URI)
    @PreAuthorize("hasAuthority('ROLE_CREATE_RELEASE') and #oauth2.hasScope('write')")
    public ResponseEntity<ReleasesModel> createRelease(@Valid @RequestBody ReleasesModel releasesModel,
            HttpServletResponse response) {

        ReleasesModel release = releasesService.createRelease(releasesModel);
        publisher.publishEvent(new ResourceCreatedEvent(this, response, release.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(release);
    }

    @DeleteMapping(RELEASE_URI + "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ROLE_DELETE_RELEASE') and #oauth2.hasScope('write')")
    public void deleteRelease(@PathVariable Long id) {

        releasesRepository.delete(id);
    }

}
