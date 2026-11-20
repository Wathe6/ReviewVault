package io.envoi.review.rate.mapper;

import io.envoi.review.rate.dto.request.UserRatingPreferenceRequest;
import io.envoi.review.rate.dto.response.UserRatingPreferenceResponse;
import io.envoi.review.rate.entity.UserRatingPreferenceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserRatingPreferenceMapper {

    UserRatingPreferenceResponse toResponse(UserRatingPreferenceEntity entity);

    UserRatingPreferenceEntity toEntity(UserRatingPreferenceRequest request);

    void update(
            UserRatingPreferenceRequest request,
            @MappingTarget UserRatingPreferenceEntity entity
    );
}
