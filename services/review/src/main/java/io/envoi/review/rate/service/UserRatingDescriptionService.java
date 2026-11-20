package io.envoi.review.rate.service;

import io.envoi.replica.profile.repository.UserProfileReplicaRepository;
import io.envoi.review.rate.dto.request.UserRatingDescriptionRequest;
import io.envoi.review.rate.entity.RatingScaleEntity;
import io.envoi.review.rate.entity.UserRatingDescriptionEntity;
import io.envoi.review.rate.entity.UserRatingPreferenceEntity;
import io.envoi.review.rate.mapper.UserRatingDescriptionMapper;
import io.envoi.review.rate.repository.RatingScaleRepository;
import io.envoi.review.rate.repository.UserRatingDescriptionRepository;
import io.envoi.review.rate.repository.UserRatingPreferenceRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserRatingDescriptionService {

    private final UserRatingDescriptionRepository userRatingDescriptionRepository;

    private final UserProfileReplicaRepository userProfileReplicaRepository;

    private final UserRatingPreferenceRepository userRatingPreferenceRepository;

    private final UserRatingDescriptionMapper userRatingDescriptionMapper;

    @Transactional(readOnly = true)
    public UserRatingDescriptionEntity findById(Long id) {

        return userRatingDescriptionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("UserRatingDescriptionEntity not found " + id));
    }

    public UserRatingDescriptionEntity create(UUID userProfileReplicaId, UserRatingDescriptionRequest request) {

        UserRatingDescriptionEntity entity = userRatingDescriptionMapper.toEntity(request);

        entity.setUserProfileId(userProfileReplicaRepository.findById(userProfileReplicaId)
                .orElseThrow(() -> new EntityNotFoundException("UserRatingDescriptionEntity not found " + userProfileReplicaId)));

        UserRatingPreferenceEntity userRatingPreference = userRatingPreferenceRepository.findByUserProfileReplicaId(entity.getUserProfileId())
                .orElseThrow(() -> new EntityNotFoundException("UserRatingPreferenceEntity not found " + userProfileReplicaId));

        RatingScaleEntity ratingScale = userRatingPreference.getRatingScaleEntity();

        entity.setNormalizedLeft(ratingScale.normalize(entity.getLeft()));
        entity.setNormalizedRight(ratingScale.normalize(entity.getRight()));

        entity = userRatingDescriptionRepository.save(entity);

        log.info("UserRatingDescriptionEntity saved with id {}", entity.getId());

        return entity;
    }

    public UserRatingDescriptionEntity update(Long id, UserRatingDescriptionRequest request) {

        UserRatingDescriptionEntity entity = userRatingDescriptionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("UserRatingDescriptionEntity not found " + id));

        userRatingDescriptionMapper.update(request, entity);

        UserRatingPreferenceEntity userRatingPreference = userRatingPreferenceRepository.findByUserProfileReplicaId(entity.getUserProfileId())
                .orElseThrow(() -> new EntityNotFoundException("UserRatingPreferenceEntity not found " + entity.getUserProfileId()));

        RatingScaleEntity ratingScale = userRatingPreference.getRatingScaleEntity();

        entity.setNormalizedLeft(ratingScale.normalize(entity.getLeft()));
        entity.setNormalizedRight(ratingScale.normalize(entity.getRight()));


        log.info("UserRatingDescriptionEntity updated with id {}", entity.getId());

        return userRatingDescriptionRepository.save(entity);
    }

    public void deleteById(Long id) {

        UserRatingDescriptionEntity entity = userRatingDescriptionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("UserRatingDescriptionEntity not found " + id));

        userRatingDescriptionRepository.delete(entity);

        log.info("UserRatingDescriptionEntity deleted with id {}", entity.getId());
    }
}
