package io.envoi.review.rate.entity;

import io.envoi.contracts.common.BaseNumericIdEntity;
import io.envoi.replica.profile.entity.UserProfileReplicaEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_rating_description", schema = "review")
public class UserRatingDescriptionEntity extends BaseNumericIdEntity<Long> {

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn(name = "user_profile_replica_id", nullable = false)
    private UserProfileReplicaEntity userProfileId;

    @Column(name = "left", precision = 5, scale = 2, nullable = false)
    private BigDecimal left;

    @Column(name = "right", precision = 5, scale = 2, nullable = false)
    private BigDecimal right;

    @Column(name = "normalized_left", nullable = false)
    private Integer normalizedLeft;

    @Column(name = "normalized_right", nullable = false)
    private Integer normalizedRight;

    @Column(name = "description", length = 100, nullable = false)
    private String description;
}
