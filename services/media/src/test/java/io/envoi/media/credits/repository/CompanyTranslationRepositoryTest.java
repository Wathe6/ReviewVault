package io.envoi.media.credits.repository;

import io.envoi.media.common.entity.LanguageEntity;
import io.envoi.media.common.repository.LanguageRepository;
import io.envoi.media.credits.entity.CompanyEntity;
import io.envoi.media.credits.entity.CompanyTranslationEntity;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CompanyTranslationRepositoryTest {

    @Autowired
    private CompanyTranslationRepository repository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private LanguageRepository languageRepository;

    @Test
    @DisplayName("Test CompanyTranslation findByCompanyAndLanguage")
    void findByCompanyAndLanguage() {

        CompanyEntity company = companyRepository
                .findById(UUID.fromString("11111111-1111-1111-1111-111111111111"))
                .orElseThrow(() -> new EntityNotFoundException("Company not found with id 11111111-1111-1111-1111-111111111111"));

        LanguageEntity language = languageRepository
                .findById((short) 1)
                .orElseThrow(() -> new EntityNotFoundException("Language not found with id 1"));

        CompanyTranslationEntity entity = repository.findByCompanyAndLanguage(
                company,
                language
        ).orElse(null);

        assertThat(entity).isNull();
    }
}