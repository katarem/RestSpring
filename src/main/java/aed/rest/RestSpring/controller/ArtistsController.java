package aed.rest.RestSpring.controller;

import aed.rest.RestSpring.exceptions.NotFoundException;
import aed.rest.RestSpring.model.ArtistsEntity;
import aed.rest.RestSpring.repository.ArtistsRepository;
import aed.rest.RestSpring.utils.StringResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/artists")
public class ArtistsController {

    @Autowired
    ArtistsRepository repository;

    @GetMapping("/all")
    public ResponseEntity<?> getAllArtists(@RequestParam(name = "name", required = false) String name){
        if(name == null) return new ResponseEntity<>(repository.findAll(),HttpStatus.OK);
        return new ResponseEntity<>(repository.findArtistsEntitiesByArtistContainsIgnoreCase(name),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getArtistById(@PathVariable("id") Integer id) throws NotFoundException {
        var artista = repository.findById(id);
        if(artista.isPresent()) return new ResponseEntity<>(artista.get(), HttpStatus.OK);
        throw new NotFoundException("No existe artista con ese id");
    }

    @PostMapping
    public String addArtista(@RequestBody ArtistsEntity artista) {
        if (repository.existsById(artista.getArtistid()))
            return "Ya existía ese artista";
        repository.save(artista);
        return "Artista agregado correctamente";
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateArtista(@PathVariable("id") Integer id, @RequestBody ArtistsEntity artista) {
        var artistaDBOptional = repository.findById(id);
        if(artistaDBOptional.isEmpty()) {
            repository.save(artista);
            return new ResponseEntity<>(new StringResponse("No existía ese artista, pero se creó"), HttpStatus.CREATED);
        }
        var artistaDB = artistaDBOptional.get();
        artistaDB.setArtist(artista.getArtist());
        artistaDB.setReviewid(artista.getReviewid());
        repository.save(artistaDB);
        return new ResponseEntity<>(new StringResponse("No existía ese artista, pero se creó"), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteArtista(@PathVariable("id") Integer id) throws NotFoundException {
        if (!repository.existsById(id))
            throw new NotFoundException("No existe artista con ese id");
        repository.delete(repository.getReferenceById(id));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
