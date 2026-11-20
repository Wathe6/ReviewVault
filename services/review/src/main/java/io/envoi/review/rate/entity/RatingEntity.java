package io.envoi.review.rate.entity;

import io.envoi.contracts.common.BaseNumericIdEntity;
import io.envoi.replica.mediagroup.entity.MediaGroupReplicaEntity;
import io.envoi.replica.mediaitem.entity.MediaItemReplicaEntity;
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
@Table(name = "rating", schema = "review")
public class RatingEntity extends BaseNumericIdEntity<Long> {

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn(name = "user_profile_replica_id", nullable = false)
    private UserProfileReplicaEntity userProfileReplica;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "media_item_replica_id", nullable = true)
    private MediaItemReplicaEntity mediaItemReplica;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "media_group_replica_id", nullable = true)
    private MediaGroupReplicaEntity mediaGroupReplica;

    @Column(name = "value", precision = 5, scale = 2, nullable = false)
    @NotNull
    private BigDecimal value;

    @Column(name = "normalized_value", nullable = false)
    private Integer normalizedValue;
}
