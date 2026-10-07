package io.envoi.review.replica.repository;

import io.envoi.review.replica.entity.MediaItemReplicaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MediaItemReplicaRepository extends JpaRepository<MediaItemReplicaEntity, UUID> {
}
