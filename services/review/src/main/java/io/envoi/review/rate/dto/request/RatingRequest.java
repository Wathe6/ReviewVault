package io.envoi.review.rate.dto.request;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record RatingRequest(
        UUID mediaItemId,
        UUID mediaGroupId,

        @NotNull
        @Digits(integer = 3, fraction = 2)
        BigDecimal value) {
}
