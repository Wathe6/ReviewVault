package io.envoi.contracts.media.v1.item;

import java.util.UUID;

public record MediaItemSnapshotV1(
    UUID id,
    String title,
    String coverUrl
) {}