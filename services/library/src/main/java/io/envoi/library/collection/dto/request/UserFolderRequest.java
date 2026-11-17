package io.envoi.library.collection.dto.request;

public record UserFolderRequest(
        String name,
        String coverUrl,
        Integer position
) {
}
