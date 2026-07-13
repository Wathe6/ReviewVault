package io.envoi.media.media.repository;

import io.envoi.media.common.entity.LanguageEntity;
import io.envoi.media.common.repository.LanguageRepository;
import io.envoi.media.media.entity.MediaGroupEntity;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MediaGroupRepositoryTest {

    @Autowired
    private MediaGroupRepository mediaGroupRepository;

    @Autowired
    private LanguageRepository languageRepository;

    private MediaGroupEntity testMediaGroup;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @BeforeEach
    public void setUp() {
        LanguageEntity testLanguage = new LanguageEntity();
        testLanguage.setName("testLanguage");
        testLanguage.setCode("te");
        testLanguage.setNativeName("testNativeName");
        languageRepository.save(testLanguage);

        testMediaGroup = new MediaGroupEntity();
        testMediaGroup.setTitle("Test Media Group Title");
        testMediaGroup.setDescription("Test Media Group Description");
        testMediaGroup.setCoverUrl("Test Media Group Cover Url");
        testMediaGroup.setOriginalLanguage(testLanguage);
        mediaGroupRepository.save(testMediaGroup);
    }

    @AfterEach
    public void tearDown() {
        mediaGroupRepository.delete(testMediaGroup);
        languageRepository.delete(testMediaGroup.getOriginalLanguage());
    }

    @Test
    @DisplayName(value = "Test getMediaGroupById")
    void givenMediaGroup_whenSaved_thenCanBeFoundById() {
        MediaGroupEntity savedMediaGroup = mediaGroupRepository.findById(testMediaGroup.getId()).orElse(null);
        assertNotNull(savedMediaGroup);
        assertEquals(testMediaGroup.getTitle(), savedMediaGroup.getTitle());
        assertEquals(testMediaGroup.getDescription(), savedMediaGroup.getDescription());
        assertEquals(testMediaGroup.getCoverUrl(), savedMediaGroup.getCoverUrl());
        assertEquals(testMediaGroup.getOriginalLanguage(), savedMediaGroup.getOriginalLanguage());
    }

    @Test
    @DisplayName(value = "Test MediaGroup update")
    void givenMediaGroup_whenUpdated_thenCanBeFoundByIdWithUpdatedData() {
        testMediaGroup.setTitle("Test Updated Media Group Title");
        mediaGroupRepository.save(testMediaGroup);

        MediaGroupEntity updatedMediaGroup = mediaGroupRepository.findById(testMediaGroup.getId()).orElse(null);

        assertNotNull(updatedMediaGroup);
        assertEquals(testMediaGroup.getTitle(), updatedMediaGroup.getTitle());
    }

    @Test
    @DisplayName(value = "Test MediaGroup findByTitle")
    void givenMediaGroup_whenSaved_thenCanBeFoundByTitle() {
        MediaGroupEntity mediaGroup = mediaGroupRepository.findByTitle("Test Media Group Title").getFirst();

        assertNotNull(mediaGroup);
        assertEquals(testMediaGroup.getTitle(), mediaGroup.getTitle());
    }
}
