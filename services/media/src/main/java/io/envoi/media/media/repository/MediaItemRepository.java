package io.envoi.media.media.repository;

import io.envoi.media.media.entity.MediaItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MediaItemRepository extends JpaRepository<MediaItemEntity, UUID> {

    List<MediaItemEntity> findByTitle(String title);
}
