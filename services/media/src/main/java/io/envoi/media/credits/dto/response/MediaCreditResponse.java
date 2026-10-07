package io.envoi.media.credits.dto.response;

public record MediaCreditResponse(
        Long id,
        String role,
        PersonResponse person,
        CompanyResponse company
) {}
