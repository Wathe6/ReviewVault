package io.envoi.media.credits.repository;

import io.envoi.media.common.entity.LanguageEntity;
import io.envoi.media.credits.entity.PersonEntity;
import io.envoi.media.credits.entity.PersonTranslationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonTranslationRepository extends JpaRepository<PersonTranslationEntity, Long> {

    Optional<PersonTranslationEntity> findByPersonAndLanguage(PersonEntity person, LanguageEntity language);
}
