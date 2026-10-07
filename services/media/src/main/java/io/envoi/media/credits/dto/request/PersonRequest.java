package io.envoi.media.credits.dto.request;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record PersonRequest (
        @NotBlank String name,
        @Nullable String biography,
        @Nullable LocalDate birthDate,
        @Nullable LocalDate deathDate,
        @Nullable String coverUrl
        ) {}
