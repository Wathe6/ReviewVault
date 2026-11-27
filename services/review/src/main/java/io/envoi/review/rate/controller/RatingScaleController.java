package io.envoi.review.rate.controller;

import io.envoi.review.rate.dto.response.RatingScaleResponse;
import io.envoi.review.rate.mapper.RatingScaleMapper;
import io.envoi.review.rate.service.RatingScaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rating-scales")
@RequiredArgsConstructor
public class RatingScaleController {

    private final RatingScaleService ratingScaleService;

    private final RatingScaleMapper ratingScaleMapper;

    @GetMapping("")
    public ResponseEntity<List<RatingScaleResponse>> findAll() {

        return ResponseEntity.ok(ratingScaleService.findAll()
                .stream()
                .map(ratingScaleMapper::toResponse)
                .toList()
        );
    }

    @GetMapping("/{name}")
    public ResponseEntity<RatingScaleResponse> findByName(@PathVariable String name) {

        return ResponseEntity.ok(ratingScaleMapper.toResponse(ratingScaleService.findByName(name)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RatingScaleResponse> findById(@PathVariable Short id) {

        return ResponseEntity.ok(ratingScaleMapper.toResponse(ratingScaleService.findById(id)));
    }
}
