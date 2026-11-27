package io.envoi.review.rate.service;

import io.envoi.replica.mediagroup.repository.MediaGroupReplicaRepository;
import io.envoi.replica.mediaitem.repository.MediaItemReplicaRepository;
import io.envoi.replica.profile.repository.UserProfileReplicaRepository;
import io.envoi.review.rate.dto.request.RatingRequest;
import io.envoi.review.rate.entity.RatingEntity;
import io.envoi.review.rate.entity.RatingScaleEntity;
import io.envoi.review.rate.entity.UserRatingPreferenceEntity;
import io.envoi.review.rate.mapper.RatingMapper;
import io.envoi.review.rate.repository.RatingRepository;
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
public class RatingService {

    private final RatingRepository ratingRepository;

    private final UserRatingPreferenceRepository userRatingPreferenceRepository;

    private final UserProfileReplicaRepository userProfileReplicaRepository;

    private final MediaGroupReplicaRepository mediaGroupReplicaRepository;

    private final MediaItemReplicaRepository mediaItemReplicaRepository;

    private final RatingMapper mapper;

    @Transactional(readOnly = true)
    public RatingEntity findById(Long id) {

        return ratingRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("RatingEntity not found " + id));
    }

    public RatingEntity create(UUID userProfileId, RatingRequest request) {

        RatingEntity entity = mapper.toEntity(request);

        entity.setUserProfileReplica(userProfileReplicaRepository.findById(userProfileId)
                .orElseThrow(() -> new EntityNotFoundException("UserProfileReplica not found " + userProfileId)));

        updateFk(entity, request);

        updateNormalizedValue(entity);

        entity = ratingRepository.save(entity);

        log.info("RatingEntity saved with id {}", entity.getId());

        return entity;
    }

    public RatingEntity update(Long id, UUID userProfileId, RatingRequest request) {

        RatingEntity entity = ratingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("RatingEntity not found " + id));

        if(!entity.getUserProfileReplica().getId().equals(userProfileId)) {
            throw new RuntimeException("Trying to update rating of another user with id " + userProfileId);
        }

        mapper.update(request, entity);

        updateFk(entity, request);

        updateNormalizedValue(entity);

        entity = ratingRepository.save(entity);

        log.info("RatingEntity updated with id {}", entity.getId());

        return entity;
    }

    private void updateNormalizedValue(RatingEntity entity) {

        UserRatingPreferenceEntity userRatingPreference =
                userRatingPreferenceRepository.findByUserProfileReplica(entity.getUserProfileReplica())
                        .orElseThrow(() -> new EntityNotFoundException("UserRatingPreference not found " + entity.getUserProfileReplica().getId()));

        RatingScaleEntity ratingScale = userRatingPreference.getRatingScaleEntity();

        entity.setNormalizedValue(ratingScale.normalize(entity.getValue()));
    }

    private void updateFk(RatingEntity entity, RatingRequest request) {

        if(request.mediaGroupId() != null) {
            entity.setMediaGroupReplica(mediaGroupReplicaRepository.findById(request.mediaGroupId())
                    .orElseThrow(() -> new EntityNotFoundException("MediaGroupReplica not found " + request.mediaGroupId())));
        }

        if(request.mediaItemId() != null) {
            entity.setMediaItemReplica(mediaItemReplicaRepository.findById(request.mediaItemId())
                    .orElseThrow(() -> new EntityNotFoundException("MediaItemReplica not found " + request.mediaItemId())));
        }
    }

    public void deleteById(Long id) {

        RatingEntity entity = ratingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("RatingEntity not found " + id));

        ratingRepository.delete(entity);

        log.info("RatingEntity deleted with id {}", id);
    }
}
