package io.envoi.media.credits.dto.response;

import java.time.LocalDate;
import java.util.UUID;

public record PersonResponse(
        UUID id,
        String name,
        String biography,
        LocalDate birthDate,
        LocalDate deathDate,
        String coverUrl
        ) {}
