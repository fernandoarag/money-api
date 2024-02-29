package com.fernandoarag.moneyapi.api.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fernandoarag.moneyapi.api.event.ResourceCreatedEvent;
import com.fernandoarag.moneyapi.api.models.CategoriesModel;
import com.fernandoarag.moneyapi.api.repository.CategoriesRepository;

@RestController
@RequestMapping("/")
public class CategoriesResource {

    /*
     * 2xx -> Sucesso
     * 4xx -> Erro do cliente
     * 5xx -> Erro no serviço/servidor
     */

    private static final String CATEGORIES_URI = "categories";
    private static final String CATEGORY_URI = "category";

    @Autowired
    private CategoriesRepository categoriesRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping(CATEGORIES_URI)
    @PreAuthorize("hasAuthority('ROLE_SEARCH_CATEGORY') and #oauth2.hasScope('read')")
    public List<CategoriesModel> listCategories() {
        return categoriesRepository.findAll();
    }

    @GetMapping(CATEGORY_URI + "/{id}")
    @PreAuthorize("hasAuthority('ROLE_SEARCH_CATEGORY') and #oauth2.hasScope('read')")
    public ResponseEntity<?> findCategoryById(@PathVariable Long id) {
        CategoriesModel category = categoriesRepository.findOne(id);

        if (category != null) {
            return ResponseEntity.ok(category);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Categoria não encontrada!");
    }

    @PostMapping(CATEGORY_URI)
    @PreAuthorize("hasAuthority('ROLE_CREATE_CATEGORY') and #oauth2.hasScope('write')")
    public ResponseEntity<CategoriesModel> createCategory(@Valid @RequestBody CategoriesModel categoriesModel,
            HttpServletResponse response) {
        CategoriesModel category = categoriesRepository.save(categoriesModel);

        publisher.publishEvent(new ResourceCreatedEvent(this, response, category.getId()));

        return ResponseEntity.status(HttpStatus.CREATED).body(category);
    }

    @DeleteMapping(CATEGORY_URI + "/{id}")
    @PreAuthorize("hasAuthority('ROLE_DELETE_CATEGORY') and #oauth2.hasScope('write')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable Long id) {
        categoriesRepository.delete(id);
    }
}
