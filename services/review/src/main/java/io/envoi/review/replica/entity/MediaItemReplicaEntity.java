package io.envoi.review.replica.entity;

import io.envoi.contracts.common.BaseUuidEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "media_item_replica", schema = "review")
public class MediaItemReplicaEntity extends BaseUuidEntity {

    @NotNull
    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "last_event_id", nullable = false)
    private UUID lastEventId;

    @Column(name = "last_event_at", nullable = true)
    private Instant lastEventAt;

    @NotNull
    @Column(name = "deleted", nullable = false)
    private Boolean deleted;
}

