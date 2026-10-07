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
@Table(name = "company", schema = "media")
public class CompanyEntity extends BaseUuidEntity {

    @NotNull
    @Column(name="name", nullable=false)
    private String name;

    @Column(name = "description", nullable = true, columnDefinition = "TEXT")
    private String description;

    @Column(name = "founded_date", nullable = true)
    private LocalDate foundedDate;

    @Column(name = "closed_date", nullable = true)
    private LocalDate closedDate;

    @Column(name = "cover_url", nullable = true)
    private String coverUrl;
}
