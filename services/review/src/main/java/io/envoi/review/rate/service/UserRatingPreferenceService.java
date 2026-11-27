package io.envoi.review.rate.service;

import io.envoi.replica.profile.repository.UserProfileReplicaRepository;
import io.envoi.review.rate.dto.request.UserRatingPreferenceRequest;
import io.envoi.review.rate.entity.RatingEntity;
import io.envoi.review.rate.entity.UserRatingPreferenceEntity;
import io.envoi.review.rate.repository.RatingRepository;
import io.envoi.review.rate.repository.RatingScaleRepository;
import io.envoi.review.rate.repository.UserRatingDescriptionRepository;
import io.envoi.review.rate.repository.UserRatingPreferenceRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserRatingPreferenceService {

    private final UserRatingPreferenceRepository userRatingPreferenceRepository;

    private final RatingScaleRepository ratingScaleRepository;

    private final RatingRepository ratingRepository;

    private final UserRatingDescriptionRepository userRatingDescriptionRepository;

    private final UserProfileReplicaRepository userProfileReplicaRepository;

    @Transactional(readOnly = true)
    public UserRatingPreferenceEntity findById(Long id) {

        return userRatingPreferenceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("UserRatingPreferenceEntity not found " + id));
    }

    public UserRatingPreferenceEntity create(UUID userProfileReplicaId, UserRatingPreferenceRequest request) {

        UserRatingPreferenceEntity entity = new UserRatingPreferenceEntity();

        entity.setRatingScaleEntity(ratingScaleRepository.findById(request.ratingScaleId())
                .orElseThrow(() -> new EntityNotFoundException("RatingScaleEntity not found " + request.ratingScaleId())));

        entity.setUserProfileReplica(userProfileReplicaRepository.findById(userProfileReplicaId)
                .orElseThrow(() -> new EntityNotFoundException("UserProfileReplicaEntity not found " + userProfileReplicaId)));

        entity = userRatingPreferenceRepository.save(entity);

        log.info("UserRatingPreferenceEntity saved with id {}", entity.getId());

        return entity;
    }

    public UserRatingPreferenceEntity update(Long id, UUID userProfileId, UserRatingPreferenceRequest request) {

        UserRatingPreferenceEntity entity = userRatingPreferenceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("UserRatingPreferenceEntity not found " + id));

        if(!entity.getUserProfileReplica().getId().equals(userProfileId)) {
            throw new RuntimeException("Trying to update rating of another user with id " + userProfileId);
        }

        entity.setRatingScaleEntity(ratingScaleRepository.findById(request.ratingScaleId())
                .orElseThrow(() -> new EntityNotFoundException("RatingScaleEntity not found " + request.ratingScaleId())));

        entity = userRatingPreferenceRepository.save(entity);

        ratingRepository.updateValueAfterPreferenceChange(
                entity.getUserProfileReplica().getId(),
                entity.getRatingScaleEntity().getId()
                );

        userRatingDescriptionRepository.updateLeftAndRightAfterPreferenceChange(
                entity.getUserProfileReplica().getId(),
                entity.getRatingScaleEntity().getId()
        );

        log.info("UserRatingPreferenceEntity updated with id {}", entity.getId());

        return entity;
    }
}
