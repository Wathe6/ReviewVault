package io.envoi.replica.mediaitem.repository;

import io.envoi.replica.mediaitem.entity.MediaItemReplicaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MediaItemReplicaRepository extends JpaRepository<MediaItemReplicaEntity, UUID> {
}
