package io.envoi.review.exception;

public record ApiErrorResponse(
        int status,
        String code,
        String message
) {
}
