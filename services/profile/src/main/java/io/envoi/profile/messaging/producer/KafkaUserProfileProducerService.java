package io.envoi.profile.messaging.producer;

import io.envoi.contracts.profile.v1.UserProfileCreatedV1;
import io.envoi.contracts.profile.v1.UserProfileDeletedV1;
import io.envoi.contracts.profile.v1.UserProfileUpdatedV1;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaUserProfileProducerService {

    @Value("${app.kafka.topics.user-profile-events}")
    private String topic;

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendUserProfileCreatedV1(UserProfileCreatedV1 entity) {

        kafkaTemplate.send(
                topic,
                entity.data().id().toString(),
                entity);

        log.info("Message userProfileCreatedV1 sent to topic: {} with id: {}", topic, entity.data().id());
    }

    public void sendUserProfileUpdatedV1(UserProfileUpdatedV1 entity) {

        kafkaTemplate.send(
                topic,
                entity.data().id().toString(),
                entity);

        log.info("Message userProfileUpdatedV1 sent to topic: {} with id: {}", topic, entity.data().id());
    }

    public void sendUserProfileDeletedV1(UserProfileDeletedV1 entity) {

        kafkaTemplate.send(
                topic,
                entity.data().id().toString(),
                entity);

        log.info("Message userProfileDeletedV1 sent to topic: {} with id: {}", topic, entity.data().id());
    }
}
