package io.envoi.media.classification.entity;

import io.envoi.media.common.entity.BaseNumericIdEntity;
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
@Table(name = "media_genre", schema = "media")
public class MediaGenreEntity extends BaseNumericIdEntity<Short> {

    @NotNull
    @Column(name = "original_name", nullable = false)
    private String originalName;

    @NotNull
    @Column(name = "normalized_name", nullable = false, unique = true)
    private String normalizedName;

    @NotNull
    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;
}
