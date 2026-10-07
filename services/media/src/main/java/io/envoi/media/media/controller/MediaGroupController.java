package io.envoi.media.media.controller;

import io.envoi.media.media.dto.request.MediaGroupRequest;
import io.envoi.media.media.dto.response.MediaCardResponse;
import io.envoi.media.media.dto.response.MediaGroupResponse;
import io.envoi.media.media.entity.MediaGroupEntity;
import io.envoi.media.media.mapping.MediaGroupMapper;
import io.envoi.media.media.service.MediaGroupService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/groups")
@RequiredArgsConstructor
public class MediaGroupController {

    private final MediaGroupService mediaGroupService;

    private final MediaGroupMapper mediaGroupMapper;

    @GetMapping("/{id}")
    public ResponseEntity<MediaGroupResponse> get(@PathVariable UUID id) {

        return ResponseEntity.ok(mediaGroupMapper.toResponse(mediaGroupService.findById(id)));
    }

    @GetMapping("/{id}/card")
    public ResponseEntity<MediaCardResponse> getCard(@PathVariable UUID id) {

        MediaGroupEntity group = mediaGroupService.findById(id);

        return ResponseEntity.ok(mediaGroupMapper.toCard(group));
    }

//    @GetMapping("/{title}")

    @PostMapping("")
    public ResponseEntity<MediaGroupResponse> create(@Valid @RequestBody MediaGroupRequest mediaGroupRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                    mediaGroupMapper.toResponse(
                        mediaGroupService.create(mediaGroupRequest)
                    )
                );
    }

    @PutMapping("/{id}")
    public ResponseEntity<MediaGroupResponse> update(@PathVariable UUID id, @Valid @RequestBody MediaGroupRequest mediaGroupRequest) {
        return ResponseEntity.ok(
                mediaGroupMapper.toResponse(
                        mediaGroupService.update(id, mediaGroupRequest)
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
        mediaGroupService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
