package io.envoi.contracts.media.v1;

import java.time.Instant;
import java.util.UUID;

public record EventMetadataV1(
        UUID eventId,
        String eventType,
        int eventVersion,
        Instant occurredAt,
        UUID aggregateId
) {}