package io.envoi.media.credits.controller;

import io.envoi.media.credits.dto.request.PersonRequest;
import io.envoi.media.credits.dto.response.PersonResponse;
import io.envoi.media.credits.mapping.PersonMapper;
import io.envoi.media.credits.service.PersonService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/persons")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    private final PersonMapper personMapper;

    @GetMapping("/{id}")
    public ResponseEntity<PersonResponse> get(
            @PathVariable UUID id,
            @RequestParam(required = false) String lang
    ) {
        if (lang != null) {
            return ResponseEntity.ok(personService.findLocalizedById(id, lang));
        }

        return ResponseEntity.ok(personMapper.toResponse(personService.findById(id)));
    }
/*
    @GetMapping("/search/{name}")
    public ResponseEntity<List<PersonResponse>> get(@PathVariable String name) {

        return ResponseEntity.ok(personService.findByName(name).stream().map(personMapper::toResponse).toList());
    }*/

    @PostMapping("")
    public ResponseEntity<PersonResponse> create(@Valid @RequestBody PersonRequest personRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(personMapper.toResponse(personService.create(personRequest)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonResponse> update(@PathVariable UUID id, @Valid @RequestBody PersonRequest personRequest) {
        return ResponseEntity.ok(personMapper.toResponse(personService.update(id, personRequest)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
        personService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
