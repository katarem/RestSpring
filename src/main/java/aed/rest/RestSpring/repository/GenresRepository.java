package aed.rest.RestSpring.repository;

import aed.rest.RestSpring.model.GenresEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenresRepository extends JpaRepository<GenresEntity, Integer> {
}
