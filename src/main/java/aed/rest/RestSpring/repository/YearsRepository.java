package aed.rest.RestSpring.repository;

import aed.rest.RestSpring.model.YearsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface YearsRepository extends JpaRepository<YearsEntity, Integer> {
}
