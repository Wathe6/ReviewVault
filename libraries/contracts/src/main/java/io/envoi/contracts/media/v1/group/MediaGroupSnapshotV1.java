package io.envoi.contracts.media.v1.group;

import java.util.UUID;

public record MediaGroupSnapshotV1(
    UUID id,
    String title,
    String coverUrl
) {}