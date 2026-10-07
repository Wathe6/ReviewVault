package io.envoi.media.credits.repository;

import io.envoi.media.common.entity.LanguageEntity;
import io.envoi.media.credits.entity.CompanyEntity;
import io.envoi.media.credits.entity.CompanyTranslationEntity;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyTranslationRepository extends JpaRepository<CompanyTranslationEntity, Long> {

    Optional<CompanyTranslationEntity> findByCompanyAndLanguage(
            @NotNull CompanyEntity company,
            @NotNull LanguageEntity language
    );
}
