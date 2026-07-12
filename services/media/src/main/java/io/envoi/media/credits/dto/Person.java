package io.envoi.media.credits.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record Person (

        @NotNull
        String name,

        @Nullable
        String biography,

        @Nullable
        LocalDate birthDate,

        @Nullable
        LocalDate deathDate,

        @Nullable
        String coverUrl
) {}
