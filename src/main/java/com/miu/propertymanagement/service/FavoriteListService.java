package com.miu.propertymanagement.service;

import com.miu.propertymanagement.domain.dto.FavoriteListResponseDTO;

public interface FavoriteListService {
    FavoriteListResponseDTO addPropertyToFavoriteList(Integer userId, Integer propertyId);

    FavoriteListResponseDTO removePropertyFromFavoriteList(Integer userId, Integer propertyId);

    FavoriteListResponseDTO getFavoriteListByUser(Integer userId);
}
