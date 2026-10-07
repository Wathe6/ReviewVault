package io.envoi.media.classification.mapping;

import io.envoi.media.classification.dto.request.MediaCategoryRequest;
import io.envoi.media.classification.dto.response.MediaCategoryResponse;
import io.envoi.media.classification.entity.MediaCategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface MediaCategoryMapper {

    @Mapping(target = "name", source = "defaultName")
    MediaCategoryResponse toResponse(MediaCategoryEntity mediaCategory);

    @Mapping(target = "id",           ignore = true)
    MediaCategoryEntity toEntity(MediaCategoryRequest mediaCategoryRequest);

    @Mapping(target = "id",           ignore = true)
    void update(
            MediaCategoryRequest request,
            @MappingTarget MediaCategoryEntity entity
    );
}
