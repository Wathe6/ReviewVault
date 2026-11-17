package io.envoi.contracts.profile.v1;

import io.envoi.contracts.common.EventMetadataV1;

public record UserProfileDeletedV1(
        EventMetadataV1 metadata,
        UserProfileSnapshotV1 data
) {}