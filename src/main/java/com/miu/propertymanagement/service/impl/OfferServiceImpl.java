package com.miu.propertymanagement.service.impl;

import com.miu.propertymanagement.domain.Offer;
import com.miu.propertymanagement.domain.Property;
import com.miu.propertymanagement.domain.User;
import com.miu.propertymanagement.domain.dto.OfferRequestDTO;
import com.miu.propertymanagement.domain.dto.OfferResponseDTO;
import com.miu.propertymanagement.domain.enums.OfferStatus;
import com.miu.propertymanagement.domain.enums.PropertyStatus;
import com.miu.propertymanagement.domain.enums.UserType;
import com.miu.propertymanagement.integration.Mapper.ListMapper;
import com.miu.propertymanagement.integration.exception.PMSException;
import com.miu.propertymanagement.repository.OfferRepository;
import com.miu.propertymanagement.repository.PropertyRepository;
import com.miu.propertymanagement.repository.UserRepository;
import com.miu.propertymanagement.service.OfferService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class OfferServiceImpl implements OfferService {

    @Autowired
    ModelMapper modelMapper;
    @Autowired
    ListMapper listMapper;
    @Autowired
    private OfferRepository offerRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PropertyRepository propertyRepository;

    @Override
    public List<OfferResponseDTO> getCustomerOffersById(Integer userId) {
        return (List<OfferResponseDTO>) listMapper.mapList(offerRepository.findAllByOfferFrom_Id(userId), new OfferResponseDTO());
    }

    @Override
    public List<OfferResponseDTO> getOwnerOffersById(Integer userId) {
        return (List<OfferResponseDTO>) listMapper.mapList(offerRepository.findAllByOfferTo_Id(userId), new OfferResponseDTO());
    }

    @Override
    public List<OfferResponseDTO> findHistoryOffersByOwner(Integer offerToId) {
        return (List<OfferResponseDTO>) listMapper.mapList(offerRepository.findHistoryOffersByOwner(offerToId), new OfferResponseDTO());
    }

    @Override
    public List<OfferResponseDTO> findLiveOffersByOwner(Integer offerToId) {
        return (List<OfferResponseDTO>) listMapper.mapList(offerRepository.findLiveOffersByOwner(offerToId), new OfferResponseDTO());
    }

    @Override
    public List<OfferResponseDTO> findHistoryOffersByCustomer(Integer offerFromId) {
        return (List<OfferResponseDTO>) listMapper.mapList(offerRepository.findHistoryOffersByCustomer(offerFromId), new OfferResponseDTO());
    }

    @Override
    public List<OfferResponseDTO> findLiveOffersByCustomer(Integer offerFromId) {
        return (List<OfferResponseDTO>) listMapper.mapList(offerRepository.findLiveOffersByCustomer(offerFromId), new OfferResponseDTO());
    }

    @Override
    public Object changeOfferStatus(Integer userId, Integer offerId, OfferStatus change) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty())
            throw new PMSException("User not Found");
        User user = optionalUser.get();
        Optional<Offer> optionalOffer = offerRepository.findById(offerId);
        if (optionalOffer.isEmpty())
            throw new PMSException("Offer not Found");
        Offer offer = optionalOffer.get();
        Optional<Property> optionalProperty = propertyRepository.findById(offer.getProperty().getId());
        if (optionalProperty.isEmpty())
            throw new PMSException("Property not found");
        Property property = optionalProperty.get();
        if (property.getPropertyStatus() == PropertyStatus.Contingent) {
            throw new PMSException("Property have Contingent status");
        }

        switch (user.getUserType()) {
            case Admin:
                throw new PMSException("Admin cannot change status of offer");
            case Owner:
                switch (change) {
                    case Accepted:
                        if (offer.getOfferStatusTo() == OfferStatus.Neutral) {
                            offer.setOfferStatusTo(change);
                            offerRepository.save(offer);
                            if (offer.getOfferStatusFrom() == OfferStatus.Neutral && property.getPropertyStatus() == PropertyStatus.Available) {
                                property.setPropertyStatus(PropertyStatus.Pending);
                                propertyRepository.save(property);
                            }
                        } else {
                            throw new PMSException("Cannot change status of offer");
                        }
                        break;
                    case Rejected:
                        if (offer.getOfferStatusTo() == OfferStatus.Neutral) {
                            rejectOfferStatusChange(change, offer, property, UserType.Owner);
                        } else {
                            throw new PMSException("Cannot change status of offer");
                        }
                        break;
                    case Neutral:
                        throw new PMSException("Cannot change status to Neutral");
                }
                break;
            case Customer:
                switch (change) {
                    case Accepted:
                        if (offer.getOfferStatusTo() != OfferStatus.Accepted)
                            throw new PMSException("Cannot accept offer if owner did not accepted offer.");
                        if (offer.getOfferStatusFrom() == OfferStatus.Neutral) {
                            offer.setOfferStatusFrom(change);
                            offerRepository.save(offer);
                            if (property.getPropertyStatus() == PropertyStatus.Pending) {
                                property.setPropertyStatus(PropertyStatus.Contingent);
                                propertyRepository.save(property);
                            }
                        } else {
                            throw new PMSException("Cannot change status of offer");
                        }
                        break;
                    case Rejected:
                        if (offer.getOfferStatusTo() != OfferStatus.Accepted)
                            throw new PMSException("Cannot reject offer if owner did not accepted offer.");
                        if (offer.getOfferStatusFrom() == OfferStatus.Neutral) {
                            rejectOfferStatusChange(change, offer, property, UserType.Customer);
                        } else {
                            throw new PMSException("Cannot change status of offer");
                        }
                        break;
                    case Neutral:
                        throw new PMSException("Cannot change status to Neutral");
                }
                break;
        }
        return null;
    }

    private void rejectOfferStatusChange(OfferStatus change, Offer offer, Property property, UserType userType) {
        if(userType == UserType.Customer) {
            offer.setOfferStatusFrom(change);
        } else {
            offer.setOfferStatusTo(change);
        }
        offerRepository.save(offer);
        List<Offer> propertyOffer = property.getOffers().stream().map(o -> {
            if (Objects.equals(o.getId(), offer.getId())) {
                if(userType == UserType.Customer) {
                    o.setOfferStatusFrom(OfferStatus.Rejected);
                } else {
                    o.setOfferStatusTo(OfferStatus.Rejected);
                }
            }
            return o;
        }).filter(o -> (o.getOfferStatusTo() == OfferStatus.Accepted && o.getOfferStatusFrom() != OfferStatus.Rejected)).toList();
        if (propertyOffer.isEmpty()) {
            property.setPropertyStatus(PropertyStatus.Available);
            propertyRepository.save(property);
        }
    }

    @Override
    public OfferResponseDTO submitOffer(OfferRequestDTO offerRequestDTO) {
        Optional<User> optionalUser1 = userRepository.findById(offerRequestDTO.getUserId());
        if (optionalUser1.isEmpty())
            throw new PMSException("User not Found");
        User userFrom = optionalUser1.get();
        if (userFrom.getUserType() != UserType.Customer)
            throw new PMSException("Only customer can send offer");

        Optional<Property> optionalProperty = propertyRepository.findById(offerRequestDTO.getPropertyId());
        if (optionalProperty.isEmpty())
            throw new PMSException("Property not found");
        Property property = optionalProperty.get();
        LocalDateTime now = LocalDateTime.now();
        Offer offer = new Offer();
        offer.setOfferStatusFrom(OfferStatus.Neutral);
        offer.setOfferStatusTo(OfferStatus.Neutral);
        offer.setOfferFrom(userFrom);
        offer.setOfferTo(property.getUser());
        offer.setPrice(offerRequestDTO.getPrice());
        offer.setOfferType(offerRequestDTO.getOfferType());
        offer.setProperty(property);
        offer.setLocalDateTime(now);

        return modelMapper.map(offerRepository.save(offer), OfferResponseDTO.class);
    }
}
