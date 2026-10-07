package io.envoi.profile.collection.entity;

import io.envoi.contracts.common.BaseNumericIdEntity;
import io.envoi.contracts.common.BaseUuidEntity;
import io.envoi.profile.profile.entity.UserProfileEntity;
import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "user_folder", schema = "profile")
public class UserFolderEntity extends BaseNumericIdEntity<Long> {

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn(name = "user_profile_id", nullable = false)
    private UserProfileEntity userProfileEntity;

    @NotNull
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @NotNull
    @Column(name = "cover_url", nullable = true)
    private String coverUrl;

    @Nullable
    @Column(name = "position", nullable = true)
    private Integer position;
}
