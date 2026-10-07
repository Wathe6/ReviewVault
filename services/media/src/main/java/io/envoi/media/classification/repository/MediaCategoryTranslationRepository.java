package io.envoi.media.classification.repository;

import io.envoi.media.classification.entity.MediaCategoryEntity;
import io.envoi.media.classification.entity.MediaCategoryTranslationEntity;
import io.envoi.media.common.entity.LanguageEntity;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MediaCategoryTranslationRepository extends JpaRepository<MediaCategoryTranslationEntity, Long > {


    Optional<MediaCategoryTranslationEntity> findByMediaCategoryAndLanguage(
            @NotNull MediaCategoryEntity mediaCategory,
            @NotNull LanguageEntity language
    );
}
