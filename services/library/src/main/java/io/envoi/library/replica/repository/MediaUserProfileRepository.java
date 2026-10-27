package io.envoi.library.replica.repository;

import io.envoi.library.replica.entity.UserProfileReplicaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MediaUserProfileRepository extends JpaRepository<UserProfileReplicaEntity, UUID> {
}
