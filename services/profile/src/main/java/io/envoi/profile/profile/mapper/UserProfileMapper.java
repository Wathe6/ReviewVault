package io.envoi.profile.profile.mapper;

import io.envoi.profile.profile.dto.request.UserProfileRequest;
import io.envoi.profile.profile.dto.response.UserProfileResponse;
import io.envoi.profile.profile.entity.UserProfileEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserProfileMapper {

    UserProfileResponse toResponse(UserProfileEntity userProfileEntity);

    @Mapping(target = "deleted", constant = "false")
    UserProfileEntity toEntity(UserProfileRequest request);

    void update(
            UserProfileRequest request,
            @MappingTarget UserProfileEntity entity
    );
}
