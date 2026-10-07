package io.envoi.media.media.repository;

import io.envoi.media.common.entity.LanguageEntity;
import io.envoi.media.media.entity.MediaItemEntity;
import io.envoi.media.media.entity.MediaItemTranslationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MediaItemTranslationRepository extends JpaRepository<MediaItemTranslationEntity, Long> {

    List<MediaItemTranslationEntity> findByName(String name);

    Optional<MediaItemTranslationEntity> findByMediaItemAndLanguage(MediaItemEntity mediaItem, LanguageEntity language);
}
