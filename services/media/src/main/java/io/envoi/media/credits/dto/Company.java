package io.envoi.media.credits.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record Company (

        @NotNull
        String name,

        @Nullable
        String description,

        @Nullable
        LocalDate foundedDate,

        @Nullable
        LocalDate closedDate,

        @Nullable
        String coverUrl
        ) {}
