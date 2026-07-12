package io.envoi.media.classification.entity;

import io.envoi.media.common.entity.BaseNumericIdEntity;
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
@Table(name = "media_format", schema = "media")
public class MediaFormatEntity extends BaseNumericIdEntity<Short> {

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn(name = "media_category_id", nullable = false)
    private MediaCategoryEntity mediaCategory;

    @NotNull
    @Column(name = "default_name", nullable = false)
    private String defaultName;

    @NotNull
    @Column(name = "normalized_name", nullable = false, unique = true)
    private String normalizedName;
}