package io.envoi.media.media.dto.response;

public record MediaCardResponse(
        String id,
        String entityType,
        String title,
        String coverUrl
) {
}
