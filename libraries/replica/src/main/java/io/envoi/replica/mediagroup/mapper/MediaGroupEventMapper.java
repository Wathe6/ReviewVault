package io.envoi.replica.mediagroup.mapper;

import io.envoi.contracts.media.v1.group.MediaGroupCreatedV1;
import io.envoi.contracts.media.v1.group.MediaGroupDeletedV1;
import io.envoi.contracts.media.v1.group.MediaGroupUpdatedV1;
import io.envoi.replica.mediagroup.entity.MediaGroupReplicaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MediaGroupEventMapper {

    @Mapping(target = "id", source = "data.id")
    @Mapping(target = "title", source = "data.title")
    @Mapping(target = "coverUrl", source = "data.coverUrl")
    @Mapping(target = "lastEventId", source = "metadata.eventId")
    @Mapping(target = "lastEventAt", source = "metadata.occurredAt")
    @Mapping(target = "deleted", expression = "java(Boolean.FALSE)")
    MediaGroupReplicaEntity fromCreatedV1(MediaGroupCreatedV1 mediaGroupCreatedV1);

    @Mapping(target = "id", source = "data.id")
    @Mapping(target = "title", source = "data.title")
    @Mapping(target = "coverUrl", source = "data.coverUrl")
    @Mapping(target = "lastEventId", source = "metadata.eventId")
    @Mapping(target = "lastEventAt", source = "metadata.occurredAt")
    @Mapping(target = "deleted", expression = "java(Boolean.FALSE)")
    MediaGroupReplicaEntity fromUpdatedV1(MediaGroupUpdatedV1 mediaGroupUpdatedV1);

    @Mapping(target = "id", source = "data.id")
    @Mapping(target = "title", source = "data.title")
    @Mapping(target = "coverUrl", source = "data.coverUrl")
    @Mapping(target = "lastEventId", source = "metadata.eventId")
    @Mapping(target = "lastEventAt", source = "metadata.occurredAt")
    @Mapping(target = "deleted", expression = "java(Boolean.TRUE)")
    MediaGroupReplicaEntity fromDeletedV1(MediaGroupDeletedV1 mediaGroupDeletedV1);
}
