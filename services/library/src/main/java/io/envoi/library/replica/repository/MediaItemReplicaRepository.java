package io.envoi.library.replica.repository;

import io.envoi.library.replica.entity.MediaItemReplicaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MediaItemReplicaRepository extends JpaRepository<MediaItemReplicaEntity, UUID> {
}
