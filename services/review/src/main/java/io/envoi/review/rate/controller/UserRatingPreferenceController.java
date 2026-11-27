package io.envoi.review.rate.controller;

import io.envoi.review.rate.dto.request.UserRatingPreferenceRequest;
import io.envoi.review.rate.dto.response.UserRatingPreferenceResponse;
import io.envoi.review.rate.mapper.UserRatingPreferenceMapper;
import io.envoi.review.rate.service.UserRatingPreferenceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/rating-preferences")
@RequiredArgsConstructor
public class UserRatingPreferenceController {

    private final UserRatingPreferenceService userRatingPreferenceService;

    private final UserRatingPreferenceMapper userRatingPreferenceMapper;

    @GetMapping("/{id}")
    public ResponseEntity<UserRatingPreferenceResponse> get(@PathVariable Long id) {

        return ResponseEntity.ok(userRatingPreferenceMapper.toResponse(userRatingPreferenceService.findById(id)));
    }

    @PostMapping("")
    public ResponseEntity<UserRatingPreferenceResponse> create(@Valid @RequestBody UserRatingPreferenceRequest userRatingPreferenceRequest, UUID keycloakUserId) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        userRatingPreferenceMapper.toResponse(
                                userRatingPreferenceService.create(
                                        keycloakUserId,
                                        userRatingPreferenceRequest
                                )));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserRatingPreferenceResponse> update(@PathVariable Long id, @Valid @RequestBody UserRatingPreferenceRequest userRatingPreferenceRequest, UUID keycloakUserId) {

        return ResponseEntity.ok(
                userRatingPreferenceMapper.toResponse(
                        userRatingPreferenceService.update(
                                id,
                                keycloakUserId,
                                userRatingPreferenceRequest))
        );
    }
}
