package aed.rest.RestSpring.controller;

import aed.rest.RestSpring.model.ArtistsEntity;
import aed.rest.RestSpring.repository.ArtistsRepository;
import aed.rest.RestSpring.utils.StringResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
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
    public String updateArtista(@PathVariable("id") Integer id, @RequestBody ArtistsEntity artista) {
        if (!repository.existsById(id))
            return "No se modificó, no existía ese artista";
        var artistaDB = repository.findById(id).get();
        artistaDB.setArtist(artista.getArtist());
        artistaDB.setReviewid(artista.getReviewid());
        repository.save(artistaDB);
        return "Artista modificado correctamente";
    }

    @DeleteMapping("/{id}")
    public String deleteArtista(@PathVariable("id") Integer id) {
        if (!repository.existsById(id))
            return "No se eliminó artista: no existe";
        repository.delete(repository.getReferenceById(id));
        return "Eliminado correctamente";
    }

}
