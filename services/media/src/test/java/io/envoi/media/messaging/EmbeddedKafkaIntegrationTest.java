package io.envoi.media.messaging;

import io.envoi.contracts.media.v1.MediaItemCreatedV1;
import io.envoi.contracts.media.v1.MediaItemDeletedV1;
import io.envoi.contracts.media.v1.MediaItemUpdatedV1;
import io.envoi.media.classification.entity.MediaCategoryEntity;
import io.envoi.media.classification.entity.MediaFormatEntity;
import io.envoi.media.common.entity.LanguageEntity;
import io.envoi.media.media.entity.MediaGroupEntity;
import io.envoi.media.media.entity.MediaItemEntity;
import io.envoi.media.media.entity.MediaItemStatusEntity;
import io.envoi.media.messaging.mapping.MediaItemEventMapper;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.support.TransactionTemplate;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(properties = {
        "test.topic=embedded-test-topic",
        "app.kafka.topics.media-item-events=embedded-test-topic",
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

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private EmbeddedKafkaBroker embeddedKafka;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Autowired
    private MediaItemEventMapper eventMapper;

    private MediaItemEntity entity;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void updateEntity() {
        entity = new MediaItemEntity();
        entity.setId(UUID.randomUUID());
        entity.setTitle("Test1");
        MediaGroupEntity mediaGroupEntity = new MediaGroupEntity();
        mediaGroupEntity.setId(UUID.randomUUID());
        entity.setMediaGroup(mediaGroupEntity);

        MediaFormatEntity mediaFormatEntity = new MediaFormatEntity();
        mediaFormatEntity.setId((short) 1);
        entity.setMediaFormat(mediaFormatEntity);

        MediaCategoryEntity mediaCategoryEntity = new MediaCategoryEntity();
        mediaCategoryEntity.setId((short) 1);
        entity.setMediaCategory(mediaCategoryEntity);

        MediaItemStatusEntity mediaItemStatusEntity = new MediaItemStatusEntity();
        mediaItemStatusEntity.setId((short) 1);
        entity.setMediaItemStatus(mediaItemStatusEntity);

        LanguageEntity languageEntity = new LanguageEntity();
        languageEntity.setId((short) 2);
        entity.setOriginalLanguage(languageEntity);
    }

    private Consumer<String, String> consumer;

    @BeforeEach
    void createConsumer() {
        Map<String, Object> properties =
                KafkaTestUtils.consumerProps(
                        embeddedKafka,
                        "producer-test-" + UUID.randomUUID(),
                        false
                );

        properties.put(
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class
        );
        properties.put(
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class
        );
        properties.put(
                ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,
                "earliest"
        );

        consumer = new DefaultKafkaConsumerFactory<String, String>(properties)
                .createConsumer();

        embeddedKafka.consumeFromAnEmbeddedTopic(
                consumer,
                true,
                topic
        );
    }

    @AfterEach
    void closeConsumer() {
        consumer.close();
    }

    @Test
    @DisplayName("Test Kafka MediaItemCreatedV1 event publishing")
    public void shouldPublishMediaItemCreatedEvent() {
        MediaItemCreatedV1 event = eventMapper.toCreatedV1(entity);

        assertThat(event.data().id()).isEqualTo(entity.getId());
        assertThat(event.data().title()).isEqualTo(entity.getTitle());
        assertThat(event.metadata().eventType()).isEqualTo("MEDIA_ITEM_CREATED");
        assertThat(event.metadata().aggregateId()).isEqualTo(entity.getId());
        assertThat(event.metadata().eventId()).isNotNull();

        transactionTemplate.executeWithoutResult(status ->
                applicationEventPublisher.publishEvent(event)
        );

        ConsumerRecord<String, String> record =
                KafkaTestUtils.getSingleRecord(consumer, topic);

        assertThat(record.key()).isEqualTo(entity.getId().toString());
        JsonNode received = objectMapper.readTree(record.value());

        assertThat(received.path("metadata").path("eventType").asString()).isEqualTo("MEDIA_ITEM_CREATED");

        assertThat(received.path("metadata").path("aggregateId").asString()).isEqualTo(entity.getId().toString());

        assertThat(received.path("data").path("id").asString()).isEqualTo(entity.getId().toString());

        assertThat(received.path("data").path("title").asString()).isEqualTo(entity.getTitle());
    }

    @Test
    @DisplayName("Test Kafka MediaItemUpdatedV1 event publishing")
    public void shouldPublishMediaItemUpdatedEvent() {
        MediaItemUpdatedV1 event = eventMapper.toUpdatedV1(entity);

        assertThat(event.data().id()).isEqualTo(entity.getId());
        assertThat(event.data().title()).isEqualTo(entity.getTitle());
        assertThat(event.metadata().eventType()).isEqualTo("MEDIA_ITEM_UPDATED");
        assertThat(event.metadata().aggregateId()).isEqualTo(entity.getId());
        assertThat(event.metadata().eventId()).isNotNull();

        transactionTemplate.executeWithoutResult(status ->
                applicationEventPublisher.publishEvent(event)
        );

        ConsumerRecord<String, String> record =
                KafkaTestUtils.getSingleRecord(consumer, topic);

        assertThat(record.key()).isEqualTo(entity.getId().toString());
        JsonNode received = objectMapper.readTree(record.value());

        assertThat(received.path("metadata").path("eventType").asString()).isEqualTo("MEDIA_ITEM_UPDATED");

        assertThat(received.path("metadata").path("aggregateId").asString()).isEqualTo(entity.getId().toString());

        assertThat(received.path("data").path("id").asString()).isEqualTo(entity.getId().toString());

        assertThat(received.path("data").path("title").asString()).isEqualTo(entity.getTitle());
    }

    @Test
    @DisplayName("Test Kafka MediaItemDeletedV1 event publishing")
    public void shouldPublishMediaItemDeletedEvent() {
        MediaItemDeletedV1 event = eventMapper.toDeletedV1(entity);

        assertThat(event.data().id()).isEqualTo(entity.getId());
        assertThat(event.data().title()).isEqualTo(entity.getTitle());
        assertThat(event.metadata().eventType()).isEqualTo("MEDIA_ITEM_DELETED");
        assertThat(event.metadata().aggregateId()).isEqualTo(entity.getId());
        assertThat(event.metadata().eventId()).isNotNull();

        transactionTemplate.executeWithoutResult(status ->
                applicationEventPublisher.publishEvent(event)
        );

        ConsumerRecord<String, String> record =
                KafkaTestUtils.getSingleRecord(consumer, topic);

        assertThat(record.key()).isEqualTo(entity.getId().toString());
        JsonNode received = objectMapper.readTree(record.value());

        assertThat(received.path("metadata").path("eventType").asString()).isEqualTo("MEDIA_ITEM_DELETED");

        assertThat(received.path("metadata").path("aggregateId").asString()).isEqualTo(entity.getId().toString());

        assertThat(received.path("data").path("id").asString()).isEqualTo(entity.getId().toString());

        assertThat(received.path("data").path("title").asString()).isEqualTo(entity.getTitle());
    }
}
