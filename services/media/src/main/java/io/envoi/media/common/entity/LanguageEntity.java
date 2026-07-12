package io.envoi.media.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "language", schema = "media")
public class LanguageEntity extends BaseNumericIdEntity<Short> {

    @NotNull
    @Size(max = 2)
    @Column(name = "code", nullable = false, length = 2)
    private String code;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "native_name", nullable = false)
    private String nativeName;
}
