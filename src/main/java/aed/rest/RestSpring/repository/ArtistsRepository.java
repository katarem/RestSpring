package aed.rest.RestSpring.repository;

import aed.rest.RestSpring.model.ArtistsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ArtistsRepository extends JpaRepository<ArtistsEntity, Integer> {
    public List<ArtistsEntity> findArtistsEntitiesByArtistContainsIgnoreCase(String artistName);
}
