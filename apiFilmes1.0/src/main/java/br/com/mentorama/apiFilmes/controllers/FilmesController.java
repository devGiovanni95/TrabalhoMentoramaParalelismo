package br.com.mentorama.apiFilmes.controllers;

import br.com.mentorama.apiFilmes.entities.Filmes;
import br.com.mentorama.apiFilmes.exceptions.UserNotFound;
import br.com.mentorama.apiFilmes.services.FilmesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@RequestMapping("/filmes")
@RestController
@Async
public class FilmesController {

    @Autowired
    private final FilmesService filmesService;

    public FilmesController(FilmesService filmesService){
        this.filmesService = filmesService;
    }

    @GetMapping
    public CompletableFuture<List<Filmes>> findAll(){
        System.out.println("Controller Thread: " + Thread.currentThread().getName());
        return this.filmesService.findAll();
    }

    @GetMapping("/{id}")
    public CompletableFuture<Filmes> findById(@PathVariable("id") Integer id){
        System.out.println("Controller Thread: " + Thread.currentThread().getName());
        return this.filmesService.findById(id)
                .thenApply(x -> {
                    try {
                        return x.orElseThrow(UserNotFound::new);
                    } catch (UserNotFound e) {
                        e.printStackTrace();
                    }
                    return null;
                });
    }

    @PostMapping
    public CompletableFuture<Filmes> add(@RequestBody final Filmes filmes) {
        System.out.println("Controller Thread: " + Thread.currentThread().getName());
        return this.filmesService.save(filmes);
    }

    @ResponseStatus(code = HttpStatus.OK)
    @PatchMapping(value = "/{id}")
    public CompletableFuture<Filmes> update(@RequestBody final Filmes filme, @PathVariable final int id) {
        CompletableFuture<Optional<Filmes>> movieToBeUpdated = filmesService.findById(id);

        CompletableFuture<Filmes> updateToBeUpdated = movieToBeUpdated.thenApply(movie -> {
            if (movie.isPresent()) {
                filme.setId(id);
                return movie.get();
            } else {
                return null;
            }
        });

        if (updateToBeUpdated.join() != null) {
            filme.setId(id);
            return filmesService.save(filme);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie not found");
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) {
        filmesService.delete(id);
    }


    //conferir documentação
    //http://localhost:8080/v3/api-docs
    //conferir documentacao com interface grafica
    //http://localhost:8080/swagger-ui.html
}

