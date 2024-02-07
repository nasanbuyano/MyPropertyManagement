package com.miu.propertymanagement.service;

import com.miu.propertymanagement.domain.dto.PropertyDetailResponseDTO;
import com.miu.propertymanagement.domain.dto.PropertyRequestDTO;
import com.miu.propertymanagement.domain.dto.PropertyResponseDTO;

import java.util.List;


public interface PropertyService {
    List<PropertyResponseDTO> getAllProperty();

    PropertyDetailResponseDTO getPropertyById(Integer id);

    PropertyResponseDTO addProperty(Integer userId, PropertyRequestDTO propertyRequestDTO);

    PropertyResponseDTO updateProperty(Integer id, PropertyRequestDTO propertyRequestDTO);

    PropertyResponseDTO deleteProperty(Integer id);

    List<PropertyResponseDTO> getAllPropertyByOwner(Integer userId);

    PropertyResponseDTO CancelContingency(Integer userId, Integer propertyId);
}
