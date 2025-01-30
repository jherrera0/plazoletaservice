package com.backendchallenge.plazoletaservice.domain.model;

public class Dish {
    private Long id;
    private Long idRestaurant;
    private String name;
    private Integer price;
    private String description;
    private String urlImage;
    private String category;
    private Boolean available;

    public Dish() {
    }

    public Dish(Long id, Long idRestaurant, String name, Integer price, String description, String urlImage, String category) {
        this.id = id;
        this.idRestaurant = idRestaurant;
        this.name = name;
        this.price = price;
        this.description = description;
        this.urlImage = urlImage;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdRestaurant() {
        return idRestaurant;
    }

    public void setIdRestaurant(Long idRestaurant) {
        this.idRestaurant = idRestaurant;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
}
