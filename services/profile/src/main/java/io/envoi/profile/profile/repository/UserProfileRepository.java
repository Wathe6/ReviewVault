package io.envoi.profile.profile.repository;

import io.envoi.profile.profile.entity.UserProfileEntity;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserProfileRepository extends JpaRepository<UserProfileEntity, UUID> {

    Optional<UserProfileEntity> findByKeycloakUserId(@NotNull UUID keycloakUserId);
}
