package io.envoi.media.media.repository;

import io.envoi.media.media.entity.MediaItemStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaItemStatusRepository extends JpaRepository<MediaItemStatusEntity, Short> {
}
