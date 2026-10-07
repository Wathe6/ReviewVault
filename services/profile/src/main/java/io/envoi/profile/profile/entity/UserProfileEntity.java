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

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_profile", schema = "profile")
public class UserProfileEntity extends BaseUuidEntity {

    @NotNull
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Nullable
    @Column(name = "cover_url", nullable = true)
    private String coverUrl;
}
