package io.envoi.media.classification.entity;

import io.envoi.contracts.common.BaseNumericIdEntity;
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
        name = "media_genre_category",
        schema = "media",
        uniqueConstraints = @UniqueConstraint(columnNames = {"media_genre_id", "media_category_id"})
)
public class MediaGenreCategoryEntity extends BaseNumericIdEntity<Long> {

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn(name = "media_genre_id", nullable = false)
    private MediaGenreEntity mediaGenre;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn(name = "media_category_id", nullable = false)
    private MediaCategoryEntity mediaCategory;
}
