package com.backendchallenge.plazoletaservice.application.http.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DishResponse {
    private Long id;
    private String name;
    private String description;
    private Integer price;
    private String urlImage;
    private Boolean available;
    private List<CategoryResponse> categories;

}
