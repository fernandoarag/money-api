package com.fernandoarag.moneyapi.api.resource;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fernandoarag.moneyapi.api.event.ResourceCreatedEvent;
import com.fernandoarag.moneyapi.api.models.PermissionsModel;
import com.fernandoarag.moneyapi.api.models.UsersModel;
import com.fernandoarag.moneyapi.api.repository.UsersRepository;
import com.fernandoarag.moneyapi.api.service.exception.NonexistentOrInactiveUserException;

@RestController
@RequestMapping("/")
public class UsersResource {

    /*
     * 2xx -> Sucesso
     * 4xx -> Erro do cliente
     * 5xx -> Erro no serviço/servidor
     */

    private static final String USERS_URI = "users";
    private static final String USER_URI = "user";

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping(USERS_URI)
    @PreAuthorize("hasAuthority('ROLE_SEARCH_USER') and #oauth2.hasScope('read')")
    public List<UsersModel> listUsers() {
        List<UsersModel> users = usersRepository.findAll();

        users.stream().allMatch(u -> u.isActive());

        return users;
    }

    @GetMapping(USER_URI + "/{id}")
    @PreAuthorize("hasAuthority('ROLE_SEARCH_USER') and #oauth2.hasScope('read')")
    public ResponseEntity<?> findUserById(@PathVariable Long id) {
        UsersModel user = usersRepository.findOne(id);

        if (user == null || user.isInactive()) {
            throw new NonexistentOrInactiveUserException();
        }

        return ResponseEntity.ok(user);
    }

    @GetMapping(USER_URI + "/permissions")
    @PreAuthorize("hasAuthority('ROLE_SEARCH_USER') and #oauth2.hasScope('read')")
    public ResponseEntity<List<PermissionsModel>> findUserPermissionsById(HttpServletRequest request) {
        // Capturando email do TOKEN
        Object email = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        UsersModel user = usersRepository.findByEmail(email.toString()).get();

        if (user == null || user.isInactive()) {
            throw new NonexistentOrInactiveUserException();
        }

        return ResponseEntity.ok(user.getPermissions());
    }

    @PostMapping(USER_URI)
    @PreAuthorize("hasAuthority('ROLE_CREATE_USER') and #oauth2.hasScope('write')")
    public ResponseEntity<UsersModel> createUser(@Valid @RequestBody UsersModel usersModel,
            HttpServletResponse response) {
        UsersModel user = usersRepository.save(usersModel);

        publisher.publishEvent(new ResourceCreatedEvent(this, response, user.getId()));

        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @DeleteMapping(USER_URI + "/{id}")
    @PreAuthorize("hasAuthority('ROLE_DELETE_USER') and #oauth2.hasScope('write')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void inactiveUser(@PathVariable Long id) {
        UsersModel user = usersRepository.findOne(id);

        if (user == null || user.isInactive()) {
            throw new NonexistentOrInactiveUserException();
        }

        user.setActive(false);
        usersRepository.save(user);
    }
}
