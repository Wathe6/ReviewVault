package io.envoi.media.media.entity;

import io.envoi.media.common.entity.BaseNumericIdEntity;
import io.envoi.media.classification.entity.MediaGenreEntity;
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
        name = "media_item_genre",
        schema = "media",
        uniqueConstraints = @UniqueConstraint(columnNames = {"media_item_id", "media_genre_id"})
)
public class MediaItemGenreEntity extends BaseNumericIdEntity<Long> {

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn(name = "media_item_id", nullable = false)
    private MediaItemEntity mediaItemEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn(name = "media_genre_id", nullable = false)
    private MediaGenreEntity mediaGenreEntity;
}
