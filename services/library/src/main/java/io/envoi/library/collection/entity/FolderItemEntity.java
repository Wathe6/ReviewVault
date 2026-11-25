package io.envoi.library.collection.entity;

import io.envoi.contracts.common.BaseNumericIdEntity;
import io.envoi.replica.mediagroup.entity.MediaGroupReplicaEntity;
import io.envoi.replica.mediaitem.entity.MediaItemReplicaEntity;
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
@Table(name = "folder_item", schema = "library")
public class FolderItemEntity extends BaseNumericIdEntity<Long> {

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn(name = "user_folder_id", nullable = false)
    private UserFolderEntity userFolder;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn(name = "media_item_replica_id", nullable = false)
    private MediaItemReplicaEntity mediaItemReplica;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn(name = "media_group_replica_id", nullable = false)
    private MediaGroupReplicaEntity mediaGroupReplica;

    @Nullable
    @Column(name = "position", nullable = true)
    private Integer position;
}
