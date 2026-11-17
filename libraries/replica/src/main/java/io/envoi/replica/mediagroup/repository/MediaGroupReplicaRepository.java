package io.envoi.replica.mediagroup.repository;

import io.envoi.replica.mediagroup.entity.MediaGroupReplicaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MediaGroupReplicaRepository extends JpaRepository<MediaGroupReplicaEntity, UUID> {
}
