package io.envoi.review.rate.entity;

import io.envoi.contracts.common.BaseNumericIdEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "rating_scale", schema = "review")
public class RatingScaleEntity extends BaseNumericIdEntity<Short> {

    @Column(name = "name", length = 12, nullable = false)
    private String name;

    @Column(name = "min", precision = 5, scale = 2, nullable = false)
    private BigDecimal min;

    @Column(name = "max", precision = 5, scale = 2, nullable = false)
    private BigDecimal max;

    @Column(name = "step", precision = 5, scale = 2, nullable = false)
    private BigDecimal step;

    @Column(name = "total_steps", nullable = false)
    private Integer totalSteps;

    @Column(name = "type", nullable = false)
    private String type;

    public Integer normalize(BigDecimal value) {
        BigDecimal base = new BigDecimal(10000);
        int roundingValue = 0;

        return value
                .subtract(min)
                .multiply(base)
                .divide(
                        (max.subtract(min)
                        ), roundingValue, RoundingMode.HALF_UP)
                .intValueExact();
    }
}