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

        entity.setUserProfileReplica(userProfileReplicaRepository.findById(userProfileReplicaId)
                .orElseThrow(() -> new EntityNotFoundException("UserRatingDescriptionEntity not found " + userProfileReplicaId)));

        updateNormalizedValues(entity);

        entity = userRatingDescriptionRepository.save(entity);

        log.info("UserRatingDescriptionEntity saved with id {}", entity.getId());

        return entity;
    }

    public UserRatingDescriptionEntity update(Long id, UUID userProfileId, UserRatingDescriptionRequest request) {

        UserRatingDescriptionEntity entity = userRatingDescriptionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("UserRatingDescriptionEntity not found " + id));

        if(!entity.getUserProfileReplica().getId().equals(userProfileId)) {
            throw new RuntimeException("Trying to update rating of another user with id " + userProfileId);
        }

        userRatingDescriptionMapper.update(request, entity);

        updateNormalizedValues(entity);

        log.info("UserRatingDescriptionEntity updated with id {}", entity.getId());

        return userRatingDescriptionRepository.save(entity);
    }

    private void updateNormalizedValues(UserRatingDescriptionEntity entity) {

        UserRatingPreferenceEntity userRatingPreference = userRatingPreferenceRepository.findByUserProfileReplica(entity.getUserProfileReplica())
                .orElseThrow(() -> new EntityNotFoundException("UserRatingPreferenceEntity not found " + entity.getUserProfileReplica()));

        RatingScaleEntity ratingScale = userRatingPreference.getRatingScaleEntity();

        entity.setNormalizedLeft(ratingScale.normalize(entity.getLeft()));
        entity.setNormalizedRight(ratingScale.normalize(entity.getRight()));
    }

    public void deleteById(Long id) {

        UserRatingDescriptionEntity entity = userRatingDescriptionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("UserRatingDescriptionEntity not found " + id));

        userRatingDescriptionRepository.delete(entity);

        log.info("UserRatingDescriptionEntity deleted with id {}", entity.getId());
    }
}
