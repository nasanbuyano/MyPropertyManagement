package com.miu.propertymanagement.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FavoriteListResponseDTO {
    private Integer Id;

    private List<PropertyResponseDTO> propertyList;
}

