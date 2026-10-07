package io.envoi.media.credits.service;

import io.envoi.media.common.entity.LanguageEntity;
import io.envoi.media.common.repository.LanguageRepository;
import io.envoi.media.credits.dto.request.CompanyRequest;
import io.envoi.media.credits.dto.response.CompanyResponse;
import io.envoi.media.credits.entity.CompanyEntity;
import io.envoi.media.credits.entity.CompanyTranslationEntity;
import io.envoi.media.credits.mapping.CompanyMapper;
import io.envoi.media.credits.repository.CompanyRepository;
import io.envoi.media.credits.repository.CompanyTranslationRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CompanyService {

    private final CompanyRepository companyRepository;

    private final CompanyTranslationRepository translationRepository;

    private final LanguageRepository languageRepository;

    private final CompanyMapper companyMapper;

    @Transactional(readOnly = true)
    public CompanyEntity findById(UUID id) {

        return companyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Company not found " + id));
    }

    public CompanyEntity create(CompanyRequest companyRequest) {
        CompanyEntity companyEntity = companyMapper.toEntity(companyRequest);

        companyEntity = companyRepository.save(companyEntity);

        log.info("Company created with id {}", companyEntity.getId());

        return companyEntity;
    }

    public CompanyEntity update(UUID id, CompanyRequest companyRequest) {
        CompanyEntity company = companyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Company not found " + id));

        companyMapper.update(companyRequest, company);

        company = companyRepository.save(company);

        log.info("Company updated with id {}", company.getId());

        return company;
    }

    public void deleteById(UUID id) {
        CompanyEntity company = companyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Company not found " + id));

        companyRepository.delete(company);

        log.info("Company deleted with id {}", company.getId());
    }

    @Transactional(readOnly = true)
    public CompanyResponse findLocalizedById(UUID id, String languageCode) {
        LanguageEntity languageEntity = languageRepository.findByCode(languageCode)
                .orElseThrow(() -> new EntityNotFoundException("Language not found " + languageCode));

        CompanyEntity company = companyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Company not found " + id));

        CompanyTranslationEntity translation = translationRepository.findByCompanyAndLanguage(company, languageEntity)
                .orElse(null);

        return companyMapper.toResponseWithLanguage(company, translation);
    }

    @Transactional(readOnly = true)
    public List<CompanyEntity> findByName(String name) {

        return companyRepository.findByName(name);
    }
}
