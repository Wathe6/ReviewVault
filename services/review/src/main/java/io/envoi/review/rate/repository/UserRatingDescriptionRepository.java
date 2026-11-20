package io.envoi.review.rate.repository;

import io.envoi.review.rate.entity.UserRatingDescriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRatingDescriptionRepository extends JpaRepository<UserRatingDescriptionEntity, Long> {
}
