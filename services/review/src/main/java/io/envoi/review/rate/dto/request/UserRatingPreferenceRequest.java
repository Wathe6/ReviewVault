package io.envoi.review.rate.dto.request;

import jakarta.validation.constraints.NotNull;

public record UserRatingPreferenceRequest(
        @NotNull
        Short ratingScaleId
) {
}
