package io.envoi.review.rate.controller;

import io.envoi.review.rate.dto.request.UserRatingDescriptionRequest;
import io.envoi.review.rate.dto.response.UserRatingDescriptionResponse;
import io.envoi.review.rate.mapper.UserRatingDescriptionMapper;
import io.envoi.review.rate.service.UserRatingDescriptionService;
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
@RequestMapping("/api/v1/rating-descriptions")
@RequiredArgsConstructor
public class UserRatingDescriptionController {

    private final UserRatingDescriptionService userRatingDescriptionService;

    private final UserRatingDescriptionMapper userRatingDescriptionMapper;

    @GetMapping("/{id}")
    public ResponseEntity<UserRatingDescriptionResponse> get(@PathVariable Long id) {

        return ResponseEntity.ok(userRatingDescriptionMapper.toResponse(userRatingDescriptionService.findById(id)));
    }

    @PostMapping("")
    public ResponseEntity<UserRatingDescriptionResponse> create(@Valid @RequestBody UserRatingDescriptionRequest userRatingDescriptionRequest, UUID keycloakUserId) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        userRatingDescriptionMapper.toResponse(
                                userRatingDescriptionService.create(
                                        keycloakUserId,
                                        userRatingDescriptionRequest
                                ))
                );
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserRatingDescriptionResponse> update(@PathVariable Long id, @Valid @RequestBody UserRatingDescriptionRequest userRatingDescriptionRequest, UUID keycloakUserId) {

        return ResponseEntity.ok(
                userRatingDescriptionMapper.toResponse(
                        userRatingDescriptionService.update(
                                id,
                                keycloakUserId,
                                userRatingDescriptionRequest
                        )
                ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserRatingDescriptionResponse> delete(@PathVariable Long id) {

        userRatingDescriptionService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
