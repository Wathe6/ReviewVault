package io.envoi.media.classification.repository;

import io.envoi.media.classification.entity.MediaGenreCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaGenreCategoryRepository extends JpaRepository<MediaGenreCategoryEntity, Long> {
}
