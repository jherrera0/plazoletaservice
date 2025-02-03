package com.backendchallenge.plazoletaservice.application.jpa.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RestaurantsWorkerId implements Serializable {

    private Long restaurantId;
    private Long employedId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RestaurantsWorkerId that)) return false;
        return restaurantId.equals(that.restaurantId) && employedId.equals(that.employedId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(restaurantId, employedId);
    }
}