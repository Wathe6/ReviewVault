package io.envoi.review.rate.repository;

import io.envoi.review.rate.entity.UserRatingDescriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface UserRatingDescriptionRepository extends JpaRepository<UserRatingDescriptionEntity, Long> {

    @Modifying
    @Query(value = """
                    UPDATE review.user_rating_description AS d
                    SET "left" = s.min + s.step * round(
                            d.normalized_left * (s.max - s.min) / (10000.0 * s.step)
                        ),
                        "right" = s.min + s.step * round(
                            d.normalized_right * (s.max - s.min) / (10000.0 * s.step)
                        )
                    FROM review.rating_scale AS s
                    WHERE s.id = :scaleId
                      AND d.user_profile_replica_id = :profileId
    """, nativeQuery = true)
    void updateLeftAndRightAfterPreferenceChange(
            @Param("profileId") UUID profileId,
            @Param("scaleId") Short scaleId);
}
