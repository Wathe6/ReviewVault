package io.envoi.media.media.mapping;

import io.envoi.media.media.dto.request.MediaGroupRequest;
import io.envoi.media.media.dto.response.MediaCardResponse;
import io.envoi.media.media.dto.response.MediaGroupResponse;
import io.envoi.media.media.entity.MediaGroupEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface MediaGroupMapper {

    @Mapping(
            target = "originalLanguageCode",
            source = "originalLanguage.code"
    )
    MediaGroupResponse toResponse(MediaGroupEntity mediaGroup);

    @Mapping(target = "id",                 ignore = true)
    MediaGroupEntity toEntity(MediaGroupRequest request);

    MediaCardResponse toCard(MediaGroupEntity mediaGroup);

    @Mapping(target = "id",                 ignore = true)
    @Mapping(target = "originalLanguage",   ignore = true)
    void update(
            MediaGroupRequest request,
            @MappingTarget MediaGroupEntity entity
    );
}
