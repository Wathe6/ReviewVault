package io.envoi.contracts.media.v1.group;

import io.envoi.contracts.common.EventMetadataV1;

public record MediaGroupDeletedV1(
        EventMetadataV1 metadata,
        MediaGroupSnapshotV1 data
) {}