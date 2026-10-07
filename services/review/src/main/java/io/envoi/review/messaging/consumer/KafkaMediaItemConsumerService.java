package io.envoi.review.messaging.consumer;

import tools.jackson.databind.ObjectMapper;
import io.envoi.contracts.media.v1.MediaItemCreatedV1;
import io.envoi.contracts.media.v1.MediaItemDeletedV1;
import io.envoi.contracts.media.v1.MediaItemUpdatedV1;
import io.envoi.review.messaging.mapping.MediaItemEventMapper;
import io.envoi.review.replica.entity.MediaItemReplicaEntity;
import io.envoi.review.replica.service.MediaItemReplicaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import tools.jackson.databind.JsonNode;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaMediaItemConsumerService {

    private final MediaItemReplicaService replicaService;

    private final MediaItemEventMapper mapper;

    private final ObjectMapper objectMapper;

    @KafkaListener(
            topics = "${app.kafka.topics.media-item-events}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void listen(JsonNode message) {
        String type = message.path("metadata").path("eventType").asText();

        MediaItemReplicaEntity entity;

        switch (type) {
            case "MEDIA_ITEM_CREATED" -> {

                MediaItemCreatedV1 createdV1 = objectMapper.convertValue(message, MediaItemCreatedV1.class);

                entity = mapper.fromCreatedV1(createdV1);

                replicaService.save(entity);
            }
            case "MEDIA_ITEM_UPDATED" -> {

                MediaItemUpdatedV1 updatedV1 = objectMapper.convertValue(message, MediaItemUpdatedV1.class);

                entity = mapper.fromUpdatedV1(updatedV1);

                replicaService.update(entity);
            }
            case "MEDIA_ITEM_DELETED" -> {

                MediaItemDeletedV1 deletedV1 = objectMapper.convertValue(message, MediaItemDeletedV1.class);

                entity = mapper.fromDeletedV1(deletedV1);

                replicaService.delete(entity);
            }
            default -> throw new IllegalArgumentException("Unknown event type: " + type);
        }

        log.info("Listen received event type={}, entityId={}", type, entity.getId());
    }

}
