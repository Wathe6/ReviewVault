package io.envoi.review.rate.mapper;

import io.envoi.review.rate.dto.request.UserRatingPreferenceRequest;
import io.envoi.review.rate.dto.response.UserRatingPreferenceResponse;
import io.envoi.review.rate.entity.UserRatingPreferenceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserRatingPreferenceMapper {

    @Mapping(target = "ratingScaleId", source = "ratingScaleEntity.id")
    UserRatingPreferenceResponse toResponse(UserRatingPreferenceEntity entity);

    @Mapping(target = "ratingScaleEntity.id", source = "ratingScaleId")
    UserRatingPreferenceEntity toEntity(UserRatingPreferenceRequest request);
}
