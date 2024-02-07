package com.miu.propertymanagement.domain.dto;

import com.miu.propertymanagement.domain.enums.OfferType;
import lombok.Getter;

@Getter
public class OfferRequestDTO {
    Integer userId;

    Double price;

    OfferType offerType;

    Integer propertyId;
}
