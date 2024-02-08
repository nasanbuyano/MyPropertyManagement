package com.miu.propertymanagement.controller;

import com.miu.propertymanagement.domain.dto.OfferRequestDTO;
import com.miu.propertymanagement.domain.enums.OfferStatus;
import com.miu.propertymanagement.service.FavoriteListService;
import com.miu.propertymanagement.service.OfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
@CrossOrigin("*")
public class CustomerController {
    @Autowired
    private OfferService offerService;

    @Autowired
    private FavoriteListService favoriteListService;

    @GetMapping("customer/{userId}/offer")
    ResponseEntity<?> getOffersByCustomer(@PathVariable("userId") Integer userId
    ) {
        return ResponseEntity.ok(offerService.getCustomerOffersById(userId));
    }

    @GetMapping("customer/{userId}/historyOffers")
    ResponseEntity<?> getHistoryOffersByCustomer(@PathVariable("userId") Integer userId
    ) {
        return ResponseEntity.ok(offerService.findHistoryOffersByCustomer(userId));
    }

    @GetMapping("customer/{userId}/liveOffers")
    ResponseEntity<?> getLiveOffersByCustomer(@PathVariable("userId") Integer userId
    ) {
        return ResponseEntity.ok(offerService.findLiveOffersByCustomer(userId));
    }

    @PostMapping("/customer/submitOffer")
    ResponseEntity<?> submitOffer(@RequestBody OfferRequestDTO offerRequestDTO) {
        return ResponseEntity.ok(offerService.submitOffer(offerRequestDTO));
    }

    @GetMapping("/customer/{userId}/offer/{offerId}")
    ResponseEntity<?> changeOfferStatus(@PathVariable("userId") Integer userId,
                                        @PathVariable("offerId") Integer offerId,
                                        @RequestParam(value = "changeOfferStatus") OfferStatus change) {
        return ResponseEntity.ok(offerService.changeOfferStatus(userId, offerId, change));
    }

    @GetMapping("/customer/{userId}/property/{propertyId}/addFavorite")
    ResponseEntity<?> addPropertyToFavoriteList(@PathVariable("userId") Integer userId,
                                        @PathVariable("propertyId") Integer propertyId) {
        return ResponseEntity.ok(favoriteListService.addPropertyToFavoriteList(userId, propertyId));
    }

    @GetMapping("/customer/{userId}/property/{propertyId}/removeFavorite")
    ResponseEntity<?> removePropertyFromFavoriteList(@PathVariable("userId") Integer userId,
                                                @PathVariable("propertyId") Integer propertyId) {
        return ResponseEntity.ok(favoriteListService.removePropertyFromFavoriteList(userId, propertyId));
    }

    @GetMapping("/customer/{userId}/favoriteList")
    ResponseEntity<?> getFavoriteListByUser(@PathVariable("userId") Integer userId) {
        return ResponseEntity.ok(favoriteListService.getFavoriteListByUser(userId));
    }
}
