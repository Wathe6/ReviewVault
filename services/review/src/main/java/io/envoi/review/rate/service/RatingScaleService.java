package io.envoi.review.rate.service;

import io.envoi.review.rate.entity.RatingScaleEntity;
import io.envoi.review.rate.repository.RatingScaleRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class RatingScaleService {

    private final RatingScaleRepository ratingScaleRepository;

    @Transactional(readOnly = true)
    public List<RatingScaleEntity> findAll() {

        return ratingScaleRepository.findAll();
    }

    @Transactional(readOnly = true)
    public RatingScaleEntity findById(Short id) {

        return ratingScaleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("RatingScaleEntity not found " + id));
    }

    @Transactional(readOnly = true)
    public RatingScaleEntity findByName(String name) {

        return ratingScaleRepository.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException("RatingScaleEntity not found " + name));
    }
}
