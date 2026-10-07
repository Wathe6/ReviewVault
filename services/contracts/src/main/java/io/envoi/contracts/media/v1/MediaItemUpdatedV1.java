package io.envoi.contracts.media.v1;

public record MediaItemUpdatedV1(
        EventMetadataV1 metadata,
        MediaItemSnapshotV1 data
) {}