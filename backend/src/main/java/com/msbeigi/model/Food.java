package com.msbeigi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String description;

    private Long price;

    @ManyToOne
    private Category foodCategory;

    @Column(length = 1000)
    @ElementCollection
    private List<String> images;

    private Boolean available;

    @ManyToOne
    private Restaurant restaurant;

    private Boolean isVegetarian;

    private Boolean isSeasonal;

    @ManyToMany
    private List<IngredientItem> ingredientItems = new ArrayList<>();

    private Date creationDate;
}
