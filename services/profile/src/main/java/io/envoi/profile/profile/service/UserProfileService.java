package io.envoi.profile.profile.service;

import io.envoi.profile.messaging.mapping.UserProfileEventMapper;
import io.envoi.profile.profile.dto.request.UserProfileRequest;
import io.envoi.profile.profile.entity.UserProfileEntity;
import io.envoi.profile.profile.mapper.UserProfileMapper;
import io.envoi.profile.profile.repository.UserProfileRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserProfileService {

    private final UserProfileRepository userProfileRepository;

    private final UserProfileMapper userProfileMapper;

    private final ApplicationEventPublisher applicationEventPublisher;

    private final UserProfileEventMapper eventMapper;

    @Transactional(readOnly = true)
    public UserProfileEntity findById(UUID id) {

        return userProfileRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("UserProfile not found " + id));
    }

    public UserProfileEntity findByKeycloakUserId(UUID keycloakUserId, UserProfileRequest request) {
        Optional<UserProfileEntity> entity = userProfileRepository.findByKeycloakUserId(keycloakUserId);

        if(entity.isEmpty()) {

            log.info("UserProfile created new profile for keycloakUserId {}", keycloakUserId);

            return create(request,  keycloakUserId);
        }

        return entity.get();
    }

    public UserProfileEntity create(UserProfileRequest request, UUID keycloakUserId) {

        UserProfileEntity entity = userProfileMapper.toEntity(request);

        entity.setKeycloakUserId(keycloakUserId);

        entity = userProfileRepository.save(entity);

        log.info("UserProfile created with id {}", entity.getId());

        applicationEventPublisher.publishEvent(
                eventMapper.toCreatedV1(entity)
        );

        return entity;
    }

    public UserProfileEntity update(UUID id, UserProfileRequest request) {

        UserProfileEntity entity = userProfileRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("UserProfile not found " + id));

        userProfileMapper.update(request,entity);

        entity = userProfileRepository.save(entity);

        log.info("UserProfile updated with id {}", entity.getId());

        applicationEventPublisher.publishEvent(
                eventMapper.toUpdatedV1(entity)
        );

        return entity;
    }

    public void deleteById(UUID id) {

        UserProfileEntity entity = userProfileRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("UserProfile not found " + id));

        entity.setDeleted(true);
        userProfileRepository.save(entity);

        log.info("UserProfile deleted with id {}", entity.getId());

        applicationEventPublisher.publishEvent(
                eventMapper.toDeletedV1(entity)
        );
    }
}
