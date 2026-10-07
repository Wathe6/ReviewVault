package io.envoi.media.classification.dto.request;

import jakarta.validation.constraints.NotNull;

public record MediaCategoryRequest(
        @NotNull String defaultName,
        @NotNull String normalizedName
) {
}
