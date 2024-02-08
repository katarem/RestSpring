package aed.rest.RestSpring.controller;

import aed.rest.RestSpring.model.DiscograficaEntity;
import aed.rest.RestSpring.repository.DiscograficaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/discografica")
public class DiscograficaController {

    @Autowired
    DiscograficaRepository repository;

    @GetMapping("/all")
    public List<DiscograficaEntity> getAllDiscograficas() {
        return repository.findAll();
    }


    @GetMapping("/{id}")
    public Optional<DiscograficaEntity> getDiscograficaById(@PathVariable("id") Integer id) {
        return repository.findById(id);
    }

    @PostMapping
    public String addDiscografica(@RequestBody DiscograficaEntity discografica){
        var t = repository.findById(discografica.getId());
        if(t.isEmpty()){
            repository.save(discografica);
            return "Discográfica guardada correctamente";
        }
        return "Ya existía esa discográfica";
    }

    @PutMapping("/{id}")
    public String updateDiscografica(@PathVariable("id") Integer id, @RequestBody DiscograficaEntity discografica){
        var t = repository.findById(id);
        if(t.isEmpty()) return "No existía esa discográfica";
        var discograficaDB = t.get();
        discograficaDB.setNombre(discografica.getNombre());
        discograficaDB.setArtistasById(discografica.getArtistasById());
        repository.save(discograficaDB);
        return "Actualizada correctamente";
    }

    @DeleteMapping("/{id}")
    public String removeDiscografica(@PathVariable("id") Integer id){
        var t = repository.findById(id);
        if(t.isEmpty()) return "No existe esa discográfica";
        repository.delete(t.get());
        return "Discográfica " + id + " fue eliminada correctamente";
    }

}