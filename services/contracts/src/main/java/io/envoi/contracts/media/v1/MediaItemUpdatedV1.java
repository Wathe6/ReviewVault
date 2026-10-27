package io.envoi.contracts.media.v1;

import io.envoi.contracts.common.EventMetadataV1;

public record MediaItemUpdatedV1(
        EventMetadataV1 metadata,
        MediaItemSnapshotV1 data
) {}