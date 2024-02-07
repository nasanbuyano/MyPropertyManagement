package com.miu.propertymanagement.service.impl;

import com.miu.propertymanagement.domain.Property;
import com.miu.propertymanagement.domain.User;
import com.miu.propertymanagement.domain.dto.PropertyDetailResponseDTO;
import com.miu.propertymanagement.domain.dto.PropertyRequestDTO;
import com.miu.propertymanagement.domain.dto.PropertyResponseDTO;
import com.miu.propertymanagement.domain.enums.PropertyStatus;
import com.miu.propertymanagement.domain.enums.UserType;
import com.miu.propertymanagement.integration.Mapper.ListMapper;
import com.miu.propertymanagement.integration.exception.PMSException;
import com.miu.propertymanagement.repository.PropertyRepository;
import com.miu.propertymanagement.repository.UserRepository;
import com.miu.propertymanagement.service.PropertyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    ModelMapper modelMapper;
    @Autowired
    ListMapper listMapper;
    @Autowired
    private PropertyRepository propertyRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<PropertyResponseDTO> getAllProperty() {
        return (List<PropertyResponseDTO>) listMapper.mapList(propertyRepository.findAllByUser_Active(true), new PropertyResponseDTO());
    }

    @Override
    public PropertyDetailResponseDTO getPropertyById(Integer id) {
        return modelMapper.map(propertyRepository.findById(id).orElseGet(null), PropertyDetailResponseDTO.class);
    }

    @Override
    public PropertyResponseDTO addProperty(Integer userId, PropertyRequestDTO propertyRequestDTO) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty())
            throw new PMSException("User not found");
        Property property = modelMapper.map(propertyRequestDTO, Property.class);
        User user = userOptional.get();

        if(user.getUserType() != UserType.Owner)
            throw new PMSException("Only Owner can add property");

        user.addProperty(property);
        propertyRepository.save(property);
        return modelMapper.map(property, PropertyResponseDTO.class);
    }

    @Override
    public PropertyResponseDTO updateProperty(Integer id, PropertyRequestDTO propertyRequestDTO) {
        Optional<Property> propertyOptional = propertyRepository.findById(id);
        if (propertyOptional.isEmpty())
            throw new PMSException("Property not found");
        Property property = propertyOptional.get();
        property.setLocation(propertyRequestDTO.getLocation());
        property.setImgUrl(propertyRequestDTO.getImgUrl());
        property.setNumberOfRooms(propertyRequestDTO.getNumberOfRooms());
        property.setPropertyHomeType(propertyRequestDTO.getPropertyHomeType());
        property.setPropertySaleType(propertyRequestDTO.getPropertySaleType());
        property.setPropertyStatus(propertyRequestDTO.getPropertyStatus());

        propertyRepository.save(property);
        return modelMapper.map(property, PropertyResponseDTO.class);
    }

    @Override
    public PropertyResponseDTO deleteProperty(Integer id) {
        Optional<Property> propertyOptional = propertyRepository.findById(id);
        if (propertyOptional.isEmpty())
            throw new PMSException("Property not found");
        Property property = propertyOptional.get();

        if (property.getPropertyStatus() == PropertyStatus.Pending) {
            throw new PMSException("Property cannot be deleted. Property status is Pending.");
        }

        if (property.getPropertyStatus() == PropertyStatus.Contingent) {
            throw new PMSException("Property cannot be deleted. Property status is Contingent.");
        }

        propertyRepository.deleteById(id);
        return modelMapper.map(property, PropertyResponseDTO.class);
    }

    @Override
    public List<PropertyResponseDTO> getAllPropertyByOwner(Integer userId) {
        return (List<PropertyResponseDTO>) listMapper.mapList(propertyRepository.findAllByUser_Id(userId), new PropertyResponseDTO());
    }

    @Override
    public PropertyResponseDTO CancelContingency(Integer userId, Integer propertyId) {
        Optional<Property> propertyOptional = propertyRepository.findById(propertyId);
        if (propertyOptional.isEmpty())
            throw new PMSException("Property not found");
        Property property = propertyOptional.get();

        if(property.getPropertyStatus() != PropertyStatus.Contingent)
            throw new PMSException("Property status is not Contingent");

        property.setPropertyStatus(PropertyStatus.Available);
        return modelMapper.map(propertyRepository.save(property), PropertyResponseDTO.class);
    }
}
