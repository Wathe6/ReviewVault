package io.envoi.media.credits.dto.response;

import java.time.LocalDate;
import java.util.UUID;

public record CompanyResponse (
    UUID id,
    String name,
    String description,
    LocalDate foundedDate,
    LocalDate closedDate,
    String coverUrl
    ) {}
