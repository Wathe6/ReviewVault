package io.envoi.media.exception;

public record ApiErrorResponse(
        int status,
        String code,
        String message
) {
}
