package io.envoi.review.rate.repository;

import io.envoi.review.rate.entity.RatingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface RatingRepository extends JpaRepository<RatingEntity, Long> {

    @Modifying
    @Query(value = """
                    UPDATE review.rating AS r
                    SET value = s.min + s.step * round(
                        r.normalized_value * (s.max - s.min) / (10000.0 * s.step)
                    )
                    FROM review.rating_scale AS s
                    WHERE s.id = :scaleId
                      AND r.user_profile_replica_id = :profileId
    """, nativeQuery = true)
    void updateValueAfterPreferenceChange(
            @Param("profileId") UUID profileId,
            @Param("scaleId") Short scaleId);
}
