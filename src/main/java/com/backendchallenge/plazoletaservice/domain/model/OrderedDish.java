package com.backendchallenge.plazoletaservice.domain.model;

public class OrderedDish {
    private Long id;
    private Long idDish;
    private Integer quantity;

    public OrderedDish() {
    }

    public OrderedDish(Long id, Long idDish, Integer quantity) {
        this.id = id;
        this.idDish = idDish;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdDish() {
        return idDish;
    }

    public void setIdDish(Long idDish) {
        this.idDish = idDish;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
