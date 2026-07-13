package io.envoi.media.credits.repository;

import io.envoi.media.credits.entity.PersonTranslationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonTranslationRepository extends JpaRepository<PersonTranslationEntity, Long> {
}
