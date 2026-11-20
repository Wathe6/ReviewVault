package io.envoi.replica.mediagroup.consumer;

import io.envoi.contracts.media.v1.group.MediaGroupCreatedV1;
import io.envoi.contracts.media.v1.group.MediaGroupDeletedV1;
import io.envoi.contracts.media.v1.group.MediaGroupUpdatedV1;
import io.envoi.replica.mediagroup.entity.MediaGroupReplicaEntity;
import io.envoi.replica.mediagroup.mapper.MediaGroupEventMapper;
import io.envoi.replica.mediagroup.service.MediaGroupReplicaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaMediaGroupConsumerService {

    private final MediaGroupReplicaService replicaService;

    private final MediaGroupEventMapper mapper;

    private final ObjectMapper objectMapper;

    @KafkaListener(
            topics = "${app.kafka.topics.media-group-events}",
            groupId = "${spring.kafka.consumers.media-group-consumer.group-id}"
    )
    public void listen(JsonNode message) {
        String type = message.path("metadata").path("eventType").asText();

        MediaGroupReplicaEntity entity;

        switch (type) {
            case "MEDIA_GROUP_CREATED" -> {

                MediaGroupCreatedV1 createdV1 = objectMapper.convertValue(message, MediaGroupCreatedV1.class);

                entity = mapper.fromCreatedV1(createdV1);

                replicaService.save(entity);
            }
            case "MEDIA_GROUP_UPDATED" -> {

                MediaGroupUpdatedV1 updatedV1 = objectMapper.convertValue(message, MediaGroupUpdatedV1.class);

                entity = mapper.fromUpdatedV1(updatedV1);

                replicaService.update(entity);
            }
            case "MEDIA_GROUP_DELETED" -> {

                MediaGroupDeletedV1 deletedV1 = objectMapper.convertValue(message, MediaGroupDeletedV1.class);

                entity = mapper.fromDeletedV1(deletedV1);

                replicaService.delete(entity);
            }
            default -> throw new IllegalArgumentException("Unknown event type: " + type);
        }

        log.info("Listen received event type={}, entityId={}", type, entity.getId());
    }

}
