package aed.rest.RestSpring.repository;

import aed.rest.RestSpring.model.GenresEntity;
import aed.rest.RestSpring.model.ReviewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface ReviewsRepository extends JpaRepository<ReviewsEntity, Integer> {

    public List<ReviewsEntity> getReviewsEntitiesByScoreGreaterThan(BigDecimal nota);
    @Query("SELECT r FROM ReviewsEntity r JOIN r.genres g WHERE g.genre = :nombreGenero")
    public List<ReviewsEntity> findByGenres(@Param("nombreGenero") String nombreGenero);
    @Query("SELECT r FROM ReviewsEntity r JOIN r.genres g WHERE g.genre = :nombreGenero AND r.score >= :nota")
    public List<ReviewsEntity> findByGenresAndScore(@Param("nombreGenero") String nombreGenero, @Param("nota") BigDecimal nota);

}
