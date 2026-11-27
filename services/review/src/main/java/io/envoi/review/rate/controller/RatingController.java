package io.envoi.review.rate.controller;

import io.envoi.review.rate.dto.request.RatingRequest;
import io.envoi.review.rate.dto.response.RatingResponse;
import io.envoi.review.rate.mapper.RatingMapper;
import io.envoi.review.rate.service.RatingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/api/v1/ratings")
@RequiredArgsConstructor
public class RatingController {

    private final RatingService ratingService;

    private final RatingMapper ratingMapper;

    @GetMapping("/{id}")
    public ResponseEntity<RatingResponse> get(@PathVariable Long id) {

        return ResponseEntity.ok(ratingMapper.toResponse(ratingService.findById(id)));
    }

    @PostMapping("")
    public ResponseEntity<RatingResponse> create(
            @Valid @RequestBody RatingRequest ratingRequest,
            UUID keycloakUserId) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        ratingMapper.toResponse(
                                ratingService.create(
                                        keycloakUserId,
                                        ratingRequest)
                        )
                );
    }

    @PutMapping("/{id}")
    public ResponseEntity<RatingResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody RatingRequest ratingRequest,
            UUID keycloakUserId) {

        return ResponseEntity.ok(
                ratingMapper.toResponse(
                        ratingService.update(id, keycloakUserId, ratingRequest)
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        ratingService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
