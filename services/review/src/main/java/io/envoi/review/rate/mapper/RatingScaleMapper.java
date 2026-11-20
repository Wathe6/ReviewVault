package io.envoi.review.rate.mapper;

import io.envoi.review.rate.dto.response.RatingScaleResponse;
import io.envoi.review.rate.entity.RatingScaleEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RatingScaleMapper {

    RatingScaleResponse toResponse(RatingScaleEntity ratingScaleEntity);
}
