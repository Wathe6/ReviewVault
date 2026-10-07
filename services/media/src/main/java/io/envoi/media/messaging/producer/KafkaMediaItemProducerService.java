package io.envoi.media.messaging.producer;

import io.envoi.contracts.media.v1.MediaItemCreatedV1;
import io.envoi.contracts.media.v1.MediaItemDeletedV1;
import io.envoi.contracts.media.v1.MediaItemUpdatedV1;
import io.envoi.media.media.entity.MediaItemEntity;
import io.envoi.media.messaging.mapping.MediaItemEventMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaMediaItemProducerService {

    @Value("${app.kafka.topics.media-item-events}")
    private String topic;

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendMediaItemCreatedV1(MediaItemCreatedV1 entity) {

        kafkaTemplate.send(
                topic,
                entity.data().id().toString(),
                entity);

        log.info("Message mediaItemCreatedV1 sent to topic: {} with mediaItemId: {}", topic, entity.data().id());
    }

    public void sendMediaItemUpdatedV1(MediaItemUpdatedV1 entity) {

        kafkaTemplate.send(
                topic,
                entity.data().id().toString(),
                entity);

        log.info("Message mediaItemUpdatedV1 sent to topic: {} with mediaItemId: {}", topic, entity.data().id());
    }

    public void sendMediaItemDeletedV1(MediaItemDeletedV1 entity) {

        kafkaTemplate.send(
                topic,
                entity.data().id().toString(),
                entity);

        log.info("Message mediaItemDeletedV1 sent to topic: {} with id: {}", topic, entity.data().id());
    }
}
