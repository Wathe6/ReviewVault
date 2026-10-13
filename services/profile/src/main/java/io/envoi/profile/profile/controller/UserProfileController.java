package io.envoi.profile.profile.controller;

import io.envoi.profile.profile.dto.request.UserProfileRequest;
import io.envoi.profile.profile.dto.response.UserProfileResponse;
import io.envoi.profile.profile.entity.UserProfileEntity;
import io.envoi.profile.profile.mapper.UserProfileMapper;
import io.envoi.profile.profile.service.UserProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
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
@RequestMapping("/api/v1/profiles")
@RequiredArgsConstructor
public class UserProfileController {

    private final UserProfileService userProfileService;

    private final UserProfileMapper userProfileMapper;

    @GetMapping("me")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<UserProfileResponse> getUserProfile(@AuthenticationPrincipal Jwt jwt) {

        String subject = jwt.getSubject();
        String username = jwt.getClaimAsString("preferred_username");
        UUID keycloakUserId = UUID.fromString(subject);

        UserProfileEntity entity = userProfileService.findByKeycloakUserId(
                keycloakUserId,
                new UserProfileRequest(username, "")
        );

        return entity.getDeleted() ?
                ResponseEntity.status(HttpStatus.GONE).build() :
                ResponseEntity.ok(userProfileMapper.toResponse(entity));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserProfileResponse> get(@PathVariable UUID id) {

        UserProfileEntity entity = userProfileService.findById(id);

        return entity.getDeleted() ?
                ResponseEntity.status(HttpStatus.GONE).build() :
                ResponseEntity.ok(userProfileMapper.toResponse(entity));
    }

    @PostMapping("")
    public ResponseEntity<UserProfileResponse> create(
            @Valid @RequestBody UserProfileRequest request,
            @AuthenticationPrincipal Jwt jwt
    ) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        userProfileMapper.toResponse(
                                userProfileService.create(
                                        request,
                                        UUID.fromString(jwt.getSubject()))
                        )
                );
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserProfileResponse> update(@PathVariable UUID id, @Valid @RequestBody UserProfileRequest request) {

        return ResponseEntity.ok(
                userProfileMapper.toResponse(
                        userProfileService.update(id, request)
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {

        userProfileService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
