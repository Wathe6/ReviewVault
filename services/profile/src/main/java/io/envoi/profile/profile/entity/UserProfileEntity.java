package io.envoi.profile.profile.entity;

import io.envoi.contracts.common.BaseUuidEntity;
import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_profile", schema = "profile")
public class UserProfileEntity extends BaseUuidEntity {

    @NotNull
    @Column(name = "keycloak_user_id", nullable = false, unique = true)
    private UUID keycloakUserId;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Nullable
    @Column(name = "cover_url", nullable = true)
    private String coverUrl;

    @NotNull
    @Column(name = "deleted", nullable = false)
    private Boolean deleted;
}
