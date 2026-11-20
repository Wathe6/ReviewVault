package io.envoi.replica.mediaitem.entity;

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
@Table(name = "media_item_replica")
public class MediaItemReplicaEntity extends BaseAuditEntity {

    @Id
    private UUID id;

    private String title;

    private String coverUrl;

    private UUID lastEventId;

    private Instant lastEventAt;

    private Boolean deleted;
}