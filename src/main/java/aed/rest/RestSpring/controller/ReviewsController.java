package aed.rest.RestSpring.controller;

import aed.rest.RestSpring.model.ArtistsEntity;
import aed.rest.RestSpring.model.ReviewsEntity;
import aed.rest.RestSpring.repository.ArtistsRepository;
import aed.rest.RestSpring.repository.ReviewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reviews")
public class ReviewsController {

    @Autowired
    ReviewsRepository repository;

    @GetMapping("/all")
    public List<ReviewsEntity> getAllReviews() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<ReviewsEntity> getReviewById(@PathVariable("id") Integer id) {
        return repository.findById(id);
    }

    @PostMapping
    public String addReview(@RequestBody ReviewsEntity review) {
        if (repository.existsById(review.getReviewid()))
            return "Ya existía esa review";
        repository.save(review);
        return "Review agregada correctamente";
    }

    @PutMapping("/{id}")
    public String updateReview(@PathVariable("id") Integer id, @RequestBody ReviewsEntity review) {
        if (!repository.existsById(id))
            return "No se modificó, no existía esa review";
        var reviewDB = repository.findById(id).get();
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
        return "Review modificada correctamente";
    }

    @DeleteMapping("/{id}")
    public String deleteReview(@PathVariable("id") Integer id) {
        if (!repository.existsById(id))
            return "No se eliminó esa review, no existe";
        repository.delete(repository.getReferenceById(id));
        return "Review eliminada correctamente";
    }

}
