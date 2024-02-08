package com.miu.propertymanagement.domain.dto;

//import com.miu.propertymanagement.domain.FavoriteList;
import com.miu.propertymanagement.domain.enums.PropertyHomeType;
import com.miu.propertymanagement.domain.enums.PropertySaleType;
import com.miu.propertymanagement.domain.enums.PropertyStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PropertyDetailResponseDTO {
    private Integer id;

    private String location;

    private String imgUrl;

    private Integer numberOfRooms;

    private PropertyHomeType propertyHomeType;

    private PropertySaleType propertySaleType;

    private PropertyStatus propertyStatus;

//    private List<Offer> offers;

//    private List<FavoriteList> favoriteLists;

    private UserResponseDTO user;
}
