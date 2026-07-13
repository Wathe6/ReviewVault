package io.envoi.media.media.repository;

import io.envoi.media.media.entity.MediaLinkEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaLinkRepository extends JpaRepository<MediaLinkEntity, Long> {
}
