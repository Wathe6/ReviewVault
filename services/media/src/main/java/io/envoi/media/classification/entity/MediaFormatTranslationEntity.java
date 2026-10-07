package io.envoi.media.classification.entity;

import io.envoi.media.common.entity.BaseTranslationEntity;
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
        name = "media_format_translation",
        schema = "media",
        uniqueConstraints = @UniqueConstraint(columnNames = {"media_format_id", "language_id"})
)
public class MediaFormatTranslationEntity extends BaseTranslationEntity<Long> {

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn(name = "media_format_id", nullable = false)
    private MediaFormatEntity mediaFormat;
}
