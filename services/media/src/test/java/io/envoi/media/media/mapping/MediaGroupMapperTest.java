package io.envoi.media.media.mapping;

import io.envoi.media.common.entity.LanguageEntity;
import io.envoi.media.media.dto.request.MediaGroupRequest;
import io.envoi.media.media.dto.response.MediaGroupResponse;
import io.envoi.media.media.entity.MediaGroupEntity;
import org.jspecify.annotations.NonNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class MediaGroupMapperTest {

    private final MediaGroupMapper mapper = Mappers.getMapper(MediaGroupMapper.class);

    @Test
    @DisplayName("Should map MediaGroupEntity to MediaGroupResponse")
    void shouldMapEntityToResponse() {
        MediaGroupEntity entity = getMediaGroup();

        MediaGroupResponse response = mapper.toResponse(entity);

        assertThat(response).isNotNull();

        assertThat(response.id()).isEqualTo(entity.getId());
        assertThat(response.title()).isEqualTo("Attack on Titan");
        assertThat(response.description()).isEqualTo("Description");
        assertThat(response.coverUrl()).isEqualTo("cover.jpg");
        assertThat(response.originalLanguageCode()).isEqualTo("en");
    }

    @Test
    @DisplayName("Should map MediaGroupRequest to MediaGroupEntity")
    void shouldMapRequestToEntity() {

        MediaGroupRequest request = new MediaGroupRequest(
                "Attack on Titan",
                "Description",
                "cover.jpg",
                "en"
        );

        MediaGroupEntity entity = mapper.toEntity(request);

        assertThat(entity).isNotNull();

        assertThat(entity.getTitle()).isEqualTo("Attack on Titan");
        assertThat(entity.getDescription()).isEqualTo("Description");
        assertThat(entity.getCoverUrl()).isEqualTo("cover.jpg");

        // игнорируются MapStruct
        assertThat(entity.getOriginalLanguage()).isNull();
    }

    @Test
    @DisplayName("Should update MediaGroupEntity from MediaGroupRequest")
    void shouldUpdateEntityFromRequest() {
        MediaGroupEntity entity = getMediaGroup();

        MediaGroupRequest request = new MediaGroupRequest(
                "Request Test",
                "Request Description",
                "cover.jpg",
                "en"
        );

        mapper.update(request, entity);

        assertThat(entity).isNotNull();
        assertThat(entity.getTitle()).isEqualTo(request.title());
        assertThat(entity.getDescription()).isEqualTo(request.description());
        assertThat(entity.getCoverUrl()).isEqualTo(request.coverUrl());
        assertThat(entity.getOriginalLanguage().getCode()).isEqualTo("en");
    }

    private static @NonNull MediaGroupEntity getMediaGroup() {
        LanguageEntity language = new LanguageEntity();
        ReflectionTestUtils.setField(language, "id", (short) 1);
        language.setCode("en");

        MediaGroupEntity entity = new MediaGroupEntity();
        ReflectionTestUtils.setField(entity, "id", UUID.randomUUID());

        entity.setTitle("Attack on Titan");
        entity.setDescription("Description");
        entity.setCoverUrl("cover.jpg");
        entity.setOriginalLanguage(language);
        return entity;
    }
}