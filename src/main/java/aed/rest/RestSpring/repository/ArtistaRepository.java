package aed.rest.RestSpring.repository;

import aed.rest.RestSpring.model.ArtistaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistaRepository extends JpaRepository<ArtistaEntity, Integer> {
}
