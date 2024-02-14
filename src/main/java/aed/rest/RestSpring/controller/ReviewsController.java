package aed.rest.RestSpring.controller;

import aed.rest.RestSpring.model.ReviewsEntity;
import aed.rest.RestSpring.repository.GenresRepository;
import aed.rest.RestSpring.repository.ReviewsRepository;
import aed.rest.RestSpring.utils.StringResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
@RestController
@RequestMapping("/reviews")
public class ReviewsController {

    @Autowired
    ReviewsRepository repository;

    @Autowired
    GenresRepository genresRepository;

    @GetMapping("/all")
    public ResponseEntity<?> getAllReviews(@RequestParam(value = "nota", required = false) BigDecimal nota, @RequestParam(value = "genero", required = false) String genero,
                                           @RequestParam(value = "size",required = false) Integer size, @RequestParam(value = "index",required = false) Integer index) {
        if(size == null) size = 10;
        if(index == null) index = 0;

        final Pageable pageable = PageRequest.of(index,size, Sort.by(Sort.Direction.DESC,"score"));
        if(nota != null && genero != null) return reviewsByNotaYGenero(nota, genero);
        else if(genero != null) return new ResponseEntity<>(repository.findByGenres(genero),HttpStatus.OK);
        else if(nota != null) return reviewsByNota(nota);
        return new ResponseEntity<>(repository.findAll(pageable), HttpStatus.OK);
    }


    private ResponseEntity<?> reviewsByNotaYGenero(BigDecimal nota, String genero){
        if(!isValidNota(nota)) return new ResponseEntity<>(new StringResponse("Nota inválida"),HttpStatus.BAD_REQUEST);
        var response = repository.findByGenresAndScore(genero,nota);
        if(response.isEmpty()) return new ResponseEntity<>(new StringResponse("No existen reviews con dicho género y nota"),HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }


    private ResponseEntity<?> reviewsByNota(BigDecimal nota){
        if(!isValidNota(nota)) return new ResponseEntity<>(new StringResponse("Nota inválida"),HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(repository.getReviewsEntitiesByScoreGreaterThan(nota),HttpStatus.OK);
    }

    private boolean isValidNota(BigDecimal nota){
        return nota.compareTo(BigDecimal.ZERO) > 0 && nota.compareTo(BigDecimal.TEN) < 1;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getReviewById(@PathVariable("id") Integer id) {
        var review = repository.findById(id);
        if(review.isPresent()) return new ResponseEntity<>(review, HttpStatus.OK);
        return new ResponseEntity<>(new StringResponse("No existe review con ese id"),HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> addReview(@RequestBody ReviewsEntity review) {
        if (repository.existsById(review.getReviewid()))
            return new ResponseEntity<>(new StringResponse("Ya existía esa review"),HttpStatus.CONFLICT);
        repository.save(review);
        return new ResponseEntity<>(new StringResponse("Review agregada correctamente"),HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateReview(@PathVariable("id") Integer id, @RequestBody ReviewsEntity review) {
        var reviewDBOptional = repository.findById(id);
        if (reviewDBOptional.isEmpty()){
            repository.save(review);
            return new ResponseEntity<>(new StringResponse("Review no existía pero fue creada."),HttpStatus.CREATED);
        }
        var reviewDB = reviewDBOptional.get();
        reviewDB.setArtist(review.getArtist());
        reviewDB.setAuthor(review.getAuthor());
        reviewDB.setAuthorType(review.getAuthorType());
        reviewDB.setPubDate(review.getPubDate());
        reviewDB.setPubYear(review.getPubYear());
        reviewDB.setPubMonth(review.getPubMonth());
        reviewDB.setPubWeekday(review.getPubWeekday());
        reviewDB.setPubDay(review.getPubDay());
        reviewDB.setScore(review.getScore());
        reviewDB.setTitle(review.getTitle());
        reviewDB.setUrl(reviewDB.getUrl());
        reviewDB.setBestNewMusic(review.getBestNewMusic());
        repository.save(reviewDB);
        return new ResponseEntity<>(new StringResponse("Review modificada correctamente"),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReview(@PathVariable("id") Integer id) {
        if (!repository.existsById(id))
            return new ResponseEntity<>(new StringResponse("No se eliminó esa review, no existe"),HttpStatus.NOT_FOUND);
        repository.delete(repository.getReferenceById(id));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
