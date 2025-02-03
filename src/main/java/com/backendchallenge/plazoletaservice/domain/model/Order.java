package com.backendchallenge.plazoletaservice.domain.model;

import java.time.LocalDate;
import java.util.List;

public class Order {
    private Long id;
    private Long idClient;
    private LocalDate date;
    private String status;
    private Long idEmployee;
    private Long idRestaurant;
    private List<OrderedDish> dishes;

    public Order() {
    }

    public Order(Long id, Long idClient, LocalDate date, String status, Long idEmployee, Long idRestaurant, List<OrderedDish> dishes) {
        this.id = id;
        this.idClient = idClient;
        this.date = date;
        this.status = status;
        this.idEmployee = idEmployee;
        this.idRestaurant = idRestaurant;
        this.dishes = dishes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdClient() {
        return idClient;
    }

    public void setIdClient(Long idClient) {
        this.idClient = idClient;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(Long idEmployee) {
        this.idEmployee = idEmployee;
    }

    public Long getIdRestaurant() {
        return idRestaurant;
    }

    public void setIdRestaurant(Long idRestaurant) {
        this.idRestaurant = idRestaurant;
    }

    public List<OrderedDish> getDishes() {
        return dishes;
    }

    public void setDishes(List<OrderedDish> dishes) {
        this.dishes = dishes;
    }


}
