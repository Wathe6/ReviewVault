package io.envoi.media.media.entity;

import io.envoi.contracts.common.BaseUuidEntity;
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

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "media_group", schema = "media")
public class MediaGroupEntity extends BaseUuidEntity {

    @NotNull
    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = true, columnDefinition = "TEXT")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn(name = "original_language_id", nullable = false)
    private LanguageEntity originalLanguage;

    @Column(name = "cover_url", nullable = true)
    private String coverUrl;

}
