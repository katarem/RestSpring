package aed.rest.RestSpring.controller;

import aed.rest.RestSpring.model.CancionEntity;
import aed.rest.RestSpring.repository.CancionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cancion")
public class CancionController {
    @Autowired
    CancionRepository repository;

    @GetMapping("/{id}")
    Optional<CancionEntity> getCancionById(@PathVariable("id") Integer id){
        return repository.findById(id);
    }

    @GetMapping("/all")
    List<CancionEntity> getAllCanciones(){
        return repository.findAll();
    }

    @PostMapping
    @ResponseBody
    public String addCancion(@RequestBody CancionEntity cancion){
        repository.save(cancion);
        return "sin problemas";
    }

    @PutMapping("{id}")
    @ResponseBody
    public String updateCancion(@PathVariable("id") Integer id, @RequestBody CancionEntity cancion){
        var t = repository.findById(id);
        if(t.isEmpty())
            return "No se modificó ningún registro: no existía la entidad";
        var cancionDB = t.get();
        cancionDB.setNombre(cancion.getNombre());
        cancionDB.setDuracion(cancion.getDuracion());
        cancionDB.setAlbumByAlbumId(cancion.getAlbumByAlbumId());
        repository.save(cancionDB);
        return "Canción" + cancion.getId() + " modificada correctamente";
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public String removeCancion(@PathVariable("id") Integer id){
        var t = repository.findById(id);
        if(t.isEmpty())
            return "No se modificó ningún registro: no existía la entidad";
        repository.delete(t.get());
        return "Canción " + id + " ha sido eliminada";
    }



}
