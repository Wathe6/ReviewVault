package io.envoi.library.collection.entity;

import io.envoi.contracts.common.BaseNumericIdEntity;
import io.envoi.replica.profile.entity.UserProfileReplicaEntity;
import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
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
@Table(
        name = "user_folder",
        schema = "library",
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_profile_replica_id", "name"}))
public class UserFolderEntity extends BaseNumericIdEntity<Long> {

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn(name = "user_profile_replica_id", nullable = false)
    private UserProfileReplicaEntity userProfileReplica;

    @NotNull
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Nullable
    @Column(name = "cover_url", nullable = true)
    private String coverUrl;

    @Nullable
    @Column(name = "position", nullable = true)
    private Integer position;
}
