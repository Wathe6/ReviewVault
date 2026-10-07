package io.envoi.media.messaging.mapping;

import io.envoi.contracts.media.v1.MediaItemCreatedV1;
import io.envoi.contracts.media.v1.MediaItemDeletedV1;
import io.envoi.contracts.media.v1.MediaItemUpdatedV1;
import io.envoi.media.media.entity.MediaItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MediaItemEventMapper {

    @Mapping(target = "data.id", source = "entity.id")
    @Mapping(target = "data.title", source = "entity.title")
    @Mapping(target = "data.coverUrl", source = "entity.coverUrl")
    @Mapping(target = "metadata.eventId", expression = "java(UUID.randomUUID())")
    @Mapping(target = "metadata.eventType", constant = "MEDIA_ITEM_CREATED")
    @Mapping(target = "metadata.eventVersion", constant = "1")
    @Mapping(target = "metadata.occurredAt", expression = "java(Instant.now())")
    @Mapping(target = "metadata.aggregateId", source = "entity.id")
    MediaItemCreatedV1 toCreatedV1(MediaItemEntity entity);

    @Mapping(target = "data.id", source = "entity.id")
    @Mapping(target = "data.title", source = "entity.title")
    @Mapping(target = "data.coverUrl", source = "entity.coverUrl")
    @Mapping(target = "metadata.eventId", expression = "java(UUID.randomUUID())")
    @Mapping(target = "metadata.eventType", constant = "MEDIA_ITEM_DELETED")
    @Mapping(target = "metadata.eventVersion", constant = "1")
    @Mapping(target = "metadata.occurredAt", expression = "java(Instant.now())")
    @Mapping(target = "metadata.aggregateId", source = "entity.id")
    MediaItemDeletedV1 toDeletedV1(MediaItemEntity entity);

    @Mapping(target = "data.id", source = "entity.id")
    @Mapping(target = "data.title", source = "entity.title")
    @Mapping(target = "data.coverUrl", source = "entity.coverUrl")
    @Mapping(target = "metadata.eventId", expression = "java(UUID.randomUUID())")
    @Mapping(target = "metadata.eventType", constant = "MEDIA_ITEM_UPDATED")
    @Mapping(target = "metadata.eventVersion", constant = "1")
    @Mapping(target = "metadata.occurredAt", expression = "java(Instant.now())")
    @Mapping(target = "metadata.aggregateId", source = "entity.id")
    MediaItemUpdatedV1 toUpdatedV1(MediaItemEntity entity);
}
