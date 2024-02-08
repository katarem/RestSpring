package aed.rest.RestSpring.controller;

import aed.rest.RestSpring.model.GeneroEntity;
import aed.rest.RestSpring.repository.GeneroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/genero")
public class GeneroController {
    @Autowired
    GeneroRepository repository;

    @GetMapping("/{id}")
    Optional<GeneroEntity> getGeneroById(@PathVariable("id") Integer id){
        return repository.findById(id);
    }

    @GetMapping("/all")
    List<GeneroEntity> getAllGeneros(){
        return repository.findAll();
    }

    @PostMapping
    @ResponseBody
    public String addGenero(@RequestBody GeneroEntity genero){
        repository.save(genero);
        return "sin problemas";
    }

    @PutMapping("{id}")
    @ResponseBody
    public String updateGenero(@PathVariable("id") Integer id, @RequestBody GeneroEntity genero){
        var t = repository.findById(id);
        if(t.isEmpty())
            return "No se modificó ningún registro: no existe el género";
        var generoDB = t.get();
        generoDB.setNombre(genero.getNombre());
        generoDB.setAlbumsById(genero.getAlbumsById());
        repository.save(generoDB);
        return "Género " + id + " modificado correctamente";
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public String removeGenero(@PathVariable("id") Integer id){
        var t = repository.findById(id);
        if(t.isEmpty())
            return "No se modificó ningún registro: no existe ese género";
        repository.delete(t.get());
        return "Género " + id + " ha sido eliminado";
    }



}
