package io.envoi.library.messaging.consumer;

import io.envoi.contracts.profile.v1.UserProfileCreatedV1;
import io.envoi.contracts.profile.v1.UserProfileDeletedV1;
import io.envoi.contracts.profile.v1.UserProfileUpdatedV1;
import io.envoi.library.messaging.mapping.UserProfileEventMapper;
import io.envoi.library.replica.entity.UserProfileReplicaEntity;
import io.envoi.library.replica.service.UserProfileReplicaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaUserProfileConsumerService {

    private final UserProfileReplicaService replicaService;

    private final UserProfileEventMapper mapper;

    private final ObjectMapper objectMapper;

    @KafkaListener(
            topics = "${app.kafka.topics.user-profile-events}",
            groupId = "${spring.kafka.consumers.user-profile-consumer.group-id}"
    )
    public void listen(JsonNode message) {
        String type = message.path("metadata").path("eventType").asText();

        UserProfileReplicaEntity entity;

        switch (type) {
            case "USER_PROFILE_CREATED" -> {

                UserProfileCreatedV1 createdV1 = objectMapper.convertValue(message, UserProfileCreatedV1.class);

                entity = mapper.fromCreatedV1(createdV1);

                replicaService.save(entity);
            }
            case "USER_PROFILE_UPDATED" -> {

                UserProfileUpdatedV1 updatedV1 = objectMapper.convertValue(message, UserProfileUpdatedV1.class);

                entity = mapper.fromUpdatedV1(updatedV1);

                replicaService.update(entity);
            }
            case "USER_PROFILE_DELETED" -> {

                UserProfileDeletedV1 deletedV1 = objectMapper.convertValue(message, UserProfileDeletedV1.class);

                entity = mapper.fromDeletedV1(deletedV1);

                replicaService.delete(entity);
            }
            default -> throw new IllegalArgumentException("Unknown event type: " + type);
        }

        log.info("Listen received event type={}, entityId={}", type, entity.getId());
    }
}