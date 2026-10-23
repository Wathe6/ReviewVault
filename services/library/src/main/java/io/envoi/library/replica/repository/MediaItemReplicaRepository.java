package io.envoi.library.replica.repository;

import io.envoi.library.replica.entity.MediaItemReplicaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MediaItemReplicaRepository extends JpaRepository<MediaItemReplicaEntity, UUID> {
}
