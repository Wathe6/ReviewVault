package io.envoi.media.credits.mapping;

import io.envoi.media.credits.dto.request.CompanyRequest;
import io.envoi.media.credits.dto.response.CompanyResponse;
import io.envoi.media.credits.entity.CompanyEntity;
import io.envoi.media.credits.entity.CompanyTranslationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CompanyMapper {

    CompanyResponse toResponse(CompanyEntity company);

    @Mapping(target = "id", ignore = true)
    CompanyEntity toEntity(CompanyRequest companyRequest);

    default CompanyResponse toResponseWithLanguage(
            CompanyEntity company,
            CompanyTranslationEntity translation
    ) {
        return new CompanyResponse(
                company.getId(),
                translation != null && translation.getName() != null
                        ? translation.getName()
                        : company.getName(),
                translation != null && translation.getDescription() != null
                        ? translation.getDescription()
                        : company.getDescription(),
                company.getFoundedDate(),
                company.getClosedDate(),
                company.getCoverUrl()
        );
    }

    @Mapping(target = "id", ignore = true)
    void update(
            CompanyRequest request,
            @MappingTarget CompanyEntity entity
    );
}
