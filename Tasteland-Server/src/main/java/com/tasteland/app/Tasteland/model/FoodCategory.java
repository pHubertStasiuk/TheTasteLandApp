package com.tasteland.app.Tasteland.model;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "food_category")
@Data
public class FoodCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "food_category_name")
    private String foodCategory;
}
