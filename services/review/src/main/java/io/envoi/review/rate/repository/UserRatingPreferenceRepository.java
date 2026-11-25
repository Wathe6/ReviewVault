package io.envoi.review.rate.repository;

import io.envoi.replica.profile.entity.UserProfileReplicaEntity;
import io.envoi.review.rate.entity.UserRatingPreferenceEntity;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRatingPreferenceRepository extends JpaRepository<UserRatingPreferenceEntity, Long> {

    Optional<UserRatingPreferenceEntity> findByUserProfileReplica(@NotNull UserProfileReplicaEntity userProfileReplica);
}
