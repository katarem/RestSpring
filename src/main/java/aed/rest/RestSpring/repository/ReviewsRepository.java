package aed.rest.RestSpring.repository;

import aed.rest.RestSpring.model.ReviewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewsRepository extends JpaRepository<ReviewsEntity, Integer> {
}
