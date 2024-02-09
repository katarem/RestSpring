package aed.rest.RestSpring.controller;

import aed.rest.RestSpring.model.ArtistsEntity;
import aed.rest.RestSpring.model.YearsEntity;
import aed.rest.RestSpring.repository.ArtistsRepository;
import aed.rest.RestSpring.repository.YearsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/years")
public class YearsController {

    @Autowired
    YearsRepository repository;

    @GetMapping("/all")
    public List<YearsEntity> getAllYears() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<YearsEntity> getYearsById(@PathVariable("id") Integer id) {
        return repository.findById(id);
    }

    @PostMapping
    public String addYear(@RequestBody YearsEntity anyo) {
        if (repository.existsById(anyo.getYearid()))
            return "Ya existía ese anio";
        repository.save(anyo);
        return "Anio agregado correctamente";
    }

    @PutMapping("/{id}")
    public String updateYear(@PathVariable("id") Integer id, @RequestBody YearsEntity anyo) {
        if (!repository.existsById(id))
            return "No se modificó, no existía ese anyo";
        var anyoDB = repository.findById(id).get();
        anyoDB.setYear(anyo.getYear());
        anyoDB.setReviewid(anyo.getReviewid());
        repository.save(anyoDB);
        return "Anio modificado correctamente";
    }

    @DeleteMapping("/{id}")
    public String deleteYear(@PathVariable("id") Integer id) {
        if (!repository.existsById(id))
            return "No se eliminó ese anio, no existe";
        repository.delete(repository.getReferenceById(id));
        return "Anio eliminado correctamente";
    }

}
