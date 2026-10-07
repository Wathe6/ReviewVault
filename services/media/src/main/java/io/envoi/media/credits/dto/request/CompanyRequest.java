package io.envoi.media.credits.dto.request;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CompanyRequest (
        @NotBlank String name,
        @Nullable String description,
        @Nullable LocalDate foundedDate,
        @Nullable LocalDate closedDate,
        @Nullable String coverUrl
) {}
