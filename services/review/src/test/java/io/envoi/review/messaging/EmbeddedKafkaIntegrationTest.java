package io.envoi.review.messaging;

import io.envoi.contracts.media.v1.EventMetadataV1;
import io.envoi.contracts.media.v1.MediaItemCreatedV1;
import io.envoi.contracts.media.v1.MediaItemDeletedV1;
import io.envoi.contracts.media.v1.MediaItemSnapshotV1;
import io.envoi.contracts.media.v1.MediaItemUpdatedV1;
import io.envoi.review.messaging.consumer.KafkaMediaItemConsumerService;
import io.envoi.review.messaging.mapping.MediaItemEventMapper;
import io.envoi.review.replica.entity.MediaItemReplicaEntity;
import io.envoi.review.replica.service.MediaItemReplicaService;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.transaction.support.TransactionTemplate;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@SpringBootTest(properties = {
        "test.topic=embedded-test-topic",
        "app.kafka.topics.media-item-events=embedded-test-topic",

        "spring.kafka.consumer.group-id=review-listener-test",
        "spring.kafka.consumer.auto-offset-reset=earliest",
        "spring.kafka.consumer.key-deserializer=org.apache.kafka.common.serialization.StringDeserializer",
        "spring.kafka.consumer.value-deserializer=org.springframework.kafka.support.serializer.JacksonJsonDeserializer",
        "spring.kafka.consumer.properties.spring.json.use.type.headers=false",
        "spring.kafka.consumer.properties.spring.json.value.default.type=tools.jackson.databind.JsonNode",

        "spring.kafka.producer.key-serializer=org.apache.kafka.common.serialization.StringSerializer",
        "spring.kafka.producer.value-serializer=org.springframework.kafka.support.serializer.JacksonJsonSerializer",
        "spring.kafka.producer.properties.spring.json.add.type.headers=false",

        "eureka.client.enabled=false"
})
@DirtiesContext
@EmbeddedKafka(
        partitions = 1,
        topics = "${test.topic}",
        bootstrapServersProperty = "spring.kafka.bootstrap-servers"
)
class EmbeddedKafkaIntegrationTest {

    @Value("${test.topic}")
    private String topic;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @MockitoBean
    private MediaItemReplicaService replicaService;

    private MediaItemCreatedV1 createdV1;
    private MediaItemUpdatedV1 updatedV1;
    private MediaItemDeletedV1 deletedV1;

    @BeforeEach
    void updateEntity() {
        UUID aggregateId = UUID.randomUUID();
        createdV1 = new MediaItemCreatedV1(
                new EventMetadataV1(
                        UUID.randomUUID(),
                        "MEDIA_ITEM_CREATED",
                        1,
                        Instant.now(),
                        aggregateId
                ),
                new MediaItemSnapshotV1(
                        aggregateId,
                        "TestCreatedV1"
                )
        );

        updatedV1 = new MediaItemUpdatedV1(
                new EventMetadataV1(
                        UUID.randomUUID(),
                        "MEDIA_ITEM_UPDATED",
                        1,
                        Instant.now(),
                        aggregateId
                ),
                new MediaItemSnapshotV1(
                        aggregateId,
                        "TestUpdatedV1"
                )
        );

        deletedV1 = new MediaItemDeletedV1(
                new EventMetadataV1(
                        UUID.randomUUID(),
                        "MEDIA_ITEM_DELETED",
                        1,
                        Instant.now(),
                        aggregateId
                ),
                new MediaItemSnapshotV1(
                        aggregateId,
                        "TestDeletedV1"
                )
        );
    }

    @Test
    @DisplayName("Test Kafka MediaItemCreatedV1 event listening")
    void shouldReceiveMediaItemCreatedEvent() throws Exception {
        kafkaTemplate.send(
                topic,
                createdV1.data().id().toString(),
                createdV1
        ).get(10, TimeUnit.SECONDS);

        ArgumentCaptor<MediaItemReplicaEntity> captor =
                ArgumentCaptor.forClass(MediaItemReplicaEntity.class);

        verify(replicaService, timeout(10_000))
                .save(captor.capture());

        MediaItemReplicaEntity replica = captor.getValue();

        assertThat(replica.getId())
                .isEqualTo(createdV1.data().id());

        assertThat(replica.getTitle())
                .isEqualTo(createdV1.data().title());

        assertThat(replica.getLastEventId())
                .isEqualTo(createdV1.metadata().eventId());

        assertThat(replica.getLastEventAt())
                .isEqualTo(createdV1.metadata().occurredAt());

        assertThat(replica.getDeleted()).isFalse();

        verifyNoMoreInteractions(replicaService);
    }

    @Test
    @DisplayName("Test Kafka MediaItemUpdatedV1 event listening")
    public void shouldReceiveMediaItemUpdatedEvent() throws Exception {
        kafkaTemplate.send(
                topic,
                updatedV1.data().id().toString(),
                updatedV1
        ).get(10, TimeUnit.SECONDS);

        ArgumentCaptor<MediaItemReplicaEntity> captor =
                ArgumentCaptor.forClass(MediaItemReplicaEntity.class);

        verify(replicaService, timeout(10_000))
                .update(captor.capture());

        MediaItemReplicaEntity replica = captor.getValue();

        assertThat(replica.getId())
                .isEqualTo(updatedV1.data().id());

        assertThat(replica.getTitle())
                .isEqualTo(updatedV1.data().title());

        assertThat(replica.getLastEventId())
                .isEqualTo(updatedV1.metadata().eventId());

        assertThat(replica.getLastEventAt())
                .isEqualTo(updatedV1.metadata().occurredAt());

        assertThat(replica.getDeleted()).isFalse();

        verifyNoMoreInteractions(replicaService);
    }

    @Test
    @DisplayName("Test Kafka MediaItemDeletedV1 event listening")
    public void shouldReceiveMediaItemDeletedEvent() throws Exception {
        kafkaTemplate.send(
                topic,
                deletedV1.data().id().toString(),
                deletedV1
        ).get(10, TimeUnit.SECONDS);

        ArgumentCaptor<MediaItemReplicaEntity> captor =
                ArgumentCaptor.forClass(MediaItemReplicaEntity.class);

        verify(replicaService, timeout(10_000))
                .delete(captor.capture());

        MediaItemReplicaEntity replica = captor.getValue();

        assertThat(replica.getId())
                .isEqualTo(deletedV1.data().id());

        assertThat(replica.getTitle())
                .isEqualTo(deletedV1.data().title());

        assertThat(replica.getLastEventId())
                .isEqualTo(deletedV1.metadata().eventId());

        assertThat(replica.getLastEventAt())
                .isEqualTo(deletedV1.metadata().occurredAt());

        assertThat(replica.getDeleted()).isTrue();

        verifyNoMoreInteractions(replicaService);
    }
}
