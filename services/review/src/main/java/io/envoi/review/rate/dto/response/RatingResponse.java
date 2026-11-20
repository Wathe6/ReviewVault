package io.envoi.review.rate.dto.response;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record RatingResponse(
        UUID mediaItemId,
        UUID mediaGroupId,

        @NotNull
        @Digits(integer = 3, fraction = 2)
        BigDecimal value) {
}
