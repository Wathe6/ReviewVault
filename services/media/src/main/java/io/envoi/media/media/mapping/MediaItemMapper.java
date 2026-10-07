package io.envoi.media.media.mapping;

import io.envoi.media.media.dto.request.MediaItemRequest;
import io.envoi.media.media.dto.response.MediaItemResponse;
import io.envoi.media.media.entity.MediaItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface MediaItemMapper {

    @Mapping(target = "mediaGroupId",           source = "mediaGroup.id")
    @Mapping(target = "mediaFormatId",          source = "mediaFormat.id")
    @Mapping(target = "mediaCategoryId",        source = "mediaCategory.id")
    @Mapping(target = "mediaItemStatusId",      source = "mediaItemStatus.id")
    @Mapping(target = "originalLanguageCode",   source = "originalLanguage.code")
    MediaItemResponse toResponse(MediaItemEntity mediaItem);

    @Mapping(target = "id",                 ignore = true)
    @Mapping(target = "mediaGroup", ignore = true)
    @Mapping(target = "mediaFormat", ignore = true)
    @Mapping(target = "mediaCategory", ignore = true)
    @Mapping(target = "mediaItemStatus", ignore = true)
    @Mapping(target = "originalLanguage", ignore = true)
    MediaItemEntity toEntity(MediaItemRequest mediaItemRequest);

    @Mapping(target = "id",                 ignore = true)
    @Mapping(target = "mediaGroup",         ignore = true)
    @Mapping(target = "mediaFormat",        ignore = true)
    @Mapping(target = "mediaCategory",      ignore = true)
    @Mapping(target = "mediaItemStatus",    ignore = true)
    @Mapping(target = "originalLanguage",   ignore = true)
    void update(
            MediaItemRequest request,
            @MappingTarget MediaItemEntity entity
    );
}
