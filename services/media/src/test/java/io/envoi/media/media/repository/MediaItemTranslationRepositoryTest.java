package io.envoi.media.media.repository;

import io.envoi.media.common.repository.LanguageRepository;
import io.envoi.media.media.entity.MediaItemEntity;
import io.envoi.media.media.entity.MediaItemTranslationEntity;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MediaItemTranslationRepositoryTest {

    @Autowired
    private MediaItemTranslationRepository mediaItemTranslationRepository;

    @Autowired
    private MediaItemRepository mediaItemRepository;

    @Autowired
    private LanguageRepository languageRepository;

    @Autowired
    private EntityManager em;

    private MediaItemTranslationEntity testMediaItemTranslation;

    @BeforeEach
    void setUp() {
        testMediaItemTranslation = new MediaItemTranslationEntity();
        MediaItemEntity mediaItem = mediaItemRepository.findById(UUID.fromString("11111111-1111-1111-1111-111111111111")).orElseThrow();

        testMediaItemTranslation.setMediaItem(mediaItem);
        testMediaItemTranslation.setName("Test Name");
        testMediaItemTranslation.setDescription("Test Description");
        testMediaItemTranslation.setLanguage(languageRepository.findById((short) 3).orElseThrow());

        mediaItemTranslationRepository.save(testMediaItemTranslation);

        em.flush();
        em.clear();
    }

    @Test
    @DisplayName(value = "Test MediaItemTranslationRepository save")
    void givenMediaItem_whenSaved_thenCanBeFoundById() {
        MediaItemTranslationEntity savedMediaItemTranslation = mediaItemTranslationRepository.findById(testMediaItemTranslation.getId()).orElse(null);

        assertNotNull(savedMediaItemTranslation);
        assertEquals(testMediaItemTranslation.getName(), savedMediaItemTranslation.getName());
    }

    @Test
    @DisplayName(value = "Test MediaItemTranslationRepository update")
    void givenMediaItemTranslation_whenUpdated_thenCanBeFoundByIdWithUpdatedData() {
        testMediaItemTranslation.setName("Test Updated Media Item Translation Name");
        mediaItemTranslationRepository.save(testMediaItemTranslation);

        em.flush();
        em.clear();

        MediaItemTranslationEntity updatedMediaItemTranslation = mediaItemTranslationRepository.findById(testMediaItemTranslation.getId()).orElse(null);

        assertNotNull(updatedMediaItemTranslation);
        assertEquals(testMediaItemTranslation.getName(), updatedMediaItemTranslation.getName());
    }

    @Test
    @DisplayName(value = "Test MediaItemTranslationRepository findByName")
    void givenMediaItemTranslation_whenSaved_thenCanBeFoundByName() {

        List<MediaItemTranslationEntity> result = mediaItemTranslationRepository.findByName("Test Name");

        assertThat(result).hasSize(1);

        MediaItemTranslationEntity mediaItemTranslation = result.getFirst();
        assertNotNull(mediaItemTranslation);
        assertEquals(testMediaItemTranslation.getName(), mediaItemTranslation.getName());
    }

}