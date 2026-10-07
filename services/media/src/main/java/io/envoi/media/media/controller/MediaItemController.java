package io.envoi.media.media.controller;

import io.envoi.media.media.dto.request.MediaItemRequest;
import io.envoi.media.media.dto.response.MediaItemResponse;
import io.envoi.media.media.mapping.MediaItemMapper;
import io.envoi.media.media.service.MediaItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/items")
@RequiredArgsConstructor
public class MediaItemController {

    private final MediaItemService mediaItemService;

    private final MediaItemMapper mediaItemMapper;

    @GetMapping("/{id}")
    public ResponseEntity<MediaItemResponse> get(@PathVariable UUID id) {

        return ResponseEntity.ok(mediaItemMapper.toResponse(mediaItemService.findById(id)));
    }
/*
    @GetMapping("/{group-id}")

    @GetMapping("/{title}")

    @GetMapping("/{category-id}")

    @GetMapping("/{format-id}")

    @GetMapping("/{genre-id}")

    @GetMapping("/{status-id}")

    @GetMapping("/{person-id}")

    @GetMapping("/{company-id}")
*/
    @PostMapping("")
    public ResponseEntity<MediaItemResponse> create(@Valid @RequestBody MediaItemRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        mediaItemMapper.toResponse(
                                mediaItemService.create(request)
                        )
                );
    }
/*
    @PostMapping("/{id}/{title}")

    @PostMapping("/{id}/{language}")
*/
    @PutMapping("/{id}")
    public ResponseEntity<MediaItemResponse> update(@PathVariable UUID id, @Valid @RequestBody MediaItemRequest request) {

        return ResponseEntity.ok(
                mediaItemMapper.toResponse(
                        mediaItemService.update(id, request)
                )
        );
    }
/*
    @PutMapping("/{id}/{groupd-id}")
*/

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {

        mediaItemService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
/*
    @DeleteMapping("/{id}/{title}")

    @DeleteMapping("/{id}/{language}")
*/
}
