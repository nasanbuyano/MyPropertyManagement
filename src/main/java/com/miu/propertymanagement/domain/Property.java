package com.miu.propertymanagement.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.miu.propertymanagement.domain.enums.PropertyHomeType;
import com.miu.propertymanagement.domain.enums.PropertySaleType;
import com.miu.propertymanagement.domain.enums.PropertyStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String location;

    private String imgUrl;

    private Integer numberOfRooms;

    @Enumerated(EnumType.STRING)
    private PropertyHomeType propertyHomeType;

    @Enumerated(EnumType.STRING)
    private PropertySaleType propertySaleType;

    @Enumerated(EnumType.STRING)
    private PropertyStatus propertyStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference("user")
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "property")
    @JsonBackReference("offer-property")
    @JsonIgnore
    private List<Offer> offers;

    @ManyToMany(mappedBy = "propertyList")
//    @JsonManagedReference("property-FavoriteList")
    private List<FavoriteList> favoriteLists;
}
