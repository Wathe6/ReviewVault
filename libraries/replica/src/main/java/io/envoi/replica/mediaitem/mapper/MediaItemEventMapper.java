package io.envoi.replica.mediaitem.mapper;

import io.envoi.contracts.media.v1.item.MediaItemCreatedV1;
import io.envoi.contracts.media.v1.item.MediaItemDeletedV1;
import io.envoi.contracts.media.v1.item.MediaItemUpdatedV1;
import io.envoi.replica.mediaitem.entity.MediaItemReplicaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MediaItemEventMapper {

    @Mapping(target = "id", source = "data.id")
    @Mapping(target = "title", source = "data.title")
    @Mapping(target = "coverUrl", source = "data.coverUrl")
    @Mapping(target = "lastEventId", source = "metadata.eventId")
    @Mapping(target = "lastEventAt", source = "metadata.occurredAt")
    @Mapping(target = "deleted", expression = "java(Boolean.FALSE)")
    MediaItemReplicaEntity fromCreatedV1(MediaItemCreatedV1 mediaItemCreatedV1);

    @Mapping(target = "id", source = "data.id")
    @Mapping(target = "title", source = "data.title")
    @Mapping(target = "coverUrl", source = "data.coverUrl")
    @Mapping(target = "lastEventId", source = "metadata.eventId")
    @Mapping(target = "lastEventAt", source = "metadata.occurredAt")
    @Mapping(target = "deleted", expression = "java(Boolean.FALSE)")
    MediaItemReplicaEntity fromUpdatedV1(MediaItemUpdatedV1 mediaItemUpdatedV1);

    @Mapping(target = "id", source = "data.id")
    @Mapping(target = "title", source = "data.title")
    @Mapping(target = "coverUrl", source = "data.coverUrl")
    @Mapping(target = "lastEventId", source = "metadata.eventId")
    @Mapping(target = "lastEventAt", source = "metadata.occurredAt")
    @Mapping(target = "deleted", expression = "java(Boolean.TRUE)")
    MediaItemReplicaEntity fromDeletedV1(MediaItemDeletedV1 mediaItemDeletedV1);
}
