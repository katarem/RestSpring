package aed.rest.RestSpring.controller;

import aed.rest.RestSpring.model.ArtistsEntity;
import aed.rest.RestSpring.model.LabelsEntity;
import aed.rest.RestSpring.repository.ArtistsRepository;
import aed.rest.RestSpring.repository.LabelsRepository;
import aed.rest.RestSpring.utils.StringResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/labels")
public class LabelsController {

    @Autowired
    LabelsRepository repository;

    @GetMapping("/all")
    public ResponseEntity<?> getAllLabels(@RequestParam(name = "index") Integer index,@RequestParam(name = "size") Integer size) {
        if(index == null) index = 0;
        if(size == null) size = 0;

        Pageable pageable = PageRequest.of(index,size);
        return new ResponseEntity<>(repository.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getLabelById(@PathVariable("id") Integer id) {
        var opt = repository.findById(id);
        return opt.isPresent() ? new ResponseEntity<>(opt.get(),HttpStatus.OK)
                : new ResponseEntity<>(new StringResponse("No existía dicho label"),HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> addLabel(@RequestBody LabelsEntity label) {
        if (repository.existsById(label.getLabelid()))
            return new ResponseEntity<>(new StringResponse("Ya existía ese label."),HttpStatus.CONFLICT);
        repository.save(label);
        return new ResponseEntity<>(new StringResponse("Label agregado correctamente."),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateLabel(@PathVariable("id") Integer id, @RequestBody LabelsEntity label) {
        var labelDBOptional = repository.findById(id);
        if(labelDBOptional.isEmpty()) {
            repository.save(label);
            return new ResponseEntity<>(new StringResponse("No existía ese label, pero se creó"), HttpStatus.CREATED);
        }
        var labelDB = labelDBOptional.get();
        labelDB.setLabel(label.getLabel());
        labelDB.setReviewid(label.getReviewid());
        repository.save(labelDB);
        return new ResponseEntity<>(new StringResponse("Label actualizado correctamente"),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLabel(@PathVariable("id") Integer id) {
        if (!repository.existsById(id))
            return new ResponseEntity<>(new StringResponse("No existía ese label"),HttpStatus.NOT_FOUND);
        repository.delete(repository.getReferenceById(id));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
