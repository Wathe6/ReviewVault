package io.envoi.media.credits.mapping;

import io.envoi.media.credits.dto.request.PersonRequest;
import io.envoi.media.credits.dto.response.PersonResponse;
import io.envoi.media.credits.entity.PersonEntity;
import io.envoi.media.credits.entity.PersonTranslationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    PersonResponse toResponse(PersonEntity person);

    @Mapping(target = "id", ignore = true)
    PersonEntity toEntity(PersonRequest personRequest);

    default PersonResponse toResponseWithLanguage(PersonEntity person, PersonTranslationEntity translation) {
        return new PersonResponse(
                person.getId(),
                translation != null && translation.getName() != null
                        ? translation.getName()
                        : person.getName(),
                translation != null && translation.getBiography() != null
                        ? translation.getBiography()
                        : person.getBiography(),
                person.getBirthDate(),
                person.getDeathDate(),
                person.getCoverUrl()
        );
    }

    @Mapping(target = "id", ignore = true)
    void update(
            PersonRequest request,
            @MappingTarget PersonEntity entity
    );
}
