package io.envoi.media.credits.controller;

import io.envoi.media.credits.dto.request.CompanyRequest;
import io.envoi.media.credits.dto.response.CompanyResponse;
import io.envoi.media.credits.mapping.CompanyMapper;
import io.envoi.media.credits.service.CompanyService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/companies")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    private final CompanyMapper companyMapper;

    @GetMapping("/{id}")
    public ResponseEntity<CompanyResponse> get(
            @PathVariable UUID id,
            @RequestParam(required = false) String lang) {

        if(lang != null) {
            return ResponseEntity.ok(companyService.findLocalizedById(id, lang));
        }

        return ResponseEntity.ok(companyMapper.toResponse(companyService.findById(id)));
    }

    /*@GetMapping("/search/{name}")
    public ResponseEntity<List<CompanyResponse>> get(@PathVariable String name) {
        return ResponseEntity.ok(
                companyService.findByName(name)
                        .stream()
                        .map(companyMapper::toResponse)
                        .toList()
                );
    }*/

    @PostMapping("")
    public ResponseEntity<CompanyResponse> create(@Valid @RequestBody CompanyRequest companyRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(companyMapper.toResponse(companyService.create(companyRequest)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompanyResponse> update(@PathVariable UUID id, @Valid @RequestBody CompanyRequest companyRequest) {
        return ResponseEntity.ok(companyMapper.toResponse(companyService.update(id, companyRequest)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
        companyService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
