package io.envoi.media.media.dto.request;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record MediaGroupRequest(
    @NotBlank String title,
    @Nullable String description,
    @Nullable String coverUrl,
    @NotNull @Size(max = 2) String originalLanguageCode
) {
}
