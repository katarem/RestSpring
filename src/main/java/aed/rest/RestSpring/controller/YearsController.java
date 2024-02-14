package aed.rest.RestSpring.controller;

import aed.rest.RestSpring.model.ArtistsEntity;
import aed.rest.RestSpring.model.YearsEntity;
import aed.rest.RestSpring.repository.ArtistsRepository;
import aed.rest.RestSpring.repository.YearsRepository;
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
@RequestMapping("/years")
public class YearsController {

    @Autowired
    YearsRepository repository;

    @GetMapping("/all")
    public ResponseEntity<?> getAllYears(@RequestParam(name = "index", required = false) Integer index,
                                      @RequestParam(name = "size", required = false) Integer size) {
        if(index == null) index = 0;
        if(size == null) size = 10;
        Pageable pageable = PageRequest.of(index,size);
        return new ResponseEntity<>(repository.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getYearsById(@PathVariable("id") Integer id) {
        var opt = repository.findById(id);
        return opt.isPresent() ? new ResponseEntity<>(opt.get(),HttpStatus.OK)
                : new ResponseEntity<>(new StringResponse("No existía dicho year"),HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> addYear(@RequestBody YearsEntity anyo) {
        if (repository.existsById(anyo.getYearid()))
            return new ResponseEntity<>(new StringResponse("Ya existía ese year."),HttpStatus.CONFLICT);
        repository.save(anyo);
        return new ResponseEntity<>(new StringResponse("Year agregado correctamente."),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateYear(@PathVariable("id") Integer id, @RequestBody YearsEntity anyo) {
        var anyoDBOPtional = repository.findById(id);
        if (anyoDBOPtional.isEmpty()){
            repository.save(anyo);
            return new ResponseEntity<>(new StringResponse("No existía ese year, fue creado"),HttpStatus.CREATED);
        }
        var anyoDB = anyoDBOPtional.get();
        anyoDB.setYear(anyo.getYear());
        anyoDB.setReviewid(anyo.getReviewid());
        repository.save(anyoDB);
        return new ResponseEntity<>(new StringResponse("Year actualizado correctamente"),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteYear(@PathVariable("id") Integer id) {
        if (!repository.existsById(id))
            return new ResponseEntity<>(new StringResponse("No existía ese year"),HttpStatus.NOT_FOUND);
        repository.delete(repository.getReferenceById(id));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
