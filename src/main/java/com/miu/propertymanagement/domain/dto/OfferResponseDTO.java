package com.miu.propertymanagement.domain.dto;

import com.miu.propertymanagement.domain.enums.OfferStatus;
import com.miu.propertymanagement.domain.enums.OfferType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
public class OfferResponseDTO {
    private Integer id;

    private OfferStatus offerStatusFrom;

    private OfferStatus offerStatusTo;

    private UserResponseDTO offerFrom;

    private UserResponseDTO offerTo;

    private Double price;

    private OfferType offerType;

    private PropertyResponseDTO property;

    private LocalDateTime localDateTime;
}
