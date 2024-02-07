package com.miu.propertymanagement.service;

import com.miu.propertymanagement.domain.dto.OfferRequestDTO;
import com.miu.propertymanagement.domain.dto.OfferResponseDTO;
import com.miu.propertymanagement.domain.enums.OfferStatus;

import java.util.List;

public interface OfferService {
    OfferResponseDTO submitOffer(OfferRequestDTO offerRequestDTO);

    List<OfferResponseDTO> getCustomerOffersById(Integer userId);

    List<OfferResponseDTO> getOwnerOffersById(Integer userId);

    Object changeOfferStatus(Integer userId, Integer offerId, OfferStatus change);

    List<OfferResponseDTO> findHistoryOffersByOwner(Integer offerToId);

    List<OfferResponseDTO> findLiveOffersByOwner(Integer offerToId);

    List<OfferResponseDTO> findHistoryOffersByCustomer(Integer offerFromId);

    List<OfferResponseDTO> findLiveOffersByCustomer(Integer offerFromId);
}
