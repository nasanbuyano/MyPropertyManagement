package com.miu.propertymanagement.service.impl;

import com.miu.propertymanagement.domain.FavoriteList;
import com.miu.propertymanagement.domain.Property;
import com.miu.propertymanagement.domain.User;
import com.miu.propertymanagement.domain.dto.FavoriteListResponseDTO;
import com.miu.propertymanagement.domain.enums.UserType;
import com.miu.propertymanagement.integration.Mapper.ListMapper;
import com.miu.propertymanagement.integration.exception.PMSException;
import com.miu.propertymanagement.repository.FavoriteListRepository;
import com.miu.propertymanagement.repository.PropertyRepository;
import com.miu.propertymanagement.repository.UserRepository;
import com.miu.propertymanagement.service.FavoriteListService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteListServiceImpl implements FavoriteListService {

    @Autowired
    ModelMapper modelMapper;
    @Autowired
    ListMapper listMapper;
    @Autowired
    private FavoriteListRepository favoriteListRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PropertyRepository propertyRepository;

    public FavoriteListResponseDTO addPropertyToFavoriteList(Integer userId, Integer propertyId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new PMSException("User not found"));
        Property property = propertyRepository.findById(propertyId).orElseThrow(() -> new PMSException("Property not found"));

        if(user.getUserType() != UserType.Customer)
            throw new PMSException("Only customer can add property to Favorite list");

        FavoriteList favoriteList = user.getFavoriteList();
        if (favoriteList == null) {
            favoriteList = new FavoriteList();
            favoriteList.setUser(user);
            user.setFavoriteList(favoriteList);
        }

        List<Property> properties = favoriteList.getPropertyList();

        if (properties.contains(property))
            throw new PMSException("Property is already in the favorite list");
        properties.add(property);
        return modelMapper.map(favoriteListRepository.save(favoriteList), FavoriteListResponseDTO.class);
    }

    @Override
    public FavoriteListResponseDTO removePropertyFromFavoriteList(Integer userId, Integer propertyId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new PMSException("User not found"));
        Property property = propertyRepository.findById(propertyId).orElseThrow(() -> new PMSException("Property not found"));

        if(user.getUserType() != UserType.Customer)
            throw new PMSException("Only customer can remove property from Favorite list");

        FavoriteList favoriteList = user.getFavoriteList();
        if (favoriteList == null) {
            favoriteList = new FavoriteList();
            favoriteList.setUser(user);
            user.setFavoriteList(favoriteList);
        }

        List<Property> properties = favoriteList.getPropertyList();

        if (!properties.contains(property))
            throw new PMSException("Property is not in the favorite list");
        properties.remove(property);
        return modelMapper.map(favoriteListRepository.save(favoriteList), FavoriteListResponseDTO.class);
    }

    @Override
    public FavoriteListResponseDTO getFavoriteListByUser(Integer userId) {
        FavoriteList favoriteList = favoriteListRepository.findByUser_Id(userId);
        if(favoriteList == null)
            return new FavoriteListResponseDTO();
        return modelMapper.map(favoriteList, FavoriteListResponseDTO.class);
//        return (List<FavoriteListResponseDTO>) listMapper.mapList(favoriteListRepository.findAllByUser_Id(userId), new FavoriteListResponseDTO());
    }
}