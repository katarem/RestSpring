package aed.rest.RestSpring.controller;

import aed.rest.RestSpring.model.ArtistsEntity;
import aed.rest.RestSpring.model.LabelsEntity;
import aed.rest.RestSpring.repository.ArtistsRepository;
import aed.rest.RestSpring.repository.LabelsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/labels")
public class LabelsController {

    @Autowired
    LabelsRepository repository;

    @GetMapping("/all")
    public List<LabelsEntity> getAllLabels() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<LabelsEntity> getLabelById(@PathVariable("id") Integer id) {
        return repository.findById(id);
    }

    @PostMapping
    public String addLabel(@RequestBody LabelsEntity label) {
        if (repository.existsById(label.getLabelid()))
            return "Ya existía esa etiqueta";
        repository.save(label);
        return "Etiqueta agregada correctamente";
    }

    @PutMapping("/{id}")
    public String updateLabel(@PathVariable("id") Integer id, @RequestBody LabelsEntity label) {
        if (!repository.existsById(id))
            return "No se modificó, no existía esa etiqueta";
        var labelDB = repository.findById(id).get();
        labelDB.setLabel(label.getLabel());
        labelDB.setReviewid(label.getReviewid());
        repository.save(labelDB);
        return "Etiqueta modificada correctamente";
    }

    @DeleteMapping("/{id}")
    public String deleteLabel(@PathVariable("id") Integer id) {
        if (!repository.existsById(id))
            return "No se eliminó esa etiqueta, no existe";
        repository.delete(repository.getReferenceById(id));
        return "Etiqueta eliminada correctamente";
    }

}
