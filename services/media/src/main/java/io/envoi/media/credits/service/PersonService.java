package io.envoi.media.credits.service;

import io.envoi.media.common.entity.LanguageEntity;
import io.envoi.media.common.repository.LanguageRepository;
import io.envoi.media.credits.dto.request.PersonRequest;
import io.envoi.media.credits.dto.response.PersonResponse;
import io.envoi.media.credits.entity.PersonEntity;
import io.envoi.media.credits.entity.PersonTranslationEntity;
import io.envoi.media.credits.mapping.PersonMapper;
import io.envoi.media.credits.repository.PersonRepository;
import io.envoi.media.credits.repository.PersonTranslationRepository;
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
public class PersonService {

    private final PersonRepository personRepository;

    private final PersonTranslationRepository translationRepository;

    private final LanguageRepository languageRepository;

    private final PersonMapper personMapper;

    @Transactional(readOnly = true)
    public PersonEntity findById(UUID id) {

        return personRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Person not found " + id));
    }

    public PersonEntity create(PersonRequest personRequest) {

        PersonEntity personEntity = personMapper.toEntity(personRequest);

        personEntity = personRepository.save(personEntity);

        log.info("Person created with id {}", personEntity.getId());

        return personEntity;
    }

    public PersonEntity update(UUID id, PersonRequest personRequest) {

        PersonEntity personEntity = personRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Person not found " + id));

        personMapper.update(personRequest, personEntity);

        personEntity = personRepository.save(personEntity);

        log.info("Person updated with id {}", personEntity.getId());

        return personEntity;
    }

    public void deleteById(UUID id) {

        PersonEntity person = personRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Person not found " + id));

        personRepository.delete(person);

        log.info("Person deleted with id {}", person.getId());
    }

    @Transactional(readOnly = true)
    public PersonResponse findLocalizedById(UUID id, String languageCode) {

        LanguageEntity languageEntity = languageRepository.findByCode(languageCode)
                .orElseThrow(() -> new EntityNotFoundException("Language not found " + languageCode));

        PersonEntity person = personRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Person not found " + id));

        PersonTranslationEntity translation = translationRepository
                .findByPersonAndLanguage(person, languageEntity)
                .orElse(null);

        return personMapper.toResponseWithLanguage(person, translation);
    }

    @Transactional(readOnly = true)
    public List<PersonEntity> findByName(String name) {

        return personRepository.findByName(name);
    }
}
