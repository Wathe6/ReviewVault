package io.envoi.media.media.service;

import io.envoi.media.classification.repository.MediaCategoryRepository;
import io.envoi.media.classification.repository.MediaFormatRepository;
import io.envoi.media.common.repository.LanguageRepository;
import io.envoi.media.media.dto.request.MediaItemRequest;
import io.envoi.media.media.entity.MediaItemEntity;
import io.envoi.media.media.mapping.MediaItemMapper;
import io.envoi.media.media.repository.MediaGroupRepository;
import io.envoi.media.media.repository.MediaItemRepository;
import io.envoi.media.media.repository.MediaItemStatusRepository;
import io.envoi.media.messaging.mapping.MediaItemEventMapper;
import io.envoi.media.messaging.producer.KafkaMediaItemProducerService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.print.attribute.standard.Media;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MediaItemService {

    private final MediaItemRepository mediaItemRepository;

    private final MediaGroupRepository mediaGroupRepository;

    private final MediaFormatRepository mediaFormatRepository;

    private final MediaCategoryRepository mediaCategoryRepository;

    private final MediaItemStatusRepository mediaItemStatusRepository;

    private final LanguageRepository languageRepository;

    private final MediaItemMapper mapper;

    private final ApplicationEventPublisher applicationEventPublisher;

    private final MediaItemEventMapper eventMapper;

    @Transactional(readOnly = true)
    public MediaItemEntity findById(UUID id) {

        return mediaItemRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("MediaItem not found " + id));
    }

    public MediaItemEntity create(MediaItemRequest request) {

        MediaItemEntity entity = mapper.toEntity(request);

        entity.setMediaGroup(
                request.mediaGroupId() == null
                        ? null
                        : mediaGroupRepository.findById(request.mediaGroupId())
                        .orElseThrow(() ->
                                new EntityNotFoundException(
                                        "MediaGroup not found " + request.mediaGroupId()
                                )
                        )
        );

        entity.setMediaFormat(mediaFormatRepository.findById(request.mediaFormatId())
                .orElseThrow(() -> new EntityNotFoundException("MediaFormat not found " + request.mediaFormatId())));

        entity.setMediaCategory(mediaCategoryRepository.findById(request.mediaCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("MediaCategory not found " + request.mediaCategoryId())));

        entity.setMediaItemStatus(mediaItemStatusRepository.findById(request.mediaItemStatusId())
                .orElseThrow(() -> new EntityNotFoundException("MediaItemStatus not found " + request.mediaItemStatusId())));

        entity.setOriginalLanguage(languageRepository.findByCode(request.originalLanguageCode())
                .orElseThrow(() -> new EntityNotFoundException("Language not found " + request.originalLanguageCode())));

        entity = mediaItemRepository.save(entity);

        log.info("MediaItem saved with id {}", entity.getId());

        applicationEventPublisher.publishEvent(eventMapper.toCreatedV1(entity));

        return entity;
    }

    public MediaItemEntity update(UUID id, MediaItemRequest request) {

        MediaItemEntity entity = mediaItemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("MediaItem not found " + id));

        mapper.update(request, entity);

        entity.setMediaGroup(
                request.mediaGroupId() == null
                        ? null
                        : mediaGroupRepository.findById(request.mediaGroupId())
                        .orElseThrow(() ->
                                new EntityNotFoundException(
                                        "MediaGroup not found " + request.mediaGroupId()
                                )
                        )
        );

        entity.setMediaFormat(mediaFormatRepository.findById(request.mediaFormatId())
                .orElseThrow(() -> new EntityNotFoundException("MediaFormat not found " + request.mediaFormatId())));

        entity.setMediaCategory(mediaCategoryRepository.findById(request.mediaCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("MediaCategory not found " + request.mediaCategoryId())));

        entity.setMediaItemStatus(mediaItemStatusRepository.findById(request.mediaItemStatusId())
                .orElseThrow(() -> new EntityNotFoundException("MediaItemStatus not found " + request.mediaItemStatusId())));

        entity.setOriginalLanguage(languageRepository.findByCode(request.originalLanguageCode())
                .orElseThrow(() -> new EntityNotFoundException("Language not found " + request.originalLanguageCode())));

        entity = mediaItemRepository.save(entity);

        log.info("MediaItem updated with id {}", entity.getId());

        applicationEventPublisher.publishEvent(eventMapper.toUpdatedV1(entity));

        return entity;
    }

    public void deleteById(UUID id) {

        MediaItemEntity entity = mediaItemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("MediaItem not found " + id));

        mediaItemRepository.delete(entity);

        log.info("MediaItem deleted with id {}", id);

        applicationEventPublisher.publishEvent(eventMapper.toDeletedV1(entity));
    }
}
