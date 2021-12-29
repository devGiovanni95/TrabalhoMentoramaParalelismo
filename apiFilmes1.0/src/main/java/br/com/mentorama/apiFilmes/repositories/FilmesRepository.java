package br.com.mentorama.apiFilmes.repositories;

import br.com.mentorama.apiFilmes.entities.Filmes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Async("executorService")
@Repository
public interface FilmesRepository extends JpaRepository<Filmes, Integer> {


    CompletableFuture<Optional<Filmes>> findOneById(final Integer id);

    CompletableFuture<List<Filmes>> findAllBy();

    void deleteById(final Integer id);

}
