package io.envoi.media.classification.repository;

import io.envoi.media.classification.entity.MediaCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaCategoryTranslationRepository extends JpaRepository<MediaCategoryEntity, Short > {

}
