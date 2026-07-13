package io.envoi.media.media.repository;

import io.envoi.media.media.entity.MediaGroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MediaGroupRepository extends JpaRepository<MediaGroupEntity, UUID> {

    List<MediaGroupEntity> findByTitle(String title);
}
