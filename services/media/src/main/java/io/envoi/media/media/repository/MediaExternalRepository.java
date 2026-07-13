package io.envoi.media.media.repository;

import io.envoi.media.media.entity.MediaExternalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaExternalRepository extends JpaRepository<MediaExternalEntity, Long> {
}
