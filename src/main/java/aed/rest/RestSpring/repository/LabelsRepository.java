package aed.rest.RestSpring.repository;

import aed.rest.RestSpring.model.LabelsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LabelsRepository extends JpaRepository<LabelsEntity, Integer> {
}
