package io.envoi.media.classification.mapping;

import io.envoi.media.classification.dto.request.MediaFormatRequest;
import io.envoi.media.classification.dto.response.MediaFormatResponse;
import io.envoi.media.classification.entity.MediaFormatEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface MediaFormatMapper {

    @Mapping(target = "name", source = "defaultName")
    MediaFormatResponse toResponse(MediaFormatEntity mediaFormat);

    @Mapping(target = "id",                 ignore = true)
    @Mapping(target = "mediaCategory.id",   source = "mediaCategoryId")
    MediaFormatEntity toEntity(MediaFormatRequest mediaFormatRequest);

    @Mapping(target = "id",                 ignore = true)
    void update(
            MediaFormatRequest request,
            @MappingTarget MediaFormatEntity entity
    );
}
