package aed.rest.RestSpring.controller;

import aed.rest.RestSpring.model.ArtistsEntity;
import aed.rest.RestSpring.model.GenresEntity;
import aed.rest.RestSpring.repository.ArtistsRepository;
import aed.rest.RestSpring.repository.GenresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/genres")
public class GenresController {
    
    @Autowired
    GenresRepository repository;

    @GetMapping("/all")
    public List<GenresEntity> getAllGenres() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<GenresEntity> getGenreById(@PathVariable("id") Integer id) {
        return repository.findById(id);
    }

    @PostMapping
    public String addGenre(@RequestBody GenresEntity genre) {
        if (repository.existsById(genre.getGenreid()))
            return "Ya existía ese género";
        repository.save(genre);
        return "Género agregado correctamente";
    }

    @PutMapping("/{id}")
    public String updateGenre(@PathVariable("id") Integer id, @RequestBody GenresEntity genre) {
        if (!repository.existsById(id))
            return "No se modificó, no existía ese género";
        var genreDB = repository.findById(id).get();
        genreDB.setReview(genre.getReview());
        genreDB.setGenre(genre.getGenre());
        repository.save(genreDB);
        return "Género modificado correctamente";
    }

    @DeleteMapping("/{id}")
    public String deleteGenre(@PathVariable("id") Integer id) {
        if (!repository.existsById(id))
            return "No se eliminó ese género, no existe";
        repository.delete(repository.getReferenceById(id));
        return "Género eliminado correctamente";
    }

}
