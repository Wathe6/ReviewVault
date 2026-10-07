package io.envoi.media.common.repository;

import io.envoi.media.common.entity.LanguageEntity;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LanguageRepository extends JpaRepository<LanguageEntity, Short> {

    Optional<LanguageEntity> findByCode(String code);
}
