package io.envoi.media.credits.mapping;

import io.envoi.media.credits.dto.request.CompanyRequest;
import io.envoi.media.credits.dto.response.CompanyResponse;
import io.envoi.media.credits.entity.CompanyEntity;
import io.envoi.media.credits.entity.CompanyTranslationEntity;
import org.jspecify.annotations.NonNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDate;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CompanyMapperTest {

    private final CompanyMapper mapper = Mappers.getMapper(CompanyMapper.class);

    @Test
    @DisplayName("Should map CompanyEntity to CompanyResponse")
    void shouldMapEntityToResponse() {

        CompanyEntity entity = getCompanyEntity();

        CompanyResponse response = mapper.toResponse(entity);

        assertThat(response)
                .extracting(
                        CompanyResponse::id,
                        CompanyResponse::name,
                        CompanyResponse::description,
                        CompanyResponse::foundedDate,
                        CompanyResponse::closedDate,
                        CompanyResponse::coverUrl
                )
                .containsExactly(
                        entity.getId(),
                        entity.getName(),
                        entity.getDescription(),
                        entity.getFoundedDate(),
                        entity.getClosedDate(),
                        entity.getCoverUrl()
                );
    }

    @Test
    @DisplayName("Should map CompanyEntity and CompanyTranslationEntity to CompanyResponse")
    void shouldMapEntityToResponseWithLanguage() {

        CompanyEntity entity = getCompanyEntity();

        CompanyTranslationEntity translation = new CompanyTranslationEntity();
        ReflectionTestUtils.setField(translation, "id", (long) 1);
        translation.setName("Company Translation Name");
        translation.setDescription("Company Translation Description");

        CompanyResponse response = mapper.toResponseWithLanguage(
                entity,
                translation
        );

        assertThat(response).isNotNull();
        assertThat(response.id()).isEqualTo(entity.getId());
        assertThat(response.name()).isEqualTo(translation.getName());
        assertThat(response.description()).isEqualTo(translation.getDescription());
    }

    @Test
    @DisplayName("Should map CompanyRequest to CompanyEntity")
    void shouldMapRequestToEntity() {

        CompanyRequest request = new CompanyRequest(
                "Response Name",
                "Description Name",
                LocalDate.now().minusMonths(1),
                LocalDate.now(),
                "coverUrl"
        );

        CompanyEntity entity = mapper.toEntity(request);

        assertThat(entity).isNotNull();
        assertThat(entity.getName()).isEqualTo(request.name());
        assertThat(entity.getDescription()).isEqualTo(request.description());
        assertThat(entity.getFoundedDate()).isEqualTo(request.foundedDate());
        assertThat(entity.getClosedDate()).isEqualTo(request.closedDate());
        assertThat(entity.getCoverUrl()).isEqualTo(request.coverUrl());
    }

    @Test
    @DisplayName("Should update CompanyEntity from CompanyRequest")
    void shouldUpdateEntityFromRequest() {
        CompanyEntity entity = getCompanyEntity();

        CompanyRequest request = new CompanyRequest(
                "Response Name",
                "Description Name",
                LocalDate.now().minusMonths(1),
                LocalDate.now(),
                "coverUrl"
        );

        mapper.update(request, entity);

        assertThat(entity).isNotNull();
        assertThat(entity.getName()).isEqualTo(request.name());
        assertThat(entity.getDescription()).isEqualTo(request.description());
        assertThat(entity.getFoundedDate()).isEqualTo(request.foundedDate());
        assertThat(entity.getClosedDate()).isEqualTo(request.closedDate());
        assertThat(entity.getCoverUrl()).isEqualTo(request.coverUrl());
    }

    private static @NonNull CompanyEntity getCompanyEntity() {
        CompanyEntity entity = new CompanyEntity();
        ReflectionTestUtils.setField(entity, "id", UUID.randomUUID());

        entity.setName("Company Name");
        entity.setDescription("Company Description");
        entity.setFoundedDate(LocalDate.now().minusMonths(1));
        entity.setClosedDate(null);
        entity.setCoverUrl(null);
        return entity;
    }
}