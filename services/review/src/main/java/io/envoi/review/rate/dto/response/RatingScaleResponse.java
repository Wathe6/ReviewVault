package io.envoi.review.rate.dto.response;

import java.math.BigDecimal;

public record RatingScaleResponse(
        String name,
        BigDecimal min,
        BigDecimal max,
        BigDecimal step,
        Integer totalSteps,
        String type
) {
}
