package io.envoi.media.media.entity;

import io.envoi.media.common.entity.BaseNumericIdEntity;
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
        name = "media_external",
        schema = "media",
        uniqueConstraints = @UniqueConstraint(columnNames = {"source", "external_id"}))
public class MediaExternalEntity extends BaseNumericIdEntity<Long> {

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn(name = "media_item_id", nullable = false)
    private MediaItemEntity mediaItem;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn(name = "media_link_type_id", nullable = false)
    private MediaLinkTypeEntity mediaLinkType;

    @NotNull
    @Column(name = "external_id", nullable = false)
    private String externalId;
}
