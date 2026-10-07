package io.envoi.media.classification.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record MediaGenreRequest(
        @NotNull String defaultName,
        @NotNull String normalizedName,
        @NotNull String description
) {
}
