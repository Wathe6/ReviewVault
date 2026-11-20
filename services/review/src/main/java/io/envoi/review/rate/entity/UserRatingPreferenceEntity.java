package io.envoi.review.rate.entity;

import io.envoi.contracts.common.BaseNumericIdEntity;
import io.envoi.replica.profile.entity.UserProfileReplicaEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
        name = "user_rating_preference",
        schema = "review",
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_profile_replica_id"})
)
public class UserRatingPreferenceEntity extends BaseNumericIdEntity<Long> {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rating_scale_id", nullable = false)
    private RatingScaleEntity ratingScaleEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn(name = "user_profile_replica_id", nullable = false)
    private UserProfileReplicaEntity userProfileReplica;
}
