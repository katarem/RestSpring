package aed.rest.RestSpring.repository;

import aed.rest.RestSpring.model.AlbumEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<AlbumEntity, Integer> {
}
