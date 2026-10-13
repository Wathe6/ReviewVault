package io.envoi.profile.profile.dto.request;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;

public record UserProfileRequest(
        @NotBlank String name,
        @Nullable String coverUrl
) {
}
