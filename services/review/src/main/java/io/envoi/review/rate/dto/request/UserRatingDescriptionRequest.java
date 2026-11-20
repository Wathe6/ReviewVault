package io.envoi.review.rate.dto.request;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record UserRatingDescriptionRequest(
        @Digits(integer = 3, fraction = 2)
        @NotNull
        BigDecimal left,

        @Digits(integer = 3, fraction = 2)
        @NotNull
        BigDecimal right,

        @Size(max = 100)
        @NotNull
        String description
) {
}
