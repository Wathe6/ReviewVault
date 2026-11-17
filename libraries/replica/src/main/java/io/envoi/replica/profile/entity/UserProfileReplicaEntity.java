package io.envoi.replica.profile.entity;

import io.envoi.contracts.common.BaseAuditEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "user_profile_replica")
public class UserProfileReplicaEntity extends BaseAuditEntity {

    @Id
    private UUID id;

    private UUID lastEventId;

    private Instant lastEventAt;

    private Boolean deleted;
}
