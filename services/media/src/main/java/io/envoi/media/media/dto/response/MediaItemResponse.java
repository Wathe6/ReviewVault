package io.envoi.media.media.dto.response;

import io.envoi.media.classification.dto.response.MediaGenreResponse;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public record MediaItemResponse(
        UUID id,
        String title,
        UUID mediaGroupId,
        Short mediaFormatId,
        Short mediaCategoryId,
        Short mediaItemStatusId,
        String description,
        LocalDate releaseDate,
        LocalDate endDate,
        String originalLanguageCode,
        String coverUrl,
        Map<String, String> metadata,

        List<MediaGenreResponse> genres
        ) {
}
