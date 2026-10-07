package io.envoi.review.messaging.mapping;

import io.envoi.contracts.media.v1.MediaItemCreatedV1;
import io.envoi.contracts.media.v1.MediaItemDeletedV1;
import io.envoi.contracts.media.v1.MediaItemUpdatedV1;
import io.envoi.review.replica.entity.MediaItemReplicaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MediaItemEventMapper {

    @Mapping(target = "id", source = "data.id")
    @Mapping(target = "title", source = "data.title")
    @Mapping(target = "lastEventId", source = "metadata.eventId")
    @Mapping(target = "lastEventAt", source = "metadata.occurredAt")
    @Mapping(target = "deleted", expression = "java(Boolean.FALSE)")
    MediaItemReplicaEntity fromCreatedV1(MediaItemCreatedV1 mediaItemCreatedV1);

    @Mapping(target = "id", source = "data.id")
    @Mapping(target = "title", source = "data.title")
    @Mapping(target = "lastEventId", source = "metadata.eventId")
    @Mapping(target = "lastEventAt", source = "metadata.occurredAt")
    @Mapping(target = "deleted", expression = "java(Boolean.FALSE)")
    MediaItemReplicaEntity fromUpdatedV1(MediaItemUpdatedV1 mediaItemUpdatedV1);

    @Mapping(target = "id", source = "data.id")
    @Mapping(target = "title", source = "data.title")
    @Mapping(target = "lastEventId", source = "metadata.eventId")
    @Mapping(target = "lastEventAt", source = "metadata.occurredAt")
    @Mapping(target = "deleted", expression = "java(Boolean.TRUE)")
    MediaItemReplicaEntity fromDeletedV1(MediaItemDeletedV1 mediaItemDeletedV1);
}
