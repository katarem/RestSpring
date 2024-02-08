package aed.rest.RestSpring.controller;

import aed.rest.RestSpring.model.ArtistaEntity;
import aed.rest.RestSpring.repository.ArtistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/artista")
public class ArtistaController {

    @Autowired
    ArtistaRepository repository;

    @GetMapping("/{id}")
    public Optional<ArtistaEntity> getArtistaById(@PathVariable("id") Integer id){
        return repository.findById(id);
    }

    @GetMapping("/all")
    public List<ArtistaEntity> getAllArtistas(){
        return repository.findAll();
    }

    @PostMapping("/{id}")
    public String addArtista(@RequestBody ArtistaEntity artista){
        var t = repository.findById(artista.getId());
        if(t.isEmpty()){
            repository.save(artista);
            return "Artista guardado correctamente";
        }
        return "Ya existía ese artista";
    }

    @PutMapping("/{id}")
    public String updateArtista(@PathVariable("id") Integer id,@RequestBody ArtistaEntity artista){
        var t = repository.findById(id);
        if(t.isPresent()){
            var artistaDB = t.get();
            artistaDB.setNombre(artista.getNombre());
            artistaDB.setAlbumsById(artista.getAlbumsById());
            artistaDB.setDiscograficaByDiscograficaId(artista.getDiscograficaByDiscograficaId());
            repository.save(artistaDB);
            return "Actualizado correctamente";
        }
        return "No existía ese artista";
    }

    @DeleteMapping("/{id}")
    public String removeArtista(@PathVariable("id") Integer id){
        var t = repository.findById(id);
        if(t.isEmpty()) return "No existía ese artista";
        repository.delete(t.get());
        return "Eliminado correctamente";
    }

}
