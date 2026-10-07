package io.envoi.media.media.repository;

import io.envoi.media.classification.repository.MediaCategoryRepository;
import io.envoi.media.classification.repository.MediaFormatRepository;
import io.envoi.media.common.repository.LanguageRepository;
import io.envoi.media.media.entity.MediaItemEntity;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MediaItemRepositoryTest {

    @Autowired
    private MediaItemRepository mediaItemRepository;

    @Autowired
    private MediaGroupRepository mediaGroupRepository;

    @Autowired
    private MediaFormatRepository mediaFormatRepository;

    @Autowired
    private MediaCategoryRepository mediaCategoryRepository;

    @Autowired
    private MediaItemStatusRepository mediaItemStatusRepository;

    @Autowired
    private LanguageRepository languageRepository;

    @Autowired
    private EntityManager em;

    private MediaItemEntity testMediaItem;

    @BeforeEach
    void setUp() {
        testMediaItem = new MediaItemEntity();

        testMediaItem.setTitle("Test Title");
        testMediaItem.setDescription("Test Description");
        testMediaItem.setMediaGroup(mediaGroupRepository.findById(UUID.fromString("11111111-1111-1111-1111-111111111111")).orElseThrow());
        testMediaItem.setMediaFormat(mediaFormatRepository.findById((short) 1).orElseThrow());
        testMediaItem.setMediaCategory(mediaCategoryRepository.findById((short) 1).orElseThrow());
        testMediaItem.setMediaItemStatus(mediaItemStatusRepository.findById((short) 1).orElseThrow());
        testMediaItem.setReleaseDate(LocalDate.now());
        testMediaItem.setEndDate(LocalDate.now().plusDays(1));
        testMediaItem.setCoverUrl("Test Cover Url");
        testMediaItem.setOriginalLanguage(languageRepository.findById((short) 1).orElseThrow());
        testMediaItem.setMetadata(new HashMap<>());

        mediaItemRepository.save(testMediaItem);

        em.flush();
        em.clear();
    }

    @Test
    @DisplayName("Test MediaItemRepository save")
    void givenMediaItem_whenSaved_thenCanBeFoundById() {
        MediaItemEntity savedMediaItem = mediaItemRepository.findById(testMediaItem.getId()).orElse(null);

        assertThat(savedMediaItem)
                .isNotNull()
                .extracting(
                        MediaItemEntity::getTitle,
                        MediaItemEntity::getDescription,
                        MediaItemEntity::getCoverUrl,
                        MediaItemEntity::getReleaseDate,
                        MediaItemEntity::getEndDate,
                        MediaItemEntity::getMetadata
                )
                .containsExactly(
                        testMediaItem.getTitle(),
                        testMediaItem.getDescription(),
                        testMediaItem.getCoverUrl(),
                        testMediaItem.getReleaseDate(),
                        testMediaItem.getEndDate(),
                        testMediaItem.getMetadata()
                );
        assertEquals(testMediaItem.getMediaGroup().getId(), savedMediaItem.getMediaGroup().getId());
        assertEquals(testMediaItem.getMediaFormat().getId(), savedMediaItem.getMediaFormat().getId());
        assertEquals(testMediaItem.getMediaCategory().getId(), savedMediaItem.getMediaCategory().getId());
        assertEquals(testMediaItem.getMediaItemStatus().getId(), savedMediaItem.getMediaItemStatus().getId());
        assertEquals(testMediaItem.getOriginalLanguage().getId(), savedMediaItem.getOriginalLanguage().getId());
    }

    @Test
    @DisplayName("Test MediaItemRepository update")
    void givenMediaItem_whenUpdated_thenCanBeFoundByIdWithUpdatedData() {
        testMediaItem.setTitle("Test Updated Media Item Title");
        mediaItemRepository.save(testMediaItem);

        em.flush();
        em.clear();

        MediaItemEntity updatedMediaItem = mediaItemRepository.findById(testMediaItem.getId()).orElse(null);

        assertNotNull(updatedMediaItem);
        assertEquals(testMediaItem.getTitle(), updatedMediaItem.getTitle());
    }

    @Test
    @DisplayName("Test MediaItemRepository findByTitle")
    void givenMediaItem_whenSaved_thenCanBeFoundByTitle() {

        List<MediaItemEntity> result = mediaItemRepository.findByTitle("Test Title");

        assertThat(result).hasSize(1);

        MediaItemEntity mediaItem = result.getFirst();

        assertThat(mediaItem)
                .isNotNull()
                .extracting(
                        MediaItemEntity::getTitle,
                        MediaItemEntity::getDescription,
                        MediaItemEntity::getCoverUrl,
                        MediaItemEntity::getReleaseDate,
                        MediaItemEntity::getEndDate,
                        MediaItemEntity::getMetadata
                )
                .containsExactly(
                        testMediaItem.getTitle(),
                        testMediaItem.getDescription(),
                        testMediaItem.getCoverUrl(),
                        testMediaItem.getReleaseDate(),
                        testMediaItem.getEndDate(),
                        testMediaItem.getMetadata()
                );

        assertEquals(testMediaItem.getMediaGroup().getId(), mediaItem.getMediaGroup().getId());
        assertEquals(testMediaItem.getMediaFormat().getId(), mediaItem.getMediaFormat().getId());
        assertEquals(testMediaItem.getMediaCategory().getId(), mediaItem.getMediaCategory().getId());
        assertEquals(testMediaItem.getMediaItemStatus().getId(), mediaItem.getMediaItemStatus().getId());
        assertEquals(testMediaItem.getOriginalLanguage().getId(), mediaItem.getOriginalLanguage().getId());
    }

}