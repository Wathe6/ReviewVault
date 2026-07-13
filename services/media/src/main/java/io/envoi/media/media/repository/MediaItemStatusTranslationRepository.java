package io.envoi.media.media.repository;

import io.envoi.media.media.entity.MediaItemStatusTranslationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaItemStatusTranslationRepository extends JpaRepository<MediaItemStatusTranslationEntity, Short> {
}
