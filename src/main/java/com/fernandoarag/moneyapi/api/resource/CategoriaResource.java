package com.fernandoarag.moneyapi.api.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fernandoarag.moneyapi.api.event.RecursoCriadoEvent;
import com.fernandoarag.moneyapi.api.model.Categoria;
import com.fernandoarag.moneyapi.api.repository.CategoriaRepository;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

    /*
     * 2xx -> Sucesso
     * 4xx -> Erro do cliente
     * 5xx -> Erro no serviço/servidor
     */
    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @CrossOrigin
    @GetMapping
    public List<Categoria> listar() {
        return categoriaRepository.findAll();
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<?> buscarPeloCodigo(@PathVariable Long codigo) {
        Categoria categoria = categoriaRepository.findOne(codigo);

        if (categoria != null) {
            return ResponseEntity.ok(categoria);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Categoria não encontrada!");
    }

    @PostMapping
    public ResponseEntity<Categoria> criar(@Valid @RequestBody Categoria categoria, HttpServletResponse response) {
        Categoria categoriaSalva = categoriaRepository.save(categoria);

        publisher.publishEvent(new RecursoCriadoEvent(this, response, categoriaSalva.getCodigo()));

        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSalva);
    }

    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long codigo) {
        categoriaRepository.delete(codigo);
    }
}
