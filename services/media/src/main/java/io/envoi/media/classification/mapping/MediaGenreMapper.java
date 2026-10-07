package io.envoi.media.classification.mapping;

import io.envoi.media.classification.dto.request.MediaGenreRequest;
import io.envoi.media.classification.dto.response.MediaGenreResponse;
import io.envoi.media.classification.entity.MediaGenreEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface MediaGenreMapper {

    @Mapping(target = "name", source = "defaultName")
    MediaGenreResponse toResponse(MediaGenreEntity mediaGenre);

    @Mapping(target = "id",          ignore = true)
    MediaGenreEntity toEntity(MediaGenreRequest mediaGenreRequest);

    @Mapping(target = "id",          ignore = true)
    void update(
            MediaGenreRequest request,
            @MappingTarget MediaGenreEntity entity
    );
}
