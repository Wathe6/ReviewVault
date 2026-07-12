package io.envoi.media.media.entity;

import io.envoi.media.common.entity.BaseTranslationEntity;
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
        name = "media_group_translation",
        schema = "media",
        uniqueConstraints = @UniqueConstraint(columnNames = {"media_group_id", "language_id"})
)
public class MediaGroupTranslationEntity extends BaseTranslationEntity<Long> {

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn(name = "media_group_id", nullable = false)
    private MediaGroupEntity mediaGroup;

    @Column(name = "description", nullable = true, columnDefinition = "TEXT")
    private String description;
}
