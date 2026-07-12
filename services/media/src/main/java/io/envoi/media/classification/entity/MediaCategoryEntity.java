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
@Table(name = "media_category", schema = "media")
public class MediaCategoryEntity extends BaseNumericIdEntity<Short> {

    @NotNull
    @Column(name = "default_name", nullable = false)
    private String defaultName;

    @NotNull
    @Column(name = "normalized_name", nullable = false, unique = true)
    private String normalizedName;
}
