package io.envoi.media.media.dto.request;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

public record MediaItemRequest(
        @NotBlank String title,
        @Nullable UUID mediaGroupId,
        @NotNull Short mediaFormatId,
        @NotNull Short mediaCategoryId,
        @NotNull Short mediaItemStatusId,
        @Nullable String description,
        @Nullable LocalDate releaseDate,
        @Nullable LocalDate endDate,
        @NotBlank @Size(max = 2) String originalLanguageCode,
        @Nullable String coverUrl,
        @Nullable Map<String, String> metadata
        ) {
}
