package io.envoi.media.classification.repository;

import io.envoi.media.classification.entity.MediaCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaCategoryRepository extends JpaRepository<MediaCategoryEntity, Short> {

}
