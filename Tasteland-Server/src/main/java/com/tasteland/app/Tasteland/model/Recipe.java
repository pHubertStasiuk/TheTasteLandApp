package com.tasteland.app.Tasteland.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "recipe")
@Data
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "recipe_name")
    private String recipeName;
    @Column(name = "recipe_description")
    private String recipeDescription;
    @OneToOne
    @JoinColumn(name = "food_category_id")
    private FoodCategory foodCategory;
    @Column(name = "preparation_time")
    private Integer preparationTime;
    @Column(name = "cooking_time")
    private Integer cookingTime;
    @Column(name = "publication_date")
    private final LocalDateTime publicationDate = LocalDateTime.now();
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User recipeAuthor;

}
