package io.envoi.media.media.entity;

import io.envoi.contracts.common.BaseNumericIdEntity;
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
@Table(name = "media_item_status", schema = "media")
public class MediaItemStatusEntity extends BaseNumericIdEntity<Short> {

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;
}
