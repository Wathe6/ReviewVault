package io.envoi.media.media.service;

import io.envoi.media.common.repository.LanguageRepository;
import io.envoi.media.media.dto.request.MediaGroupRequest;
import io.envoi.media.media.entity.MediaGroupEntity;
import io.envoi.media.media.mapping.MediaGroupMapper;
import io.envoi.media.media.repository.MediaGroupRepository;
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
public class MediaGroupService {

    private final MediaGroupRepository mediaGroupRepository;

    private final LanguageRepository languageRepository;

    private final MediaGroupMapper mapper;

    @Transactional(readOnly = true)
    public MediaGroupEntity findById(UUID id) {

        return mediaGroupRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Group not found " + id));
    }

    public MediaGroupEntity create(MediaGroupRequest request) {

        MediaGroupEntity entity = mapper.toEntity(request);

        entity.setOriginalLanguage(languageRepository.findByCode(request.originalLanguageCode())
                .orElseThrow(() -> new EntityNotFoundException("Language not found " + request.originalLanguageCode())));

        entity = mediaGroupRepository.save(entity);

        log.info("MediaGroup created with id {}", entity.getId());

        return entity;
    }

    public MediaGroupEntity update(UUID id, MediaGroupRequest request) {

        MediaGroupEntity entity = mediaGroupRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Group not found " + id));

        mapper.update(request, entity);

        entity.setOriginalLanguage(languageRepository.findByCode(request.originalLanguageCode())
                .orElseThrow(() -> new EntityNotFoundException("Language not found " + request.originalLanguageCode())));

        entity = mediaGroupRepository.save(entity);

        log.info("MediaGroup updated with id {}", entity.getId());

        return entity;
    }

    public void deleteById(UUID id) {

        MediaGroupEntity entity = mediaGroupRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Group not found " + id));

        mediaGroupRepository.delete(entity);

        log.info("MediaGroup deleted with id {}", id);
    }

    @Transactional(readOnly = true)
    public List<MediaGroupEntity> findByTitle(String title) {

        return mediaGroupRepository.findByTitle(title);
    }
}
