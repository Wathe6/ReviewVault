package io.envoi.media.credits.repository;

import io.envoi.media.credits.entity.MediaCreditEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaCreditRepository extends JpaRepository<MediaCreditEntity, Long> {
}
