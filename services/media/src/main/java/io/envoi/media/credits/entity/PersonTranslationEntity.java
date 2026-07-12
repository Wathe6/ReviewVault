package io.envoi.media.credits.entity;

import io.envoi.media.common.entity.BaseTranslationEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
        name = "person_translation",
        schema = "media",
        uniqueConstraints = @UniqueConstraint(columnNames = {"person_id", "language_id"})
)
public class PersonTranslationEntity extends BaseTranslationEntity<Long> {

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn(name = "person_id", nullable = false)
    private PersonEntity person;

    @Column(name = "biography", nullable = true, columnDefinition = "TEXT")
    private String biography;
}
