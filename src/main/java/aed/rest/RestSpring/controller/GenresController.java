package aed.rest.RestSpring.controller;

import aed.rest.RestSpring.model.GenresEntity;
import aed.rest.RestSpring.repository.GenresRepository;
import aed.rest.RestSpring.utils.StringResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/genres")
public class GenresController {
    
    @Autowired
    GenresRepository repository;

    @GetMapping("/all")
    public ResponseEntity<?> getAllGenres(@RequestParam(value = "index",required = false) Integer index, @RequestParam(value = "size", required = false) Integer size) {
        if(index == null) index = 0;
        if(size == null) size = 10;
        Pageable pageable = PageRequest.of(index,size);
        return new ResponseEntity<>(repository.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getGenreById(@PathVariable("id") Integer id) {
        var opt = repository.findById(id);
        return opt.isPresent() ? new ResponseEntity<>(opt.get(),HttpStatus.OK)
                               : new ResponseEntity<>(new StringResponse("No existía dicho género"),HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> addGenre(@RequestBody GenresEntity genre) {
        if (repository.existsById(genre.getGenreid()))
            return new ResponseEntity<>(new StringResponse("Ya existía esa review"),HttpStatus.CONFLICT);
        repository.save(genre);
        return new ResponseEntity<>(new StringResponse("Review agregada correctamente."),HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateGenre(@PathVariable("id") Integer id, @RequestBody GenresEntity genre) {
        var genreDBOptional = repository.findById(id);
        if(genreDBOptional.isEmpty()) {
            repository.save(genre);
            return new ResponseEntity<>(new StringResponse("No existía ese género, pero se creó"), HttpStatus.CREATED);
        }

        var genreDB = genreDBOptional.get();
        genreDB.setReview(genre.getReview());
        genreDB.setGenre(genre.getGenre());
        repository.save(genreDB);

        return new ResponseEntity<>(new StringResponse("Género actualizado correctamente."),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGenre(@PathVariable("id") Integer id) {
        if (!repository.existsById(id))
            return new ResponseEntity<>(new StringResponse("No existía ese género"),HttpStatus.NOT_FOUND);
        repository.delete(repository.getReferenceById(id));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

}
