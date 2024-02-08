package aed.rest.RestSpring.controller;

import aed.rest.RestSpring.model.AlbumEntity;
import aed.rest.RestSpring.model.CancionEntity;
import aed.rest.RestSpring.repository.AlbumRepository;
import aed.rest.RestSpring.repository.CancionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/album")
public class AlbumController {
    @Autowired
    AlbumRepository repository;

    @GetMapping("/{id}")
    Optional<AlbumEntity> getAlbumById(@PathVariable("id") Integer id){
        return repository.findById(id);
    }

    @GetMapping("/all")
    List<AlbumEntity> getAllAlbums(){
        return repository.findAll();
    }

    @PostMapping
    @ResponseBody
    public String addAlbum(@RequestBody AlbumEntity album){
        repository.save(album);
        return "sin problemas";
    }

    @PutMapping("/{id}")
    @ResponseBody
    public String updateAlbum(@PathVariable("id") Integer id, @RequestBody AlbumEntity album){
        var t = repository.findById(id);
        if(t.isEmpty())
            return "No se modificó ningún registro: no existía el álbum";
        var albumDB = t.get();
        albumDB.setNombre(album.getNombre());
        albumDB.setCancionsById(album.getCancionsById());
        albumDB.setArtistaByArtistaId(album.getArtistaByArtistaId());
        albumDB.setAnyo(album.getAnyo());
        repository.save(albumDB);
        return "Álbum " + album.getId() + " modificado correctamente";
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public String removeAlbum(@PathVariable("id") Integer id){
        var t = repository.findById(id);
        if(t.isEmpty())
            return "No se modificó ningún registro: no existía el album";
        repository.delete(t.get());
        return "Álbum " + id + " ha sido eliminado";
    }



}
