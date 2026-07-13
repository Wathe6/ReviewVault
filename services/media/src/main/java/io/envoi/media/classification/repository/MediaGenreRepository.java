package io.envoi.media.classification.repository;

import io.envoi.media.classification.entity.MediaGenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaGenreRepository extends JpaRepository<MediaGenreEntity, Short> {
}
