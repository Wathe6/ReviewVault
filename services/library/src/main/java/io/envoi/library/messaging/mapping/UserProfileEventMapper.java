package io.envoi.library.messaging.mapping;

import io.envoi.contracts.profile.v1.UserProfileCreatedV1;
import io.envoi.contracts.profile.v1.UserProfileDeletedV1;
import io.envoi.contracts.profile.v1.UserProfileUpdatedV1;
import io.envoi.library.replica.entity.UserProfileReplicaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserProfileEventMapper {

    @Mapping(target = "id", source = "data.id")
    @Mapping(target = "lastEventId", source = "metadata.eventId")
    @Mapping(target = "lastEventAt", source = "metadata.occurredAt")
    @Mapping(target = "deleted", expression = "java(Boolean.FALSE)")
    UserProfileReplicaEntity fromCreatedV1(UserProfileCreatedV1 userProfileCreatedV1);

    @Mapping(target = "id", source = "data.id")
    @Mapping(target = "lastEventId", source = "metadata.eventId")
    @Mapping(target = "lastEventAt", source = "metadata.occurredAt")
    @Mapping(target = "deleted", expression = "java(Boolean.FALSE)")
    UserProfileReplicaEntity fromUpdatedV1(UserProfileUpdatedV1 userProfileUpdatedV1);

    @Mapping(target = "id", source = "data.id")
    @Mapping(target = "lastEventId", source = "metadata.eventId")
    @Mapping(target = "lastEventAt", source = "metadata.occurredAt")
    @Mapping(target = "deleted", expression = "java(Boolean.TRUE)")
    UserProfileReplicaEntity fromDeletedV1(UserProfileDeletedV1 userProfileDeletedV1);
}
