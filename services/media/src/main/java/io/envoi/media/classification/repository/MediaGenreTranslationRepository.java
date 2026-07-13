package io.envoi.media.classification.repository;

import io.envoi.media.classification.entity.MediaGenreTranslationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaGenreTranslationRepository extends JpaRepository<MediaGenreTranslationEntity, Long> {
}
