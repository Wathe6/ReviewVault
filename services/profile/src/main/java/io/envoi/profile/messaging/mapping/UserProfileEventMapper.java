package io.envoi.profile.messaging.mapping;

import io.envoi.contracts.profile.v1.UserProfileCreatedV1;
import io.envoi.contracts.profile.v1.UserProfileDeletedV1;
import io.envoi.contracts.profile.v1.UserProfileUpdatedV1;
import io.envoi.profile.profile.entity.UserProfileEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserProfileEventMapper {

    @Mapping(target = "data.id", source = "entity.id")
    @Mapping(target = "metadata.eventId", expression = "java(UUID.randomUUID())")
    @Mapping(target = "metadata.eventType", constant = "USER_PROFILE_CREATED")
    @Mapping(target = "metadata.eventVersion", constant = "1")
    @Mapping(target = "metadata.occurredAt", expression = "java(Instant.now())")
    @Mapping(target = "metadata.aggregateId", source = "entity.id")
    UserProfileCreatedV1 toCreatedV1(UserProfileEntity entity);

    @Mapping(target = "data.id", source = "entity.id")
    @Mapping(target = "metadata.eventId", expression = "java(UUID.randomUUID())")
    @Mapping(target = "metadata.eventType", constant = "USER_PROFILE_DELETED")
    @Mapping(target = "metadata.eventVersion", constant = "1")
    @Mapping(target = "metadata.occurredAt", expression = "java(Instant.now())")
    @Mapping(target = "metadata.aggregateId", source = "entity.id")
    UserProfileDeletedV1 toDeletedV1(UserProfileEntity entity);

    @Mapping(target = "data.id", source = "entity.id")
    @Mapping(target = "metadata.eventId", expression = "java(UUID.randomUUID())")
    @Mapping(target = "metadata.eventType", constant = "USER_PROFILE_UPDATED")
    @Mapping(target = "metadata.eventVersion", constant = "1")
    @Mapping(target = "metadata.occurredAt", expression = "java(Instant.now())")
    @Mapping(target = "metadata.aggregateId", source = "entity.id")
    UserProfileUpdatedV1 toUpdatedV1(UserProfileEntity entity);
}
