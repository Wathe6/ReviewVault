package io.envoi.media.media.repository;

import io.envoi.media.media.entity.MediaLinkTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaLinkTypeRepository extends JpaRepository<MediaLinkTypeEntity, Short> {
}
