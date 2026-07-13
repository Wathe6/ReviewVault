package io.envoi.media.classification.repository;

import io.envoi.media.classification.entity.MediaFormatEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaFormatRepository extends JpaRepository<MediaFormatEntity, Short> {
}
