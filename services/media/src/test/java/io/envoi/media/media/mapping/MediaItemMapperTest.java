package io.envoi.media.media.mapping;

import io.envoi.media.classification.entity.MediaCategoryEntity;
import io.envoi.media.classification.entity.MediaFormatEntity;
import io.envoi.media.common.entity.LanguageEntity;
import io.envoi.media.credits.dto.request.CompanyRequest;
import io.envoi.media.credits.entity.CompanyEntity;
import io.envoi.media.media.dto.request.MediaItemRequest;
import io.envoi.media.media.dto.response.MediaItemResponse;
import io.envoi.media.media.entity.MediaGroupEntity;
import io.envoi.media.media.entity.MediaItemEntity;
import io.envoi.media.media.entity.MediaItemStatusEntity;
import lombok.NonNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class MediaItemMapperTest {

    private final MediaItemMapper mapper = Mappers.getMapper(MediaItemMapper.class);

    @Test
    @DisplayName("Should map MediaItemEntity to MediaItemResponse")
    void shouldMapEntityToResponse() {
        MediaItemEntity entity = getMediaItemEntity();

        MediaItemResponse response = mapper.toResponse(entity);

        assertThat(response).isNotNull();

        assertThat(response.id()).isEqualTo(entity.getId());
        assertThat(response.mediaGroupId()).isEqualTo(entity.getMediaGroup().getId());
        assertThat(response.mediaFormatId()).isEqualTo(entity.getMediaFormat().getId());
        assertThat(response.mediaCategoryId()).isEqualTo(entity.getMediaCategory().getId());
        assertThat(response.mediaItemStatusId()).isEqualTo(entity.getMediaItemStatus().getId());
        assertThat(response.originalLanguageCode()).isEqualTo(entity.getOriginalLanguage().getCode());
        assertThat(response.title()).isEqualTo(entity.getTitle());
    }

    @Test
    @DisplayName("Should map MediaItemRequest to MediaItemEntity")
    void shouldMapRequestToEntity() {

        MediaItemRequest request =
                new MediaItemRequest(
                        "Attack on Titan",
                        UUID.randomUUID(),
                        (short)1,
                        (short)2,
                        (short)3,
                        "Description",
                        LocalDate.of(2013,4,7),
                        null,
                        "en",
                        "cover",
                        Map.of()
                );

        MediaItemEntity entity = mapper.toEntity(request);

        assertThat(entity).isNotNull();

        assertThat(entity.getTitle()).isEqualTo(request.title());
        assertThat(entity.getDescription()).isEqualTo(request.description());
        assertThat(entity.getReleaseDate()).isEqualTo(request.releaseDate());
        assertThat(entity.getEndDate()).isEqualTo(request.endDate());
        assertThat(entity.getCoverUrl()).isEqualTo(request.coverUrl());

        assertThat(entity.getMediaGroup()).isNull();
        assertThat(entity.getMediaFormat()).isNull();
        assertThat(entity.getMediaCategory()).isNull();
        assertThat(entity.getMediaItemStatus()).isNull();
        assertThat(entity.getOriginalLanguage()).isNull();
    }

    @Test
    @DisplayName("Should update MediaItemEntity from MediaItemRequest")
    void shouldUpdateEntityFromRequest() {
        MediaItemEntity entity = getMediaItemEntity();

        MediaItemRequest request = new MediaItemRequest(
                "Request Title",
                null,
                (short) 2,
                (short) 3,
                (short) 1,
                "Request Description",
                null,
                null,
                "ja",
                "Request CoverUrl",
                Map.of()
        );

        mapper.update(request, entity);

        assertThat(entity).isNotNull();
        assertThat(entity.getTitle()).isEqualTo(request.title());
        assertThat(entity.getDescription()).isEqualTo(request.description());
        assertThat(entity.getReleaseDate()).isEqualTo(request.releaseDate());
        assertThat(entity.getEndDate()).isEqualTo(request.endDate());
        assertThat(entity.getCoverUrl()).isEqualTo(request.coverUrl());
    }

    private static @NonNull MediaItemEntity getMediaItemEntity() {
        MediaFormatEntity format = new MediaFormatEntity();
        format.setId((short) 1);

        MediaCategoryEntity category = new MediaCategoryEntity();
        category.setId((short) 2);

        MediaItemStatusEntity status = new MediaItemStatusEntity();
        status.setId((short) 3);

        LanguageEntity language = new LanguageEntity();
        language.setId((short) 1);
        language.setCode("en");

        UUID groupId = UUID.randomUUID();

        MediaGroupEntity group = new MediaGroupEntity();
        group.setId(groupId);

        UUID itemId = UUID.randomUUID();

        MediaItemEntity entity = new MediaItemEntity();

        entity.setId(itemId);
        entity.setTitle("Attack on Titan");
        entity.setDescription("Description");
        entity.setMediaGroup(group);
        entity.setMediaFormat(format);
        entity.setMediaCategory(category);
        entity.setMediaItemStatus(status);
        entity.setOriginalLanguage(language);
        entity.setCoverUrl("cover");
        entity.setReleaseDate(LocalDate.of(2013,4,7));

        return entity;
    }
}