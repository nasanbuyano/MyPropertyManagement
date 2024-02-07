package com.miu.propertymanagement.domain.dto;

import com.miu.propertymanagement.domain.Offer;
import com.miu.propertymanagement.domain.Property;
import com.miu.propertymanagement.domain.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDetailResponseDTO {
    private Integer Id;

    private String firstName;

    private String lastName;

    private UserType userType;

    private Boolean active;

    private CredentialResponseDTO credential;

    private List<Property> propertyList;

    private List<Offer> offersFrom;

    private List<Offer> offersTo;

    private FavoriteListResponseDTO favoriteList;
}

