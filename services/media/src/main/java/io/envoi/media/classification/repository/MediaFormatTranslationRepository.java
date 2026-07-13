package io.envoi.media.classification.repository;

import io.envoi.media.classification.entity.MediaFormatTranslationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaFormatTranslationRepository extends JpaRepository<MediaFormatTranslationEntity, Long> {
}
