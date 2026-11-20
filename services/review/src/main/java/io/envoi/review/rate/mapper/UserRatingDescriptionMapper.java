package io.envoi.review.rate.mapper;

import io.envoi.review.rate.dto.request.UserRatingDescriptionRequest;
import io.envoi.review.rate.dto.response.UserRatingDescriptionResponse;
import io.envoi.review.rate.entity.UserRatingDescriptionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserRatingDescriptionMapper {

    UserRatingDescriptionResponse toResponse(UserRatingDescriptionEntity entity);

    UserRatingDescriptionEntity toEntity(UserRatingDescriptionRequest request);

    void update(
            UserRatingDescriptionRequest request,
            @MappingTarget UserRatingDescriptionEntity entity
    );
}
