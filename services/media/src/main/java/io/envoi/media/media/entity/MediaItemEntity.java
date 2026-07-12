package io.envoi.media.media.entity;

import io.envoi.media.common.entity.BaseUuidEntity;
import io.envoi.media.classification.entity.MediaCategoryEntity;
import io.envoi.media.classification.entity.MediaFormatEntity;
import io.envoi.media.common.entity.LanguageEntity;
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
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "media_item", schema = "media")
public class MediaItemEntity extends BaseUuidEntity {

    @NotNull
    @Column(name = "title", nullable = false)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "media_group_id", nullable = true)
    private MediaGroupEntity mediaGroup;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn(name = "media_format_id", nullable = false)
    private MediaFormatEntity mediaFormat;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn(name = "media_category_id", nullable = false)
    private MediaCategoryEntity mediaCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn(name = "media_item_status_id", nullable = false)
    private MediaItemStatusEntity mediaItemStatus;

    @Column(name = "description", nullable = true, columnDefinition = "TEXT")
    private String description;

    @Column(name = "release_date", nullable = true)
    private LocalDate releaseDate;

    @Column(name = "end_date", nullable = true)
    private LocalDate endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn(name = "original_language_id", nullable = false)
    private LanguageEntity originalLanguage;

    @Column(name = "cover_url", nullable = true)
    private String coverUrl;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "metadata", nullable = true, columnDefinition = "JSONB")
    private Map<Object, String> metadata;
}
