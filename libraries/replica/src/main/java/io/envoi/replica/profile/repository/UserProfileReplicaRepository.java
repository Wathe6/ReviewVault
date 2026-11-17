package io.envoi.replica.profile.repository;

import io.envoi.replica.profile.entity.UserProfileReplicaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserProfileReplicaRepository extends JpaRepository<UserProfileReplicaEntity, UUID> {
}
