package io.envoi.media.classification.repository;

import io.envoi.media.classification.entity.MediaGenreEntity;
import io.envoi.media.classification.entity.MediaGenreTranslationEntity;
import io.envoi.media.common.entity.LanguageEntity;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MediaGenreTranslationRepository extends JpaRepository<MediaGenreTranslationEntity, Long> {

    Optional<MediaGenreTranslationEntity> findByMediaGenreAndLanguage(
            @NotNull MediaGenreEntity mediaGenreId,
            @NotNull LanguageEntity languageId
    );
}
