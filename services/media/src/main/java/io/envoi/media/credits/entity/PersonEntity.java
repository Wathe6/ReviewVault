package io.envoi.media.credits.entity;

import io.envoi.contracts.common.BaseUuidEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "person", schema = "media")
public class PersonEntity extends BaseUuidEntity {

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "biography", nullable = true, columnDefinition = "TEXT")
    private String biography;

    @Column(name = "birth_date", nullable = true)
    private LocalDate birthDate;

    @Column(name = "death_date", nullable = true)
    private LocalDate deathDate;

    @Column(name = "cover_url", nullable = true)
    private String coverUrl;
}
