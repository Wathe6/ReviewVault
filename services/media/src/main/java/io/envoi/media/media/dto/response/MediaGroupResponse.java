package io.envoi.media.media.dto.response;

import java.util.UUID;

public record MediaGroupResponse(
    UUID id,
    String title,
    String description,
    String coverUrl,
    String originalLanguageCode
) {
}
