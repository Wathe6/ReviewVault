package io.envoi.media.credits.entity;

import io.envoi.media.media.entity.MediaItemEntity;
import io.envoi.media.common.entity.BaseNumericIdEntity;
import jakarta.persistence.CheckConstraint;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(
        name = "media_credit",
        schema = "media",
        check = @CheckConstraint(constraint = "person_id IS NOT NULL OR company_id IS NOT NULL")
)
public class MediaCreditEntity extends BaseNumericIdEntity<Long> {

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn(name = "media_item_id", nullable = false)
    private MediaItemEntity mediaItem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id", nullable = true)
    private PersonEntity person;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = true)
    private CompanyEntity company;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn(name = "credit_role_id", nullable = false)
    private CreditRoleEntity creditRole;
}
