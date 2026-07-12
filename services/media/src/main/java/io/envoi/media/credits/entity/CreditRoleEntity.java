package io.envoi.media.credits.entity;

import io.envoi.media.common.entity.BaseNumericIdEntity;
import io.envoi.media.common.entity.BaseTranslationEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
@Table(name = "credit_role", schema = "media")
public class CreditRoleEntity extends BaseNumericIdEntity<Short> {

    @NotNull
    @Column(name = "original_name", nullable = false)
    private String originalName;

    @NotNull
    @Column(name = "normalized_name", nullable = false, unique = true)
    private String normalizedName;
}
