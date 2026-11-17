package io.envoi.library.collection.mapper;

import io.envoi.library.collection.dto.request.UserFolderRequest;
import io.envoi.library.collection.dto.response.UserFolderResponse;
import io.envoi.library.collection.entity.UserFolderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserFolderMapper {

    UserFolderResponse toResponse(UserFolderEntity userFolderEntity);

    UserFolderEntity toEntity(UserFolderRequest userFolderResponse);

    void update(
            UserFolderRequest request,
            @MappingTarget UserFolderEntity entity
    );
}
