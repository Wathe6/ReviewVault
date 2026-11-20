package io.envoi.review.rate.repository;

import io.envoi.review.rate.entity.RatingScaleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RatingScaleRepository extends JpaRepository<RatingScaleEntity, Short> {

    Optional<RatingScaleEntity> findByName(String name);
}
