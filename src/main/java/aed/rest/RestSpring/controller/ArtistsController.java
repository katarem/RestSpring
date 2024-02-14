package aed.rest.RestSpring.controller;

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
    public ResponseEntity<?> getAllArtists(@RequestParam(name = "name", required = false) String name,
                                           @RequestParam(name = "index",required = false) Integer index,
                                           @RequestParam(name = "size",required = false) Integer size){
        if(index == null) index = 0;
        if(size == null) size = 10;
        Pageable pageable = PageRequest.of(index,size);
        if(name == null) return new ResponseEntity<>(repository.findAll(pageable),HttpStatus.OK);
        return new ResponseEntity<>(repository.findArtistsEntitiesByArtistContainsIgnoreCase(name),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getArtistById(@PathVariable("id") Integer id) {
        if(!repository.existsById(id)) return new ResponseEntity<>(new StringResponse("No existe artista con ese id."),HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(repository.getReferenceById(id),HttpStatus.OK);
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
    public ResponseEntity<?> deleteArtista(@PathVariable("id") Integer id) {
        if (!repository.existsById(id))
            return new ResponseEntity<>(new StringResponse("No se eliminó artista: no existe"),HttpStatus.NOT_FOUND);
        repository.delete(repository.getReferenceById(id));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
