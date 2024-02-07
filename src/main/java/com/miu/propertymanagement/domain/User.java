package com.miu.propertymanagement.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.miu.propertymanagement.domain.enums.UserType;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String firstName;

    private String lastName;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    private Boolean active;

    @JsonBackReference("credential")
    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    private Credential credential;

    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
    @JsonManagedReference("user")
    @JsonIgnore
    private List<Property> propertyList;

    @OneToMany(mappedBy = "offerFrom")
    @JsonBackReference("offer-from")
    @JsonIgnore
    private List<Offer> offersFrom;

    @OneToMany(mappedBy = "offerTo")
    @JsonBackReference("offer-to")
    @JsonIgnore
    private List<Offer> offersTo;

//    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
//    @JsonBackReference("user-favorite")
//    @JsonIgnore
//    private FavoriteList favoriteList = new FavoriteList();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "user_favorite_properties",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "property_id"))
    private List<Property> favoriteList;

    public void addProperty(Property property) {
        this.propertyList.add(property);
        property.setUser(this);
    }
}
