package com.backendchallenge.plazoletaservice.domain.model;

public class OrderedDish {
    private Long id;
    private Long dishId;
    private Integer quantity;

    public OrderedDish() {
    }

    public OrderedDish(Long id, Long dishId, Integer quantity) {
        this.id = id;
        this.dishId = dishId;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDishId() {
        return dishId;
    }

    public void setDishId(Long dishId) {
        this.dishId = dishId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
