package io.envoi.media.classification.dto.request;

import jakarta.validation.constraints.NotNull;

public record MediaFormatRequest(
        @NotNull Short mediaCategoryId,
        @NotNull String defaultName,
        @NotNull String normalizedName
) {
}
