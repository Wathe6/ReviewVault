package io.envoi.review.replica;

import io.envoi.review.replica.entity.MediaItemReplicaEntity;
import io.envoi.review.replica.repository.MediaItemReplicaRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import java.time.Instant;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MediaItemReplicaRepositoryTest {

    @Autowired
    private MediaItemReplicaRepository mediaItemReplicaRepository;

    @Autowired
    private EntityManager em;

    private MediaItemReplicaEntity mediaItemReplicaEntity;

    @BeforeEach
    void setup() {
        mediaItemReplicaEntity = new MediaItemReplicaEntity();

        mediaItemReplicaEntity.setId(UUID.randomUUID());
        mediaItemReplicaEntity.setTitle("Test1");
        mediaItemReplicaEntity.setCreatedAt(Instant.now());
        mediaItemReplicaEntity.setUpdatedAt(Instant.now());
        mediaItemReplicaEntity.setDeleted(false);
        mediaItemReplicaEntity.setLastEventId(UUID.randomUUID());
        mediaItemReplicaEntity.setLastEventAt(Instant.now());

        mediaItemReplicaRepository.save(mediaItemReplicaEntity);
    }

    @AfterEach
    void tearDown() {
        mediaItemReplicaRepository.delete(mediaItemReplicaEntity);
    }

    @Test
    @DisplayName("Test MediaItemReplica save")
    void givenMediaItemReplica_whenSave_thenCanBeFoundById() {
        MediaItemReplicaEntity savedMediaItemReplicaEntity = mediaItemReplicaRepository.findById(mediaItemReplicaEntity.getId()).orElse(null);

        assertThat(savedMediaItemReplicaEntity)
                .isNotNull()
                .extracting(
                        MediaItemReplicaEntity::getTitle,
                        MediaItemReplicaEntity::getCreatedAt,
                        MediaItemReplicaEntity::getUpdatedAt,
                        MediaItemReplicaEntity::getDeleted,
                        MediaItemReplicaEntity::getLastEventId,
                        MediaItemReplicaEntity::getLastEventAt
                )
                .containsExactly(
                        savedMediaItemReplicaEntity.getTitle(),
                        savedMediaItemReplicaEntity.getCreatedAt(),
                        savedMediaItemReplicaEntity.getUpdatedAt(),
                        savedMediaItemReplicaEntity.getDeleted(),
                        savedMediaItemReplicaEntity.getLastEventId(),
                        savedMediaItemReplicaEntity.getLastEventAt()
                );
    }

    @Test
    @DisplayName("Test MediaItemReplica update")
    void givenMediaItemReplica_whenUpdate_thenCanBeFoundByIdWithUpdatedData() {
        mediaItemReplicaEntity.setTitle("Test2");
        mediaItemReplicaRepository.save(mediaItemReplicaEntity);

        em.flush();
        em.clear();

        MediaItemReplicaEntity updatedEntity = mediaItemReplicaRepository.findById(mediaItemReplicaEntity.getId()).orElse(null);

        assertThat(updatedEntity);
        assertEquals(updatedEntity.getTitle(), mediaItemReplicaEntity.getTitle());
    }


}
