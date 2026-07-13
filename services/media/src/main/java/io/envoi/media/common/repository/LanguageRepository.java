package io.envoi.media.common.repository;

import io.envoi.media.common.entity.LanguageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepository extends JpaRepository<LanguageEntity, Short> {
}
