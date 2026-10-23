package io.envoi.library.replica.entity;

import io.envoi.contracts.common.BaseUuidEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_profile_replica", schema = "library")
public class UserProfileReplicaEntity extends BaseUuidEntity {

    @Column(name = "last_event_id", nullable = false)
    private UUID lastEventId;

    @Column(name = "last_event_at", nullable = true)
    private Instant lastEventAt;

    @NotNull
    @Column(name = "deleted", nullable = false)
    private Boolean deleted;
}
