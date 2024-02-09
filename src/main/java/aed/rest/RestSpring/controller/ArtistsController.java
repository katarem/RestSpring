package aed.rest.RestSpring.controller;

import aed.rest.RestSpring.model.ArtistsEntity;
import aed.rest.RestSpring.repository.ArtistsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/artists")
public class ArtistsController {

    @Autowired
    ArtistsRepository repository;

    @GetMapping("/all")
    public List<ArtistsEntity> getAllArtists() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<ArtistsEntity> getArtistById(@PathVariable("id") Integer id) {
        return repository.findById(id);
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
