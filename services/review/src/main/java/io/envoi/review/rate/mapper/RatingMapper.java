package io.envoi.review.rate.mapper;

import io.envoi.review.rate.dto.request.RatingRequest;
import io.envoi.review.rate.dto.response.RatingResponse;
import io.envoi.review.rate.entity.RatingEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RatingMapper {

    @Mapping(target = "mediaItemId",        source = "mediaItemReplica.id")
    @Mapping(target = "mediaGroupId",       source = "mediaGroupReplica.id")
    RatingResponse toResponse(RatingEntity ratingEntity);

    @Mapping(target = "mediaItemReplica",        ignore = true)
    @Mapping(target = "mediaGroupReplica",      ignore = true)
    RatingEntity toEntity(RatingRequest ratingRequest);

    @Mapping(target = "mediaItemReplica",        ignore = true)
    @Mapping(target = "mediaGroupReplica",      ignore = true)
    void update(
            RatingRequest request,
            @MappingTarget RatingEntity entity
    );
}
