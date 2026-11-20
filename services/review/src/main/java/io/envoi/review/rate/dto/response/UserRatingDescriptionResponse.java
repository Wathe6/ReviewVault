package io.envoi.review.rate.dto.response;

import java.math.BigDecimal;

public record UserRatingDescriptionResponse(
        BigDecimal left,
        BigDecimal right,
        String description
) {
}
