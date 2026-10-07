package io.envoi.media.classification.repository;

import io.envoi.media.classification.entity.MediaFormatEntity;
import io.envoi.media.classification.entity.MediaFormatTranslationEntity;
import io.envoi.media.common.entity.LanguageEntity;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MediaFormatTranslationRepository extends JpaRepository<MediaFormatTranslationEntity, Long> {

    Optional<MediaFormatTranslationEntity> findByMediaFormatAndLanguage(
            @NotNull MediaFormatEntity mediaFormat,
            @NotNull LanguageEntity language
    );
}
