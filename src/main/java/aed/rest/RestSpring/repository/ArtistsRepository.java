package aed.rest.RestSpring.repository;

import aed.rest.RestSpring.model.ArtistsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistsRepository extends JpaRepository<ArtistsEntity, Integer> {
}
