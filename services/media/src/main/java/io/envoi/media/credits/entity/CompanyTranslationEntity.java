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
        name = "company_translation",
        schema = "media",
        uniqueConstraints = @UniqueConstraint(columnNames = {"company_id", "language_id"})
)
public class CompanyTranslationEntity extends BaseTranslationEntity<Long> {

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn(name = "company_id",  nullable = false)
    private CompanyEntity company;

    @Column(name = "description", nullable = true, columnDefinition = "TEXT")
    private String description;
}