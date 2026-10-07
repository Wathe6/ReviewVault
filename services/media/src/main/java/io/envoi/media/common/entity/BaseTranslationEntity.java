package io.envoi.media.common.entity;

import io.envoi.contracts.common.BaseNumericIdEntity;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseTranslationEntity<ID extends Number> extends BaseNumericIdEntity<ID> {

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn(name = "language_id", nullable = false)
    private LanguageEntity language;

    @Column(name = "name", nullable = true)
    private String name;
}
