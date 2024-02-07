package com.miu.propertymanagement.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class FavoriteList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "favoriteList")
    @JsonManagedReference("user-favorite")
    private User user;

    @ManyToMany
    private List<Property> propertyList = new ArrayList<>();

    // Other properties and methods as needed
}